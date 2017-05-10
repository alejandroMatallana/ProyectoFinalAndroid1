package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerCargo;
import matallana.alejandro.proyectofinalandroid1.R;

public class CargoActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtHorario;
    EditText txtSalario;
    EditText txtDescripcion;
    ControllerCargo controllerCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo);
        txtNombre = (EditText)findViewById(R.id.nombre);
        txtHorario = (EditText)findViewById(R.id.horario);
        txtSalario = (EditText)findViewById(R.id.salario);
        txtDescripcion = (EditText)findViewById(R.id.descripcion);
        controllerCargo = new ControllerCargo(this);
    }

    public void crear(View view) {

    }

    public void buscar(View view) {

    }

    public void eliminar(View view){
        String nombre = txtNombre.getText().toString();
        if (nombre!=null){
          controllerCargo.eliminar(nombre);
            Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }  else {
            Toast.makeText(this, "Error!!!  no se encuenta alamcenando", Toast.LENGTH_SHORT).show();
        }
    }

    public void editar(View view){
        if (txtSalario.getText().toString().isEmpty() || txtHorario.getText().toString().isEmpty() || txtDescripcion.getText().toString().isEmpty()
                || txtNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe llenar todos los campos para continuar", Toast.LENGTH_SHORT).show();
        } else {
            String nombre = txtNombre.getText().toString();
            String descripcion = txtDescripcion.getText().toString();
            String horario = txtHorario.getText().toString();
            double salario = Double.parseDouble(txtSalario.getText().toString());
            if (controllerCargo.modificar(nombre,descripcion,horario,salario)){
                Toast.makeText(this, "Editado correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "Error!!! alamcenando la información", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void limpiarCampos(){
        txtNombre.setText(null);
        txtDescripcion.setText(null);
        txtHorario.setText(null);
        txtSalario.setText(null);
    }

}
