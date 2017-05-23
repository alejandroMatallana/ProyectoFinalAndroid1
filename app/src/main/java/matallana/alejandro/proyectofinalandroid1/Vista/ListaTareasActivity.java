package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerTarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaTareasActivity extends AppCompatActivity {

    private ListView listaTareas;
    private ControllerTarea controllerTarea;
    private EditText nombreTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);
        listaTareas = (ListView) findViewById(R.id.listaTareas);
        nombreTarea = (EditText) findViewById(R.id.nombreTarea);
        controllerTarea = new ControllerTarea(this);
        listar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listar();
    }

    public void crearTarea(View view) {
        TareaActivity.tipo = 0;
        Intent intent = new Intent(this,TareaActivity.class);
        startActivity(intent);
    }

    public void buscar(View view) {
        if (!nombreTarea.getText().toString().isEmpty()) {
            Tarea tarea = controllerTarea.buscar(nombreTarea.getText().toString());
            if (tarea != null) {
                modificarLista(tarea);
            } else {
                Toast.makeText(this,"No hay tarea con ese nombre",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Se debe ingresar un nombre para poder buscar la tarea",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificarLista(Tarea tarea) {
        final List<Tarea> busqueda = new ArrayList<>();
        busqueda.add(tarea);
        ArrayAdapter<Tarea> adapter = new ArrayAdapter<Tarea>(this,android.R.layout.simple_list_item_1,busqueda);
        listaTareas.setAdapter(adapter);
        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                TareaActivity.tipo = 1;
                TareaActivity.tarea = busqueda.get(posicion);
                Intent intent = new Intent(getApplicationContext(),TareaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void listar() {
        final List<Tarea> tareas = controllerTarea.listar();
        ArrayAdapter<Tarea> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareas);
        listaTareas.setAdapter(adapter);
        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                TareaActivity.tipo = 1;
                TareaActivity.tarea = tareas.get(posicion);
                Intent intent = new Intent(getApplicationContext(), TareaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void refrescar(View view) {
        listar();
    }
}
