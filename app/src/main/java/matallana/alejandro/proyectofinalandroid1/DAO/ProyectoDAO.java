package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;

/**
 * Created by sebastiancardona on 9/05/17.
 */

public class ProyectoDAO {

    Conexion conex;

    public ProyectoDAO(Activity activity) {
        conex = new Conexion(activity);
    }
}
