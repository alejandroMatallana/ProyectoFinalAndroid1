package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerActividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaActividadesActivity extends AppCompatActivity {


    private ListView listaActividades;
    private ControllerActividad controllerActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_actividades);
        listaActividades = (ListView) findViewById(R.id.TVlistaActividades);
        controllerActividad = new ControllerActividad(this);
        cargarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    public void cargarLista(){
        final List<Actividad> actividades = controllerActividad.listar(MenuProyectosActivity.proyecto);
        ArrayAdapter<Actividad> adapter = new ArrayAdapter<Actividad>(this,android.R.layout.simple_list_item_1,actividades);
        listaActividades.setAdapter(adapter);
        listaActividades.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                MenuActividadesActivity.actividad = actividades.get(posicion);
                ActividadesActivity.tipo = 1;
                Intent intent = new Intent(getApplicationContext(),MenuActividadesActivity.class);
                startActivity(intent);

            }
        });
    }


    public void crearActividad(View view){
        ActividadesActivity.tipo=0;
        Intent intent = new Intent(this,ActividadesActivity.class);
        startActivity(intent);
    }
}
