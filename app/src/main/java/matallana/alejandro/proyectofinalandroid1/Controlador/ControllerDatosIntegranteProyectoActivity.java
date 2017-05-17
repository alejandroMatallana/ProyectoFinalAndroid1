package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.ArrayList;

import matallana.alejandro.proyectofinalandroid1.DAO.CargoDAO;
import matallana.alejandro.proyectofinalandroid1.DAO.ProyectosIntegrantesDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;

/**
 * Created by sebastiancardona on 17/05/17.
 */

public class ControllerDatosIntegranteProyectoActivity {

    private Activity activity;

    /**
     * Constructor...
     * @param activity
     */
    public ControllerDatosIntegranteProyectoActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * metodo para verificar si un usuario es integrante de un proyecto
     * @param usuario, id del usuario
     * @param proyecto, id del proyecto
     * @return  true si el usuario si es integrante del proyecto, false de lo contrario
     */
    public boolean esUsuarioUnIntegrante(int usuario, int proyecto){
        ProyectosIntegrantesDAO dao = new ProyectosIntegrantesDAO(activity);
        return dao.buscarUsuarioProyecto(usuario,proyecto);
    }

    /**
     * metodo para listar los cargos del proyecto
     * @param proyecto, proyecto del que se listaran los cargos
     * @return una lista con los cargos del proyecto
     */
    public ArrayList<Cargo> listarCargosDelProyecto(Proyecto proyecto){
        CargoDAO dao = new CargoDAO(activity);
        ArrayList<Cargo> list = (ArrayList) dao.listar(proyecto);
        return list;
    }

    /**
     * metodo para agregar un usuario como integrante a un proyecto con un cargo especifico
     * @param cargo, id del cargo
     * @param usuario, id del usuario
     * @param proyecto, id del proyecto
     * @return true si se guardo el registro, false de lo contrario
     */
    public boolean agregarIntegrante(int cargo, int usuario, int proyecto){
        ProyectosIntegrantesDAO dao = new ProyectosIntegrantesDAO(activity);
        return dao.guardar(cargo,proyecto,usuario);
    }

    /**
     * metodo para eliminar un integrante de un proyecto
     * @param usuario, id del usuario (integrante)
     * @param proyecto, id del proyecto
     * @return true si se eliminó el proyecto, false de lo contrario
     */
    public boolean quitarIntegrante(int usuario, int proyecto){
        ProyectosIntegrantesDAO dao = new ProyectosIntegrantesDAO(activity);
        return dao.eliminar(usuario,proyecto);
    }
}
