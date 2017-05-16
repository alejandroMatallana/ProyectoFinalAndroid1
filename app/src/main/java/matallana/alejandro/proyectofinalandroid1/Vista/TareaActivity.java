package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerTarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.R;

public class TareaActivity extends AppCompatActivity {

    public static int tipo = 0;
    private ControllerTarea controllerTarea;
    private EditText nombre, procentaje, fechaIni, fechaFin;
    private Button crear, editar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);
        controllerTarea = new ControllerTarea(this);
        nombre = (EditText) findViewById(R.id.nombreTarea);
        procentaje = (EditText) findViewById(R.id.porcentajeTarea);
        fechaIni = (EditText) findViewById(R.id.fechaIni);
        fechaFin = (EditText) findViewById(R.id.fechaFin);
        crear = (Button) findViewById(R.id.botonCrear);
        editar = (Button) findViewById(R.id.botonEditar);
        eliminar = (Button) findViewById(R.id.botonEliminar);
        if (tipo == 0) {
            editar.setVisibility(View.GONE);
            eliminar.setVisibility(View.GONE);
        } else {
            crear.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        limpiar();
    }

    public void crear(View view) {
        limpiar();
    }

    public void editar(View view) {
        finish();
    }

    public void eliminar(View view) {
        finish();
    }

    public void limpiar() {
        tipo = 0;
        nombre.setText(null);
        procentaje.setText(null);
        fechaIni.setText(null);
        fechaFin.setText(null);
    }
}
