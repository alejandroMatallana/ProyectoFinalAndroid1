package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.Date;

import matallana.alejandro.proyectofinalandroid1.DAO.ProyectoDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;

/**
 * Created by sebastiancardona on 9/05/17.
 */

public class ControllerProyecto {

    ProyectoDAO proyectoDAO;

    public ControllerProyecto (Activity activity) {
        proyectoDAO = new ProyectoDAO(activity);
    }


    public boolean modificarProyecto(String nombre, Date fechaInicion, Date fechaFin, double estado){
      //  Proyecto proyecto = new Proyecto(nombre,fechaInicion,fechaFin,estado);
       // return proyectoDAO.modificar(proyecto);
        return false;
    }

    public  boolean eliminar(String nombre){
       // Proyecto proyecto = new Proyecto(nombre,"","",0);
        //return proyectoDAO.eliminar(proyecto);
        return false;
    }

}
