package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerListaIntegrantesActivity;
import matallana.alejandro.proyectofinalandroid1.Modelo.ProyectosIntegrantes;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaIntegrantesActivity extends AppCompatActivity {

    private ListView lstLista;
    private ArrayList<ProyectosIntegrantes> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_integrantes);
        lstLista = (ListView) findViewById(R.id.lstListaIntegrantes);
        cargarListaIntegrantes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarListaIntegrantes();
    }

    public void abrirAgregarNuevoIntegrante(View view){
        Intent i = new Intent(this, ListaAgregarIntegranteActivity.class);
        startActivity(i);
    }

    public void cargarListaIntegrantes(){
        ControllerListaIntegrantesActivity controlador = new ControllerListaIntegrantesActivity(this);
        int idProyecto = MenuProyectosActivity.proyecto.getId();
        lista = controlador.listarIntegrantesDelProyecto(idProyecto);
        ArrayAdapter<ProyectosIntegrantes> adapter = new ArrayAdapter<ProyectosIntegrantes>(
                this,android.R.layout.simple_list_item_1,lista);
        lstLista.setAdapter(adapter);
        lstLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProyectosIntegrantes pi = lista.get(position);
                DatosIntegranteProyectoActivity.usuario = pi.getIntegrante();
                DatosIntegranteProyectoActivity.cargo = pi.getCargo();
                Intent i = new Intent(getApplicationContext(), DatosIntegranteProyectoActivity.class);
                startActivity(i);
            }
        });
    }
}
