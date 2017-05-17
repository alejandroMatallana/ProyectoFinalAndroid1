package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import matallana.alejandro.proyectofinalandroid1.DAO.ProyectosIntegrantesDAO;

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
}
