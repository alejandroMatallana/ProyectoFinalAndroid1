package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.DAO.TareaDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuActividadesActivity;

/**
 * Created by miguel on 15/05/17.
 */

public class ControllerTarea {

    private TareaDAO tareaDAO;

    public ControllerTarea(Activity activity) {
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
}