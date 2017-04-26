package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import matallana.alejandro.proyectofinalandroid1.R;

public class UsuarioActivity extends AppCompatActivity {

    Spinner tipoDocumento;
    EditText numeroDoc;
    EditText nombre;
    EditText apellido;
    EditText fechaNacimiento;
    EditText correo;
    EditText password;
    Spinner tipoUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        tipoDocumento = (Spinner) findViewById(R.id.tipoDocumento);
        numeroDoc = (EditText)findViewById(R.id.numDocumento);
        nombre = (EditText)findViewById(R.id.nombreUSu);
        apellido = (EditText)findViewById(R.id.apellidoUsu);
        //Aca va la fecha
        correo = (EditText)findViewById(R.id.correoUsu);
        password = (EditText)findViewById(R.id.password);
        tipoUsuario = (Spinner) findViewById(R.id.tipoUsu);
    }

    public void crear(View view){

    }

    public void buscar(View view){

    }

    public void eliminar(View view){

    }

    public void editar(View view){

    }
}
