package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerRecurso;
import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerTareasRecursos;
import matallana.alejandro.proyectofinalandroid1.Modelo.Recurso;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.TareasRecursos;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaRecursosTareasActivity extends AppCompatActivity {

    public static int tipo = 0;
    private EditText nombre;
    private Button buscar;
    private ListView lista;
    private ControllerTareasRecursos controllerTareasRecursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recursos_tareas);
        lista = (ListView) findViewById(R.id.lista);
        nombre = (EditText) findViewById(R.id.nombre);
        buscar = (Button) findViewById(R.id.buscar);
        controllerTareasRecursos= new ControllerTareasRecursos(this);
        if (tipo == 0) {
            agregarRecurso();
        } else {
            quitarRecurso();
            nombre.setVisibility(View.GONE);
            buscar.setVisibility(View.GONE);
        }
    }

    public void quitarRecurso() {
        final List<TareasRecursos> tareas = controllerTareasRecursos.listarTareasConRecursos(MenuProyectosActivity.proyecto,RecursoActivity.recurso);
        ArrayAdapter<TareasRecursos> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tareas);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                controllerTareasRecursos.eliminar(tareas.get(posicion));
                Toast.makeText(getApplicationContext(),"Se quito el recurso de la tarea",Toast.LENGTH_SHORT).show();
                quitarRecurso();
            }
        });
    }

    public void agregarRecurso() {
        final List<Tarea> tareas = controllerTareasRecursos.listarTareas(MenuProyectosActivity.proyecto);
        ArrayAdapter<Tarea> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tareas);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                TareasRecursos tareasRecursos = new TareasRecursos(tareas.get(posicion),RecursoActivity.recurso);
                controllerTareasRecursos.crear(tareasRecursos);
                Toast.makeText(getApplicationContext(),"Se agrego el recurso a la tarea",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
