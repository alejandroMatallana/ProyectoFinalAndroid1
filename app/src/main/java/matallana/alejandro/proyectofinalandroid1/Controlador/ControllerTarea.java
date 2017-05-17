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

    public String crear(Tarea tarea) {
        if (tarea.getFechaInicio().compareTo(MenuActividadesActivity.actividad.getFechaIni()) >= 0) {
            if (tarea.getFechaInicio().compareTo(MenuActividadesActivity.actividad.getFechaFin()) < 0) {
                if (tarea.getFechaFinal().compareTo(tarea.getFechaInicio()) > 0) {
                    if (tarea.getFechaFinal().compareTo(MenuActividadesActivity.actividad.getFechaFin()) <= 0) {
                        if (tareaDAO.crear(tarea, MenuActividadesActivity.actividad)) {
                            return "Se registro la tarea";
                        } else {
                            return "Error en el registro de la tarea";
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

    public List<Tarea> listar() {
        return tareaDAO.listar(MenuActividadesActivity.actividad);
    }
}