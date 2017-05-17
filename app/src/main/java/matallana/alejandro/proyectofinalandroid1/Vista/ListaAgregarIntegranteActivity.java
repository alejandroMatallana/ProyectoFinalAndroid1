package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerListaAgregarIntegranteActivity;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class ListaAgregarIntegranteActivity extends AppCompatActivity {

    private EditText txtBuscarUsuario;
    private ListView lstUsuarios;
    private ArrayList<Usuario> usuarioTipoIntegrante;
    private ArrayList<Usuario> usuariosAuxiliar; //lista para cuando se busque el usuario por nombre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_agregar_integrante);
        txtBuscarUsuario = (EditText) findViewById(R.id.txtBuscarUsuarioIntegrante);
        lstUsuarios = (ListView) findViewById(R.id.lstUsuariosIntegrantes);
        cargarListaUsuariosTipoIntegrante();
        cargarListViewUsuarios();
    }

    public void limpiarCampoBuscarUsuarioIntegrante(View view){
        txtBuscarUsuario.setText("");
        cargarListViewUsuarios();
    }

    public void buscarUsuarioIntegrante(View view){
        String identificacionNombreUser = txtBuscarUsuario.getText().toString();
        if(identificacionNombreUser.isEmpty()){
            cargarListViewUsuarios();
        } else {
            int identificacionUser = -1;
            String nombre = null;

            try{
                //si ingresaron solo numeros significa que buscaron por numero de documento
                identificacionUser = Integer.parseInt(identificacionNombreUser);
            } catch (NumberFormatException e){
                //si sale un NumberFormatException significa que digitaron letras
                //osea buscaron por nombre
                nombre = identificacionNombreUser;
            }

            //si no salio un NumberFormatException y se cambio el valor de identificacionUser
            if(identificacionUser > 0){
                ControllerListaAgregarIntegranteActivity controller = new ControllerListaAgregarIntegranteActivity(this);
                Usuario user = controller.buscarUsuario(identificacionUser);
                if(user != null){

                } else {
                    Toast.makeText(this,"No se encontro un usuario con ese numero de identificación"
                            ,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void cargarListViewUsuarios(){
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this,android.R.layout.simple_list_item_1,usuarioTipoIntegrante);
        lstUsuarios.setAdapter(adapter);
        lstUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void cargarListaUsuariosTipoIntegrante(){
        ControllerListaAgregarIntegranteActivity controller = new ControllerListaAgregarIntegranteActivity(this);
        usuarioTipoIntegrante = controller.listarUsuariosTipoIntegrante();
    }
}
