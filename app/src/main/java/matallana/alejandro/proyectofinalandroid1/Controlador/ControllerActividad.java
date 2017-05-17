package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.DAO.ActividadDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.Vista.MainActivity;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuProyectosActivity;

/**
 * Created by AlejandroM on 15/05/2017.
 */
public class ControllerActividad {


    ActividadDAO actividadDAO;

    public  ControllerActividad(Activity activity){
        actividadDAO = new ActividadDAO(activity);
    }


    /**
     * Metodo para guardar una actividad
     * @param actividad
     * @return
     */
    public String guardar(Actividad actividad, Usuario usuario, Proyecto proyecto){
        Actividad a = actividadDAO.buscarNombre(actividad.getNombre());
        if (a!=null){
            return "Señor usuario ya hay una actividad creada con este nombre";
        } else {
            if (actividad.getNombre() != null) {
                System.out.println(proyecto.getNombre() + usuario.getNombres());
                System.out.println(proyecto.getId() + usuario.getId());
                actividadDAO.guardar(actividad, usuario, proyecto);
                return "La actividad se ha registrado exitosamente";
            } else {
                return "Se ha generado un problema al crear la actividad";
            }
        }
    }

    /**
     * Metodo para buscar una actividad
     * @param nombre
     * @return
     */
    public boolean buscar(String nombre){
        Actividad actividad = actividadDAO.buscar(nombre, MainActivity.usuario, MenuProyectosActivity.proyecto);
        if (actividad!=null){

            return true;

        }else {
            return false;
        }
    }

    /**
     * Metodo para modificar una actividad
     * @param actividad
     * @return
     */
    public boolean modificar (Actividad actividad, Usuario usuario, Proyecto proyecto){
        if (actividad.getNombre()!=null){
            return  actividadDAO.modificar(actividad, usuario, proyecto);
        }else {
            return false;
        }

    }

    /**
     * MEtodo para eliminar una actividad
     * @param actividad
     * @return
     */
    public boolean eliminar (Actividad actividad){
        return actividadDAO.eliminar(actividad);
    }


    /**
     * Metodo para listar las actividades
     * @return
     */
    public List<Actividad> listar(){
        return  actividadDAO.listaActividades(MainActivity.usuario,MenuProyectosActivity.proyecto);
    }

}
