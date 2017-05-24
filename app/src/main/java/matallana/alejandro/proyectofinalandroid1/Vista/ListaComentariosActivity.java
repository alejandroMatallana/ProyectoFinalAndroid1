package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerComentario;
import matallana.alejandro.proyectofinalandroid1.Modelo.ComentarioTarea;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaComentariosActivity extends AppCompatActivity {

    private ListView lista;
    private ControllerComentario controllerComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);
        lista = (ListView) findViewById(R.id.lista);
        controllerComentario = new ControllerComentario(this);
        cargarLista();
    }

    public void cargarLista() {
        final List<ComentarioTarea> comentarios = controllerComentario.listar(TareaActivity.tarea);
        ArrayAdapter<ComentarioTarea> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comentarios);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                ComentarioActivity.comentario = comentarios.get(posicion);
                Intent intent = new Intent(getApplicationContext(), ComentarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
