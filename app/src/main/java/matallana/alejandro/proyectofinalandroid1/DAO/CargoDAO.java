package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;

/**
 * Created by sebastian,matallana,miguel on 9/05/17.
 */

public class CargoDAO {

    Conexion conex;

    public CargoDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Cargo cargo) {
        return true;
    }

    public boolean modificar(Cargo cargo) {
        return true;
    }

    public Cargo buscar(String nombreCargo) {
        return null;
    }

    public boolean eliminar(Cargo usuario) {
        return true;
    }
}