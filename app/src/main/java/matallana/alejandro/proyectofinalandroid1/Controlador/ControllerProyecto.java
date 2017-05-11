package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import matallana.alejandro.proyectofinalandroid1.DAO.ProyectoDAO;
import matallana.alejandro.proyectofinalandroid1.DAO.ProyectosIntegrantesDAO;
import matallana.alejandro.proyectofinalandroid1.DAO.UsuarioDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;

/**
 * Created by sebastiancardona on 9/05/17.
 */

public class ControllerProyecto {

    private Activity activity;

    public ControllerProyecto (Activity activity) {
        this.activity = activity;
    }

    /**
     * metodo para guardar un nuevo proyecto
     * @param nombre
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public String guardarProyecto(String nombre, Date fechaInicio, Date fechaFin){
        ProyectoDAO proyectoDAO = new ProyectoDAO(activity);
        Proyecto p = proyectoDAO.buscarIdProyecto(nombre);
        if(p != null){
            return "Señor usuario, ya hay un proyecto registrado con ese nombre";
        } else {
            Proyecto proyecto = new Proyecto(nombre,fechaInicio,fechaFin,0);
            boolean res = proyectoDAO.guardar(proyecto);
            //si retorna true, osea si guardó correctamente
            if (res){
                Proyecto proyectoGuardado = proyectoDAO.obtenerIdUltimoProyecto();
                ProyectosIntegrantesDAO piDAO = new ProyectosIntegrantesDAO(activity);
                //guarda la respuesta del guardar, es true si se guardo el registro o false si no
                boolean res2 = piDAO.guardar(1,proyectoGuardado.getId(), UsuarioDAO.IDUsuarioLogueado);
                //si el false
                if(!res2){
                    return "Hubo un problema guardando";
                } else {
                    proyectoDAO.cerrarConexionBaseDeDatos();
                    return "El proyecto se ha guardado exitosamente";
                }
            } else {
                proyectoDAO.cerrarConexionBaseDeDatos();
                return "Hubo un problema guardando";
            }
        }
    }

    public boolean modificarProyecto(String nombre, Date fechaInicio, Date fechaFin, double estado){
        ProyectoDAO dao = new ProyectoDAO(activity);
        Proyecto proyecto = new Proyecto(nombre,fechaInicio,fechaFin,estado);
        return dao.modificar(proyecto);
    }

    public  boolean eliminar(String nombre){

        return false;
    }

    /**
     * metodo para listar proyectos de la persona que esté logueada
     * @return
     */
    public ArrayList<Proyecto> listarProyectos(){
        ProyectoDAO dao = new ProyectoDAO(activity);
        return dao.listar();
    }

}
