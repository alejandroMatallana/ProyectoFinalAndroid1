package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerCargo;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaCargosActivity extends AppCompatActivity {

    private ListView listaCargos;
    private ControllerCargo controllerCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cargos);
        listaCargos = (ListView) findViewById(R.id.lista);
        controllerCargo = new ControllerCargo(this);
        configurarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaCargos = (ListView) findViewById(R.id.lista);
        configurarLista();
    }

    public void configurarLista() {
        List<Cargo> cargos = controllerCargo.listar();
        ArrayAdapter<Cargo> adapter = new ArrayAdapter<Cargo>(this,android.R.layout.simple_list_item_1,cargos);
        listaCargos.setAdapter(adapter);
        listaCargos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                abrirGestionCargo(listaCargos.getItemAtPosition(posicion).toString());
            }
        });
    }

    public void abrirGestionCargo(String datos) {
        CargoActivity.tipo = 1;
        Intent intent = new Intent(this,CargoActivity.class);
        startActivity(intent);
        String[] dat = datos.split(" - ");
        CargoActivity.cargo = controllerCargo.buscar(dat[0]);
    }

    public void crearCargo(View view) {
        CargoActivity.tipo = 0;
        Intent intent = new Intent(this,CargoActivity.class);
        startActivity(intent);
    }
}