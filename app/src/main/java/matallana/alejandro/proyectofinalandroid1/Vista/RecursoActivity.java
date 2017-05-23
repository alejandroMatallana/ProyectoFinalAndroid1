package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerRecurso;
import matallana.alejandro.proyectofinalandroid1.Modelo.Recurso;
import matallana.alejandro.proyectofinalandroid1.R;

public class RecursoActivity extends AppCompatActivity {

    private ControllerRecurso controllerRecurso;
    public static Recurso recurso = null;
    public static int tipo = 0;
    private EditText nombre, cantidad, descripcion, ubicacion;
    private Button crear, editar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurso);
        controllerRecurso = new ControllerRecurso(this);
        nombre = (EditText) findViewById(R.id.nombre);
        cantidad = (EditText) findViewById(R.id.cantidad);
        descripcion = (EditText) findViewById(R.id.descripcion);
        ubicacion = (EditText) findViewById(R.id.ubicacion);
        crear = (Button) findViewById(R.id.crear);
        editar = (Button) findViewById(R.id.editar);
        eliminar = (Button) findViewById(R.id.eliminar);
        if (tipo == 0 && recurso == null) {
            editar.setVisibility(View.GONE);
            eliminar.setVisibility(View.GONE);
        } else {
            crear.setVisibility(View.GONE);
            nombre.setEnabled(false);
            cargarDatos();
        }
    }

    public void crear(View view) {
        if (nombre.getText().toString().isEmpty() || cantidad.getText().toString().isEmpty() ||
                descripcion.getText().toString().isEmpty() || ubicacion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {
            Recurso recurso = new Recurso(nombre.getText().toString(), Integer.parseInt(cantidad.getText().toString()),
                    descripcion.getText().toString(), ubicacion.getText().toString(), MenuProyectosActivity.proyecto);
            Toast.makeText(this, controllerRecurso.crear(recurso), Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }
    }

    public void editar(View view) {
        if (cantidad.getText().toString().isEmpty() || descripcion.getText().toString().isEmpty() ||
                ubicacion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {
            recurso.setCantidad(Integer.parseInt(cantidad.getText().toString()));
            recurso.setUbicacion(ubicacion.getText().toString());
            recurso.setDescripcion(descripcion.getText().toString());
            if (controllerRecurso.editar(recurso)) {
                Toast.makeText(this, "Se edito el recurso", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                finish();
            } else {
                Toast.makeText(this, "Hubo un error editando el recurso", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminar(View view) {
        if (controllerRecurso.eliminar(recurso)) {
            Toast.makeText(this, "Se elimino el recurso", Toast.LENGTH_SHORT).show();
            tipo = 2;
            limpiarCampos();
            finish();
        } else {
            Toast.makeText(this, "Hubo un error eliminando el recurso", Toast.LENGTH_SHORT).show();
        }
    }

    public void cargarDatos() {
        nombre.setText(recurso.getNombre());
        cantidad.setText(String.valueOf(recurso.getCantidad()));
        descripcion.setText(recurso.getDescripcion());
        ubicacion.setText(recurso.getUbicacion());
    }

    public void limpiarCampos() {
        nombre.setText(null);
        cantidad.setText(null);
        descripcion.setText(null);
        ubicacion.setText(null);
    }
}
