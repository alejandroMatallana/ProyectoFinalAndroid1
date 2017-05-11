package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerCargo;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;
import matallana.alejandro.proyectofinalandroid1.R;

public class CargoActivity extends AppCompatActivity {

    public static int tipo=0;
    public static Cargo cargo = null;

    private EditText txtNombre;
    private EditText txtHorario;
    private EditText txtSalario;
    private EditText txtDescripcion;
    private Button crear;
    private Button modificar;
    private Button eliminar;
    private ControllerCargo controllerCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo);
        txtNombre = (EditText)findViewById(R.id.nombre);
        txtHorario = (EditText)findViewById(R.id.horario);
        txtSalario = (EditText)findViewById(R.id.salario);
        txtDescripcion = (EditText)findViewById(R.id.descripcion);
        controllerCargo = new ControllerCargo(this);
        crear = (Button) findViewById(R.id.crear);
        modificar = (Button) findViewById(R.id.modificar);
        eliminar = (Button) findViewById(R.id.eliminar);
        if (tipo == 1) {
            crear.setVisibility(View.GONE);
        } else {
            modificar.setVisibility(View.GONE);
            eliminar.setVisibility(View.GONE);
        }
        if (cargo != null) {
            cargarCargo();
        }
    }

    public void crear(View view) {
        if (txtNombre.getText().toString().isEmpty() || txtHorario.getText().toString().isEmpty() ||
                txtSalario.getText().toString().isEmpty() || txtDescripcion.getText().toString().isEmpty()) {
            Toast.makeText(this,"Se debe ingresar todos los datos",Toast.LENGTH_SHORT).show();
        } else {
            Cargo cargo = new Cargo();
            cargo.setNombre(txtNombre.getText().toString());
            cargo.setHorario(txtHorario.getText().toString());
            cargo.setSalario(Double.parseDouble(txtSalario.getText().toString()));
            cargo.setDescripcion(txtDescripcion.getText().toString());
            cargo.setProyecto(MenuProyectosActivity.proyecto);
            if (controllerCargo.guardar(cargo)) {
                Toast.makeText(this,"Se registró el cargo",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"El cargo ya existe",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminar(View view){
        String nombre = txtNombre.getText().toString();
        if (nombre!=null){
          controllerCargo.eliminar(nombre);
            Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
            finish();
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
                finish();
            } else {
                Toast.makeText(this, "Error!!! alamcenando la información", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void cargarCargo() {
        txtNombre.setText(cargo.getNombre());
        txtDescripcion.setText(cargo.getDescripcion());
        txtHorario.setText(cargo.getHorario());
        txtSalario.setText(String.valueOf(cargo.getSalario()));
    }

    public void limpiarCampos(){
        txtNombre.setText(null);
        txtDescripcion.setText(null);
        txtHorario.setText(null);
        txtSalario.setText(null);
        cargo = null;
    }
}
