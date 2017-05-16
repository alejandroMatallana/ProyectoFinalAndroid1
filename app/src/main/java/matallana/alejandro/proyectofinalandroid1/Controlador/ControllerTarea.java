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

    TareaDAO tareaDAO;

    public ControllerTarea(Activity activity) {
        tareaDAO = new TareaDAO(activity);
    }

    public Tarea buscar(String nombreTarea) {
        return tareaDAO.buscar(nombreTarea, MenuActividadesActivity.actividad);
    }

    public List<Tarea> listar() {
        return tareaDAO.listar(MenuActividadesActivity.actividad);
    }
}