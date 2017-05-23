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

import java.util.ArrayList;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerProyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaProyectoActivity extends AppCompatActivity {

    private ListView lstProyectos;
    private ArrayList<Proyecto> lista, auxiliar;
    private EditText txtNombreBuscar;
    private Button btnCrearNuevoProyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proyecto);
        lstProyectos = (ListView) findViewById(R.id.lstListaProyectos);
        txtNombreBuscar = (EditText) findViewById(R.id.txtBuscarProyecto);
        btnCrearNuevoProyecto = (Button) findViewById(R.id.btnCrearNuevoProyecto);
        cargarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtNombreBuscar.setText("");
        cargarLista();
    }

    /**
     * metodo para ocultar o mostrar el botón de crear proyecto según que usuario se
     * logueo, oculta el botón si se logueo un usuario tipo integrante y lo muestra si se logueo
     * un usuario tipo director
     */
    public void ocultarBotonCrearProyecto(){
        if(MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_DIRECTOR)){
            btnCrearNuevoProyecto.setVisibility(View.VISIBLE);
        } else if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_INTEGRANTE)){
            btnCrearNuevoProyecto.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * metodo que lista los proyectos que contengan los
     * caracteres ingresados en el campo de buscar proyecto
     */
    public void listaBuscarProyectos(View view){
        String nombreProyecto = txtNombreBuscar.getText().toString();
        if(nombreProyecto.isEmpty()){
            cargarLista();
        } else {
            auxiliar = new ArrayList<>();
            final ControllerProyecto controlador = new ControllerProyecto(this);
            lista = controlador.listarProyectos();
            //busca en la lista de todos los proyectos los que contengan en su nombre
            //lo ingresado en el campo de texto de buscar proyecto
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getNombre().toLowerCase().contains(nombreProyecto.toLowerCase())){
                    auxiliar.add(lista.get(i));
                }
            }
            ArrayAdapter<Proyecto> adapter = new ArrayAdapter<Proyecto>(this,android.R.layout.simple_list_item_1,auxiliar);
            lstProyectos.setAdapter(adapter);
            lstProyectos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Proyecto proyectoSeleccionado = auxiliar.get(position);
                    MenuProyectosActivity.proyecto = proyectoSeleccionado;
                    Intent i = new Intent(getApplicationContext(),MenuProyectosActivity.class);
                    startActivity(i);
                }
            });
        }
    }

    public void limpiarBusquedaProyecto(View view){
        txtNombreBuscar.setText("");
        cargarLista();
    }

    public void abrirCrearNuevoProyecto(View view){
        MenuProyectosActivity.proyecto = null;
        Intent i = new Intent(this, GestionProyectoActivity.class);
        startActivity(i);
    }

    public void cargarLista(){
        ControllerProyecto controlador = new ControllerProyecto(this);
        lista = controlador.listarProyectos();
        ArrayAdapter<Proyecto> adapter = new ArrayAdapter<Proyecto>(this,android.R.layout.simple_list_item_1,lista);
        lstProyectos.setAdapter(adapter);
        lstProyectos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Proyecto proyectoSeleccionado = lista.get(position);
                MenuProyectosActivity.proyecto = proyectoSeleccionado;
                Intent i = new Intent(getApplicationContext(),MenuProyectosActivity.class);
                startActivity(i);
            }
        });
    }

}
