package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.DAO.TareasRecursosDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Recurso;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.TareasRecursos;

/**
 * Created by miguel on 23/05/17.
 */

public class ControllerTareasRecursos {

    private TareasRecursosDAO tareasRecursosDAO;

    public ControllerTareasRecursos(Activity activity) {
        this.tareasRecursosDAO = new TareasRecursosDAO(activity);
    }

    public boolean crear(TareasRecursos tareasRecursos) {
        return tareasRecursosDAO.crear(tareasRecursos);
    }

    public boolean eliminar(TareasRecursos tareasRecursos) {
        return tareasRecursosDAO.eliminar(tareasRecursos);
    }

    public List<Tarea> listarTareas(Proyecto proyecto) {
        return tareasRecursosDAO.listarTareas(proyecto);
    }

    public List<TareasRecursos> listarTareasConRecursos(Proyecto proyecto, Recurso recurso) {
        return tareasRecursosDAO.listarTareasConRecursos(proyecto,recurso);
    }
}
