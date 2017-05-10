package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerProyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaProyectoActivity extends AppCompatActivity {

    private ListView lstProyectos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proyecto);
        lstProyectos = (ListView) findViewById(R.id.lstListaProyectos);
        cargarLista();
    }

    public void abrirCrearNuevoProyecto(View view){

    }

    public void cargarLista(){
        ControllerProyecto controlador = new ControllerProyecto(this);
        ArrayList<Proyecto> lista = controlador.listarProyectos();
        ArrayAdapter<Proyecto> adapter = new ArrayAdapter<Proyecto>(this,android.R.layout.simple_list_item_1,lista);
        lstProyectos.setAdapter(adapter);
    }

}
