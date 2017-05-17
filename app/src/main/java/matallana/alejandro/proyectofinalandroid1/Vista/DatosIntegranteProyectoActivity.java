package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
        mostrarDatosUsuario();
    }

    public void cerrarDatosIntegranteProyecto(View view){
        usuario = null;
        cargo = null;
        finish();
    }



    public void mostrarDatosUsuario(){
        if(usuario != null){
            txtTipoDoc.setText(usuario.getTipoDocumento());
            txtnombreCompleto.setText(usuario.getNombres() + " " + usuario.getApellidos());
            txtDocumento.setText(String.valueOf(usuario.getNumeroDocumento()));
            txtCorreo.setText(usuario.getCorreoElectronico());

            //si se va a agregar el usuario como integrante
            if(cargo == null){
                ocultarBotonQuitar();
            } else {
                //entra al else, si el cargo es diferente de null significa que el usuario ya es
                //integrante del proyecto
                ocultarBotonAgregar();
            }

        }
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