package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.ArrayList;

import matallana.alejandro.proyectofinalandroid1.DAO.ProyectosIntegrantesDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.ProyectosIntegrantes;

/**
 * Created by sebastiancardona on 17/05/17.
 */

public class ControllerListaIntegrantesActivity {

    private Activity activity;

    /**
     * Constructor...
     * @param activity
     */
    public ControllerListaIntegrantesActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * metodo para listar los integrantes de un proyecto especifico
     * @param idProyecto, id del proyecto del que se quieren obtener los integrantes
     * @return una lista con los integrantes del proyecto que tiene como id idProyecto
     */
    public ArrayList<ProyectosIntegrantes> listarIntegrantesDelProyecto(int idProyecto){
        ProyectosIntegrantesDAO dao = new ProyectosIntegrantesDAO(activity);
        return dao.listar(idProyecto);
    }
}
