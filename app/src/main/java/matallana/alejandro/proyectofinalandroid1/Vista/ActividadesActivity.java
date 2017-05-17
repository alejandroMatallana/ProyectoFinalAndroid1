package matallana.alejandro.proyectofinalandroid1.Vista;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerActividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.R;

public class ActividadesActivity extends AppCompatActivity {

    private Button crear;
    private EditText txtNomber, txtDescripcion, txtFechaInicio, txtFechaFin;
    private ControllerActividad controllerActividad;
    public static Actividad actividad= null;
    public static  int tipo =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        txtNomber = (EditText) findViewById(R.id.nomActividad);
        txtDescripcion = (EditText) findViewById(R.id.descripActividad);
        txtFechaInicio = (EditText) findViewById(R.id.dateInicioActividad);
        txtFechaFin = (EditText) findViewById(R.id.dateFinActividad);
        controllerActividad = new ControllerActividad(this);

        txtFechaInicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                DatePickerDialog mDatePicker=new DatePickerDialog(ActividadesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        txtFechaInicio.setText(selectedyear+"/"+selectedmonth+"/"+selectedday);
                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });


        txtFechaFin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                DatePickerDialog mDatePicker=new DatePickerDialog(ActividadesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        txtFechaFin.setText(selectedyear+"/"+selectedmonth+"/"+selectedday);
                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });



        cargarActividad();
    }


    public void crearActividad (View view) throws ParseException {
        if (txtNomber.getText().toString().isEmpty() || txtDescripcion.getText().toString().isEmpty() ||
                txtFechaInicio.getText().toString().isEmpty() || txtFechaFin.getText().toString().isEmpty()){
            Toast.makeText(this, "Favor ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {
            String fechaIni = txtFechaInicio.getText().toString();
            String fechaFi = txtFechaFin.getText().toString();

            SimpleDateFormat fechas = new SimpleDateFormat("yyyy/MM/dd");
            Date inicio = fechas.parse(fechaIni);
            Date fin = fechas.parse(fechaFi);


            if (inicio.compareTo(fin)!=1){

                ControllerActividad controllerActividad = new ControllerActividad(this);

                Actividad actividad = new Actividad();


                Calendar fechaInicial = Calendar.getInstance();
                String[] datosfechaIni = txtFechaInicio.getText().toString().split("/");
                fechaInicial.set(Integer.parseInt(datosfechaIni[0]), Integer.parseInt(datosfechaIni[1]),
                        Integer.parseInt(datosfechaIni[2]));


                Calendar fechaFin = Calendar.getInstance();
                String[] datosFechaFin = txtFechaFin.getText().toString().split("/");
                fechaFin.set(Integer.parseInt(datosFechaFin[0]), Integer.parseInt(datosFechaFin[1]),
                        Integer.parseInt(datosFechaFin[2]));

                actividad.setNombre(txtNomber.getText().toString());
                actividad.setDescripcion(txtDescripcion.getText().toString());
                actividad.setFechaIni(fechaInicial.getTime());
                actividad.setFechaFin(fechaFin.getTime());


                String res =  controllerActividad.guardar(actividad, MainActivity.usuario, MenuProyectosActivity.proyecto);
                //if (controllerActividad.guardar(actividad, MainActivity.usuario, MenuProyectosActivity.proyecto)){
                    Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(this, "Error creando la actividad", Toast.LENGTH_SHORT).show();
//                }

            }else {
                Toast.makeText(this, "La fecha de fin es menor a la fecha inicial", Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     * Metodo para eliminar una actividad
     * @throws ParseException
     */
    public void editarActividad(View view) throws ParseException {
        String fechaIni = txtFechaInicio.getText().toString();
        String fechaFi = txtFechaFin.getText().toString();

        SimpleDateFormat fechas = new SimpleDateFormat("yyyy/MM/dd");
        Date inicio = fechas.parse(fechaIni);
        Date fin = fechas.parse(fechaFi);


        if (inicio.compareTo(fin)!=1){

            ControllerActividad controllerActividad = new ControllerActividad(this);

            Actividad actividad = new Actividad();


            Calendar fechaInicial = Calendar.getInstance();
            String[] datosfechaIni = txtFechaInicio.getText().toString().split("/");
            fechaInicial.set(Integer.parseInt(datosfechaIni[0]), Integer.parseInt(datosfechaIni[1]),
                    Integer.parseInt(datosfechaIni[2]));


            Calendar fechaFin = Calendar.getInstance();
            String[] datosFechaFin = txtFechaFin.getText().toString().split("/");
            fechaFin.set(Integer.parseInt(datosFechaFin[0]), Integer.parseInt(datosFechaFin[1]),
                    Integer.parseInt(datosFechaFin[2]));

            actividad.setNombre(txtNomber.getText().toString());
            actividad.setDescripcion(txtDescripcion.getText().toString());
            actividad.setFechaIni(fechaInicial.getTime());
            actividad.setFechaFin(fechaFin.getTime());

            if (controllerActividad.modificar(actividad, MainActivity.usuario, MenuProyectosActivity.proyecto)){
                Toast.makeText(this, "Se Ha modificado exitosamente la actividad", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error modificando la actividad", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "La fecha de fin es menor a la fecha inicial", Toast.LENGTH_SHORT).show();
        }
    }

    public void elimniarActividad(View view){
        controllerActividad.eliminar(actividad);
        Toast.makeText(this, "¡Se ha eliminado orrectamente!", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void cargarActividad(){
        txtDescripcion.setText(actividad.getDescripcion());
        txtNomber.setText(actividad.getNombre());
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        txtFechaFin.setText(format.format(actividad.getFechaFin()));
        txtFechaInicio.setText(format.format(actividad.getFechaIni()));
    }







}
