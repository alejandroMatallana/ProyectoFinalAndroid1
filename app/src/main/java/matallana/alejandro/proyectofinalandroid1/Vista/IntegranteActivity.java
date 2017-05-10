package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import matallana.alejandro.proyectofinalandroid1.R;

public class IntegranteActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtHorario;
    EditText txtSalario;
    EditText txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrante);
        txtNombre = (EditText)findViewById(R.id.nombre);
        txtHorario = (EditText)findViewById(R.id.horario);
        txtSalario = (EditText)findViewById(R.id.salario);
        txtDescripcion = (EditText)findViewById(R.id.descripcion);
    }
}
