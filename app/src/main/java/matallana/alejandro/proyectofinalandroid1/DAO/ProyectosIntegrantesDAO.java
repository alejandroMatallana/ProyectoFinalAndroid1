package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;

/**
 * Created by sebastiancardona on 10/05/17.
 */

public class ProyectosIntegrantesDAO {

    private Conexion conex;

    public ProyectosIntegrantesDAO(Activity activity) {
        conex = new Conexion(activity);
    }

    /**
     * metodo para guardar un registro en la tabla ProyectosIntegrantes
     * @param cargo, id del cargo
     * @param proyecto, id del proyecto
     * @param usuario, id del usuario
     */
    public boolean guardar(int cargo, int proyecto, int usuario){
        ContentValues registro= new ContentValues();
        registro.put("idCargo", cargo);
        registro.put("idProyecto", proyecto);
        registro.put("idUsuario", usuario);
        return conex.insert("ProyectosIntegrantes", registro);
    }
}
