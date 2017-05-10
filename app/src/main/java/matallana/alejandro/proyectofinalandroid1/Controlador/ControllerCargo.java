package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import matallana.alejandro.proyectofinalandroid1.DAO.CargoDAO;
import matallana.alejandro.proyectofinalandroid1.DAO.ProyectoDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;

/**
 * Created by sebastian,matallana,miguel on 10/05/17.
 */

public class ControllerCargo {

    CargoDAO cargoDAO;
    ProyectoDAO proyectoDAO;

    public ControllerCargo(Activity activity){
        cargoDAO = new CargoDAO(activity);
        proyectoDAO = new ProyectoDAO(activity);
    }

    public boolean guardar(Cargo cargo, String nombreProyecto) {
        Proyecto proyecto = proyectoDAO.buscar(nombreProyecto);
        if (proyecto != null) {
            cargoDAO.guardar(cargo,proyecto.getId());
            return true;
        }
        return false;
    }
}
