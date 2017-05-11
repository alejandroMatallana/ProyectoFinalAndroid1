package matallana.alejandro.proyectofinalandroid1.Vista;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerProyecto;
import matallana.alejandro.proyectofinalandroid1.DAO.ProyectoDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.R;

public class GestionProyectoActivity extends AppCompatActivity {

    private LinearLayout layoutEdicion;
    private Button btnCrear;
    private EditText txtNombre, txtFechaInicio, txtFechaFin, txtEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_proyecto);
        layoutEdicion = (LinearLayout) findViewById(R.id.layoutEditarEliminar);
        btnCrear = (Button) findViewById(R.id.btnCrearProyecto);
        txtFechaFin = (EditText) findViewById(R.id.dateFinProyecto);
        txtFechaInicio = (EditText) findViewById(R.id.dateInicioProyecto);
        txtNombre = (EditText) findViewById(R.id.txtNombreProyecto);
        txtEstado = (EditText) findViewById(R.id.txtEstadoProyecto);

        txtFechaInicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                DatePickerDialog mDatePicker=new DatePickerDialog(GestionProyectoActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog mDatePicker=new DatePickerDialog(GestionProyectoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        txtFechaFin.setText(selectedyear+"/"+selectedmonth+"/"+selectedday);
                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });

        verificarSiCrearOEliminarEditar();
    }

    /**
     * metodo para crear un proyecto
     * @param view
     */
    public void crearProyecto(View view) throws ParseException {
        if(txtFechaInicio.getText().toString().equals("")
                || txtFechaFin.getText().toString().equals("")
                || txtNombre.getText().toString().equals("")){
            Toast.makeText(this, "Favor ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {

            String fechaIni = txtFechaInicio.getText().toString();
            String fechaFi = txtFechaFin.getText().toString();

            SimpleDateFormat fechas = new SimpleDateFormat("yyyy/MM/dd");
            Date inicio = fechas.parse(fechaIni);
            Date fin = fechas.parse(fechaFi);

            if (inicio.compareTo(fin)!=1){
                ControllerProyecto controllerProyecto = new ControllerProyecto(this);

                Calendar fechaInicial = Calendar.getInstance();
                String[] datosfechaIni = txtFechaInicio.getText().toString().split("/");
                fechaInicial.set(Integer.parseInt(datosfechaIni[0]), Integer.parseInt(datosfechaIni[1]),
                        Integer.parseInt(datosfechaIni[2]));

                Calendar fechaFin = Calendar.getInstance();
                String[] datosFechaFin = txtFechaFin.getText().toString().split("/");
                fechaFin.set(Integer.parseInt(datosFechaFin[0]), Integer.parseInt(datosFechaFin[1]),
                        Integer.parseInt(datosFechaFin[2]));

                String res = controllerProyecto.guardarProyecto(txtNombre.getText().toString(),
                        fechaInicial.getTime(),fechaFin.getTime());
                Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
                if(res.equals("El proyecto se ha guardado exitosamente")){
                    limpiarCampos();
                    finish();
                }

            } else {
                Toast.makeText(this, "La fecha de fin es menor a la fecha inicial",
                        Toast.LENGTH_SHORT).show();
            }


        }
    }

    /**
     * metodo para buscar un proyecto
     * @param view
     */
    public void buscarProyecto(View view){
        if(txtNombre.getText().toString().equals("")){
            Toast.makeText(this, "Favor ingresar el nombre del proyecto que desea buscar",
                    Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    /**
     * metodo para editar un proyecto
     * @param view
     */
    public void editarProyecto(View view) throws ParseException {
        if(txtFechaInicio.getText().toString().equals("")
                || txtFechaFin.getText().toString().equals("")
                || txtNombre.getText().toString().equals("")){
            Toast.makeText(this, "Error al editar: Favor ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {

            String fechaIni = txtFechaInicio.getText().toString();
            String fechaFi = txtFechaFin.getText().toString();

            SimpleDateFormat fechas = new SimpleDateFormat("yyyy/MM/dd");
            Date inicio = fechas.parse(fechaIni);
            Date fin = fechas.parse(fechaFi);

            if (inicio.compareTo(fin)!=1){
                ControllerProyecto controllerProyecto = new ControllerProyecto(this);

                Calendar fechaInicial = Calendar.getInstance();
                String[] datosfechaIni = txtFechaInicio.getText().toString().split("/");
                fechaInicial.set(Integer.parseInt(datosfechaIni[0]), Integer.parseInt(datosfechaIni[1]),
                        Integer.parseInt(datosfechaIni[2]));

                Calendar fechaFin = Calendar.getInstance();
                String[] datosFechaFin = txtFechaFin.getText().toString().split("/");
                fechaFin.set(Integer.parseInt(datosFechaFin[0]), Integer.parseInt(datosFechaFin[1]),
                        Integer.parseInt(datosFechaFin[2]));

                boolean res = controllerProyecto.modificarProyecto(txtNombre.getText().toString(),
                        fechaInicial.getTime(),fechaFin.getTime(),Double.parseDouble(txtEstado.getText().toString()));
                if(res){
                    Toast.makeText(this, "Modificado con exito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error al modificar", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "La fecha de fin es menor a la fecha inicial",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * metodo para eliminar un proyecto
     * @param view
     */
    public void eliminarProyecto(View view){
        if(txtNombre.getText().toString().equals("")){
            Toast.makeText(this, "Error al eliminar: Favor ingresar el nombre del proyecto que desea eliminar",
                    Toast.LENGTH_SHORT).show();
        } else {
            String nombre = txtNombre.getText().toString();
            if (nombre!=null){
                ControllerProyecto controllerProyecto = new ControllerProyecto(this);
                controllerProyecto.eliminar(txtNombre.getText().toString());
                Toast.makeText(this, "Exito, se ha eliminado correctamente",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al eliminar: No se encuentra ningun proyecto con este nombre",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * metodo que verifica si se va a crear un proyecto o se va a editar.
     * dependiendo de eso se habilitan unos campos y se ocultan otros
     */
    public void verificarSiCrearOEliminarEditar(){
        if(MenuProyectosActivity.proyecto == null){
            ocultarBotonesEdicion();
        } else {
            habilitarBotonesEdicion();
            Proyecto pro = MenuProyectosActivity.proyecto;
            txtNombre.setText(pro.getNombre());
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            txtFechaFin.setText(format.format(pro.getFechaFin()));
            txtFechaInicio.setText(format.format(pro.getFechaInicio()));
            txtEstado.setText(String.valueOf(pro.getEtapa()));
        }
    }

    public void limpiarCampos(){
        txtNombre.setText("");
        txtFechaFin.setText("");
        txtFechaInicio.setText("");
    }

    /**
     * metodo para ocultar los botones de Editar, Eliminar y CancelarEdicion
     */
    public void ocultarBotonesEdicion(){
        layoutEdicion.setVisibility(View.INVISIBLE);
        btnCrear.setVisibility(View.VISIBLE);
        txtNombre.setEnabled(true);
    }

    /**
     * metodo para volver visibles los botones de Editar, Eliminar y CancelarEdicion
     */
    public void habilitarBotonesEdicion(){
        layoutEdicion.setVisibility(View.VISIBLE);
        btnCrear.setVisibility(View.INVISIBLE);
        txtNombre.setEnabled(false);
    }

}
