package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;

/**
 * Created by miguel on 15/05/17.
 */

public class TareaDAO {

    Conexion conexion;

    public TareaDAO(Activity activity) {
        conexion = new Conexion(activity);
    }



}
