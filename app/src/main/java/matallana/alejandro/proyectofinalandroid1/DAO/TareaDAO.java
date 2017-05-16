package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;

import java.util.ArrayList;
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
        String consulta = "SELECT nombre,porcentajeDesarrollo,fechaInicio,fechaFinal FROM Tareas WHERE idProyecto="  ;
        /*Cursor temp = conex.search(consulta);
        if (temp.moveToFirst()){
            do {
                Cargo cargo = new Cargo(temp.getString(0),temp.getString(1),temp.getString(2),temp.getDouble(3));
                cargos.add(cargo);
            } while (temp.moveToNext());
        }*/
        return tareas;
    }

}
