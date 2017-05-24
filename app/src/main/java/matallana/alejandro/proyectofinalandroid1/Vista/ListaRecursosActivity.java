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

import java.util.ArrayList;
import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerRecurso;
import matallana.alejandro.proyectofinalandroid1.DAO.UsuarioDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Recurso;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaRecursosActivity extends AppCompatActivity {

    private Button crear;
    private EditText nombreRecurso;
    private ListView listaRecursos;
    private ControllerRecurso controllerRecurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recursos);
        nombreRecurso = (EditText) findViewById(R.id.nombreRecurso);
        listaRecursos = (ListView) findViewById(R.id.listaRecursos);
        crear = (Button) findViewById(R.id.crear);
        controllerRecurso = new ControllerRecurso(this);
        if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_INTEGRANTE)) {
            crear.setVisibility(View.GONE);
            listaRecursosIntegrante();
        } else {
            cargarLista();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        nombreRecurso.setText(null);
        if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_INTEGRANTE)) {
            crear.setVisibility(View.GONE);
            listaRecursosIntegrante();
        } else {
            cargarLista();
        }
    }

    public void buscar(View view) {
        if (nombreRecurso.getText().toString().isEmpty()) {
            Toast.makeText(this,"Debe ingresar el nombre del recurso",Toast.LENGTH_SHORT).show();
        } else {
            Recurso recurso = controllerRecurso.buscar(nombreRecurso.getText().toString());
            if (recurso == null) {
                Toast.makeText(this,"El recurso que busca no existe",Toast.LENGTH_SHORT).show();
            } else {
                cargarBusqueda(recurso);
            }
        }
    }

    public void crear(View view) {
        RecursoActivity.tipo = 0;
        Intent intent = new Intent(this,RecursoActivity.class);
        startActivity(intent);
    }

    public void refrescar(View view) {
        if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_DIRECTOR)) {
            cargarLista();
        } else {
            listaRecursosIntegrante();
        }
    }

    public void cargarLista() {
        final List<Recurso> recursos = controllerRecurso.listar();
        ArrayAdapter<Recurso> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,recursos);
        listaRecursos.setAdapter(adapter);
        listaRecursos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                RecursoActivity.recurso = recursos.get(posicion);
                Intent intent = new Intent(getApplicationContext(),MenuRecursosActivity.class);
                startActivity(intent);
            }
        });
    }

    public void cargarBusqueda(Recurso recurso) {
        final List<Recurso> recursos = new ArrayList<>();
        recursos.add(recurso);
        ArrayAdapter<Recurso> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,recursos);
        listaRecursos.setAdapter(adapter);
        if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_DIRECTOR)) {
            listaRecursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                    RecursoActivity.recurso = recursos.get(posicion);
                    Intent intent = new Intent(getApplicationContext(), MenuRecursosActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    public void listaRecursosIntegrante() {
        final List<Recurso> recursos = controllerRecurso.listarRecursosIntegrante(MenuActividadesActivity.actividad,MenuProyectosActivity.proyecto, UsuarioDAO.IDUsuarioLogueado);
        ArrayAdapter<Recurso> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,recursos);
        listaRecursos.setAdapter(adapter);
    }
}
