package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.DAO.ComentarioDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.ComentarioTarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;

/**
 * Created by miguel on 24/05/17.
 */

public class ControllerComentario {

    private ComentarioDAO comentarioDAO;

    public ControllerComentario(Activity activity) {
        comentarioDAO = new ComentarioDAO(activity);
    }

    public boolean crear(ComentarioTarea comentarioTarea) {
        return comentarioDAO.guardar(comentarioTarea);
    }

    public List<ComentarioTarea> listar(Tarea tarea) {
        return comentarioDAO.listar(tarea);
    }
}