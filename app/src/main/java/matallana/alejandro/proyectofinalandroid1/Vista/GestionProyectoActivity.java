package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerProyecto;
import matallana.alejandro.proyectofinalandroid1.DAO.ProyectoDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.R;

public class GestionProyectoActivity extends AppCompatActivity {

    private LinearLayout layoutEdicion;
    private Button btnCrear;
    private EditText txtNombre, txtFechaInicio, txtFechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_proyecto);
        layoutEdicion = (LinearLayout) findViewById(R.id.layoutEditarEliminar);
        btnCrear = (Button) findViewById(R.id.btnCrearProyecto);
        txtFechaFin = (EditText) findViewById(R.id.dateFinProyecto);
        txtFechaInicio = (EditText) findViewById(R.id.dateInicioProyecto);
        txtNombre = (EditText) findViewById(R.id.txtNombreProyecto);
        verificarSiCrearOEliminarEditar();
    }

    /**
     * metodo para crear un proyecto
     * @param view
     */
    public void crearProyecto(View view){
        if(txtFechaInicio.getText().toString().equals("")
                || txtFechaFin.getText().toString().equals("")
                || txtNombre.getText().toString().equals("")){
            Toast.makeText(this, "Favor ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {

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
    public void editarProyecto(View view){
        if(txtFechaInicio.getText().toString().equals("")
                || txtFechaFin.getText().toString().equals("")
                || txtNombre.getText().toString().equals("")){
            Toast.makeText(this, "Error al editar: Favor ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {
//            if (txtNombre.getText().toString()== proyecto.getNombre()){
//                Calendar fechaInicio = Calendar.getInstance();
//                Calendar fechaFin = Calendar.getInstance();
//                String[] datos = txtFechaInicio.getText().toString().split("/");
//                String[] datos2 = txtFechaFin.getText().toString().split("/");
//                fechaInicio.set(Integer.parseInt(datos[0]), (Integer.parseInt(datos[1]))-1, Integer.parseInt(datos[2]));
//                fechaInicio.set(Integer.parseInt(datos2[0]), (Integer.parseInt(datos2[1]))-1, Integer.parseInt(datos2[2]));
//                proyecto.setFechaInicio(fechaInicio.getTime());
//                proyecto.setFechaFin(fechaFin.getTime());
//
//            }else {
//
//            }



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
        txtNombre.setClickable(true);
    }

    /**
     * metodo para volver visibles los botones de Editar, Eliminar y CancelarEdicion
     */
    public void habilitarBotonesEdicion(){
        layoutEdicion.setVisibility(View.VISIBLE);
        btnCrear.setVisibility(View.INVISIBLE);
        txtNombre.setClickable(false);
    }

}
