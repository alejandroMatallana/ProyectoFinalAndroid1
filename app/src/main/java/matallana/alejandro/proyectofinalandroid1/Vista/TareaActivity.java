package matallana.alejandro.proyectofinalandroid1.Vista;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerTarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.R;

public class TareaActivity extends AppCompatActivity {

    public static int tipo = 0;
    private ControllerTarea controllerTarea;
    private EditText nombre, porcentaje, fechaIni, fechaFin;
    private Button crear, editar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);
        controllerTarea = new ControllerTarea(this);
        nombre = (EditText) findViewById(R.id.nombreTarea);
        porcentaje = (EditText) findViewById(R.id.porcentajeTarea);
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
        fechaIni.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                DatePickerDialog mDatePicker=new DatePickerDialog(TareaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        fechaIni.setText(selectedyear+"/"+selectedmonth+"/"+selectedday);
                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
        fechaFin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                DatePickerDialog mDatePicker=new DatePickerDialog(TareaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        fechaFin.setText(selectedyear+"/"+selectedmonth+"/"+selectedday);
                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        limpiar();
    }

    public void crear(View view) {
        if (nombre.getText().toString().isEmpty() || porcentaje.getText().toString().isEmpty() ||
                fechaIni.getText().toString().isEmpty() || fechaFin.getText().toString().isEmpty()) {
            Toast.makeText(this,"Debe ingresar todos los datos",Toast.LENGTH_SHORT).show();
        } else {
            Calendar fechaInicial = Calendar.getInstance();
            String[] datosfechaIni = fechaIni.getText().toString().split("/");
            fechaInicial.set(Integer.parseInt(datosfechaIni[0]), Integer.parseInt(datosfechaIni[1]),
                    Integer.parseInt(datosfechaIni[2]));
            fechaInicial.set(Calendar.HOUR_OF_DAY,0);
            fechaInicial.set(Calendar.MINUTE,0);
            fechaInicial.set(Calendar.SECOND,0);
            fechaInicial.set(Calendar.MILLISECOND,0);
            Calendar fechaFinal = Calendar.getInstance();
            String[] datosfechaFin = fechaFin.getText().toString().split("/");
            fechaFinal.set(Integer.parseInt(datosfechaFin[0]), Integer.parseInt(datosfechaFin[1]),
                    Integer.parseInt(datosfechaFin[2]));
            fechaFinal.set(Calendar.HOUR_OF_DAY,0);
            fechaFinal.set(Calendar.MINUTE,0);
            fechaFinal.set(Calendar.SECOND,0);
            fechaFinal.set(Calendar.MILLISECOND,0);
            Tarea tarea = new Tarea();
            tarea.setActividad(MenuActividadesActivity.actividad);
            tarea.setPorcentaje(Integer.parseInt(porcentaje.getText().toString()));
            tarea.setNombreTarea(nombre.getText().toString());
            tarea.setFechaInicio(fechaInicial.getTime());
            tarea.setFechaFinal(fechaFinal.getTime());
            String resp = controllerTarea.crear(tarea);
            if (resp.equalsIgnoreCase("Se registro la tarea")) {
                limpiar();
            }
            Toast.makeText(this,resp,Toast.LENGTH_SHORT).show();
        }
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
        porcentaje.setText(null);
        fechaIni.setText(null);
        fechaFin.setText(null);
    }
}
