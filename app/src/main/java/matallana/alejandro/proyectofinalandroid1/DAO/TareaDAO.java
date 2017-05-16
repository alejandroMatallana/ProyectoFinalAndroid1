package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;

/**
 * Created by miguel on 15/05/17.
 */

public class TareaDAO {

    Conexion conexion;

    public TareaDAO(Activity activity) {
        conexion = new Conexion(activity);
    }

    public List<Tarea> listar() {
        List<Tarea> tareas = new ArrayList<>();
        String consulta = "SELECT nombre,porcentajeDesarrollo,fechaInicio,fechaFinal FROM Tareas WHERE idActividad="  ;
        Cursor temp = conexion.search(consulta);
        if (temp.moveToFirst()){
            do {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                Date fechaInicio = new Date();
                Date fechaFinal = new Date();
                try {
                    fechaInicio = format.parse(temp.getString(2));
                    fechaFinal = format.parse(temp.getString(3));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Tarea tarea = new Tarea(temp.getString(0),temp.getInt(1),fechaInicio,fechaFinal);
                tareas.add(tarea);
            } while (temp.moveToNext());
        }
        return tareas;
    }

}
