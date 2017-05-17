package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerDatosIntegranteProyectoActivity;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class DatosIntegranteProyectoActivity extends AppCompatActivity {

    public static Usuario usuario = null;
    public static Cargo cargo = null;
    private EditText txtTipoDoc, txtDocumento, txtnombreCompleto, txtCorreo;
    private Spinner spnCargo;
    private Button btnAgregarIntegrante, btnQuitarIntegrante;
    private TextView lblUsuarioIntegrante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_integrante_proyecto);
        txtCorreo = (EditText) findViewById(R.id.txtDatosUCorreo);
        txtDocumento = (EditText) findViewById(R.id.txtDatosUDocumento);
        txtnombreCompleto = (EditText) findViewById(R.id.txtDatosUNombre);
        txtTipoDoc = (EditText) findViewById(R.id.txtDatosUTipoDocu);
        spnCargo = (Spinner) findViewById(R.id.spnDatosUCargo);
        lblUsuarioIntegrante = (TextView) findViewById(R.id.lblUsuarioIntegrante);
        cargarSpinnerCargos();
        mostrarDatosUsuario();
    }

    public void cerrarDatosIntegranteProyecto(View view){
        usuario = null;
        cargo = null;
        finish();
    }

    public void agregarEsteIntegrante(View view){
        if(usuario != null){
            int idUsuario = usuario.getId();
            int idProyecto = MenuProyectosActivity.proyecto.getId();
            Cargo cargo = (Cargo)spnCargo.getSelectedItem();
            ControllerDatosIntegranteProyectoActivity controller = new ControllerDatosIntegranteProyectoActivity(this);
            boolean res = controller.agregarIntegrante(cargo.getId(),idUsuario,idProyecto);
            if(res){
                Toast.makeText(this,"El usuario ha sido agregado como integrante del proyecto con " +
                        "exito", Toast.LENGTH_SHORT).show();
                usuario=null;
                cargo=null;
                finish();
            }
        }
    }

    public void quitarEsteIntegrante(View view){
        if(usuario != null){
            int idUsuario = usuario.getId();
            int idProyecto = MenuProyectosActivity.proyecto.getId();
            ControllerDatosIntegranteProyectoActivity controller = new ControllerDatosIntegranteProyectoActivity(this);
            boolean res = controller.quitarIntegrante(idUsuario,idProyecto);
            if(res){
                Toast.makeText(this,"El usuario ha sido eliminado como integrante del proyecto con " +
                        "exito", Toast.LENGTH_SHORT).show();
                usuario=null;
                cargo=null;
                finish();
            }
        }
    }

    public void mostrarDatosUsuario(){
        if(usuario != null){
            txtTipoDoc.setText(usuario.getTipoDocumento());
            txtnombreCompleto.setText(usuario.getNombres() + " " + usuario.getApellidos());
            txtDocumento.setText(String.valueOf(usuario.getNumeroDocumento()));
            txtCorreo.setText(usuario.getCorreoElectronico());

            //si se va a agregar el usuario como integrante
            if(cargo == null){
                ControllerDatosIntegranteProyectoActivity controller = new ControllerDatosIntegranteProyectoActivity(this);
                boolean res = controller.esUsuarioUnIntegrante(usuario.getId(),MenuProyectosActivity.proyecto.getId());
                if(res){
                    ocultarBotonAgregar();
                } else {
                    ocultarBotonQuitar();
                }
            } else {
                //entra al else, si el cargo es diferente de null significa que el usuario ya es
                //integrante del proyecto
                ocultarBotonAgregar();
            }

        }
    }

    public void cargarSpinnerCargos(){
        ControllerDatosIntegranteProyectoActivity controller = new ControllerDatosIntegranteProyectoActivity(this);
        ArrayList<Cargo> lista = controller.listarCargosDelProyecto(MenuProyectosActivity.proyecto);
        ArrayAdapter<Cargo> adapter = new ArrayAdapter<Cargo>(this,android.R.layout.simple_spinner_item,lista);
        spnCargo.setAdapter(adapter);
    }

    public void ocultarBotonAgregar(){
        btnAgregarIntegrante.setVisibility(View.INVISIBLE);
        btnQuitarIntegrante.setVisibility(View.VISIBLE);
        lblUsuarioIntegrante.setVisibility(View.VISIBLE);
    }

    public void ocultarBotonQuitar(){
        btnAgregarIntegrante.setVisibility(View.VISIBLE);
        btnQuitarIntegrante.setVisibility(View.INVISIBLE);
        lblUsuarioIntegrante.setVisibility(View.INVISIBLE);
    }
}
