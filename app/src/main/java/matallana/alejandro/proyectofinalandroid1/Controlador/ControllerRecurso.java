package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.DAO.RecursoDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Recurso;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuProyectosActivity;

/**
 * Created by miguel on 15/05/17.
 */

public class ControllerRecurso {

    private RecursoDAO recursoDAO;

    public ControllerRecurso(Activity activity) {
        recursoDAO = new RecursoDAO(activity);
    }

    public String crear(Recurso recurso) {
        if (buscar(recurso.getNombre()) != null) {
            return "El recurso ya existe";
        } else {
            if (recursoDAO.guardar(recurso)) {
                return "El recurso se registró";
            } else {
                return "Ocurrio un error al registrar el recurso";
            }
        }
    }

    public Recurso buscar(String nombre) {
        return recursoDAO.buscar(nombre);
    }

    public boolean editar(Recurso recurso) {
        return recursoDAO.editar(recurso);
    }

    public boolean eliminar(Recurso recurso) {
        return recursoDAO.eliminar(recurso);
    }

    public List<Recurso> listar() {
        return recursoDAO.listar(MenuProyectosActivity.proyecto);
    }

    public List<Recurso> listarRecursosIntegrante(Actividad actividad, Proyecto proyecto, int idUsuario) {
        return recursoDAO.listarRecursosIntegrante(actividad,proyecto,idUsuario);
    }
}
