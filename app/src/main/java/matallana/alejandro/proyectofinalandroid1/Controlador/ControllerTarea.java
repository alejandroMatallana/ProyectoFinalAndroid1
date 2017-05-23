package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.DAO.ActividadDAO;
import matallana.alejandro.proyectofinalandroid1.DAO.ProyectoDAO;
import matallana.alejandro.proyectofinalandroid1.DAO.TareaDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuActividadesActivity;

/**
 * Created by miguel on 15/05/17.
 */

public class ControllerTarea {

    private TareaDAO tareaDAO;
    private Activity activity;

    public ControllerTarea(Activity activity) {
        this.activity = activity;
        tareaDAO = new TareaDAO(activity);
    }

    public Tarea buscar(String nombreTarea) {
        return tareaDAO.buscar(nombreTarea, MenuActividadesActivity.actividad);
    }

    public String editar(Tarea tarea) {
        if (tarea.getFechaInicio().compareTo(MenuActividadesActivity.actividad.getFechaIni()) >= 0) {
            if (tarea.getFechaInicio().compareTo(MenuActividadesActivity.actividad.getFechaFin()) < 0) {
                if (tarea.getFechaFinal().compareTo(tarea.getFechaInicio()) > 0) {
                    if (tarea.getFechaFinal().compareTo(MenuActividadesActivity.actividad.getFechaFin()) <= 0) {
                        if (tarea.getPorcentaje() <= 100) {
                            if (tareaDAO.editar(tarea)) {
                                actualizarDesarrolloProyecto(MenuActividadesActivity.actividad.getProyecto());
                                return "Se edito la tarea";
                            } else {
                                return "Error en la edicion de la tarea";
                            }
                        } else {
                            return "El porcentaje no debe sobrepasar el 100%";
                        }
                    } else {
                        return "La fecha final de la tarea debe ser menor o igual a la fecha" +
                                " final de la actividad";
                    }
                } else {
                    return "La fecha final debe ser mayor a la fecha inicial";
                }
            } else {
                return "La fecha inicial debe ser menor a la fecha final de la Actividad";
            }
        } else {
            return "La fecha inicial debe ser mayor o igual a la fecha inicial de la Actividad";
        }
    }

    public String crear(Tarea tarea) {
        if (tarea.getFechaInicio().compareTo(MenuActividadesActivity.actividad.getFechaIni()) >= 0) {
            if (tarea.getFechaInicio().compareTo(MenuActividadesActivity.actividad.getFechaFin()) < 0) {
                if (tarea.getFechaFinal().compareTo(tarea.getFechaInicio()) > 0) {
                    if (tarea.getFechaFinal().compareTo(MenuActividadesActivity.actividad.getFechaFin()) <= 0) {
                        if (tarea.getPorcentaje() <= 100) {
                            if (tareaDAO.crear(tarea, MenuActividadesActivity.actividad)) {
                                actualizarDesarrolloProyecto(MenuActividadesActivity.actividad.getProyecto());
                                return "Se registro la tarea";
                            } else {
                                return "Error en el registro de la tarea";
                            }
                        } else {
                            return "El porcentaje no debe sobrepasar el 100%";
                        }
                    } else {
                        return "La fecha final de la tarea debe ser menor o igual a la fecha" +
                                " final de la actividad";
                    }
                } else {
                    return "La fecha final debe ser mayor a la fecha inicial";
                }
            } else {
                return "La fecha inicial debe ser menor a la fecha final de la Actividad";
            }
        } else {
            return "La fecha inicial debe ser mayor o igual a la fecha inicial de la Actividad";
        }
    }

    public boolean eliminar(Tarea tarea) {
        return tareaDAO.eliminar(tarea);
    }

    public List<Tarea> listar() {
        return tareaDAO.listar(MenuActividadesActivity.actividad);
    }

    /**
     * metodo para actualizar el estado de desarrollo del proyecto al que pertenece
     * una tarea
     * @param proyecto, el proyecto al que pertenece la tarea
     */
    public void actualizarDesarrolloProyecto(Proyecto proyecto){
        double porcentaje = 0;
        ActividadDAO actividadDAO = new ActividadDAO(activity);
        ProyectoDAO proyectoDao = new ProyectoDAO(activity);
        List<Actividad> actividades = actividadDAO.listaActividades(proyecto);
        for (int i = 0; i < actividades.size(); i++) {
            porcentaje = porcentaje + tareaDAO.desarolloDeLasTareas(actividades.get(i));
        }
        System.out.println("sin dividri....."+porcentaje);
        porcentaje = porcentaje / actividades.size();
        System.out.println("dividiendo....."+porcentaje);
        proyecto.setEtapa(porcentaje);
        proyectoDao.modificar(proyecto);
    }
}