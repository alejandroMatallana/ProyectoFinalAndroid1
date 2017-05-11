package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.List;

import matallana.alejandro.proyectofinalandroid1.DAO.CargoDAO;
import matallana.alejandro.proyectofinalandroid1.DAO.ProyectoDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Vista.CargoActivity;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuProyectosActivity;

/**
 * Created by sebastian,matallana,miguel on 10/05/17.
 */

public class ControllerCargo {

    CargoDAO cargoDAO;

    public ControllerCargo(Activity activity) {
        cargoDAO = new CargoDAO(activity);
    }

    public boolean guardar(Cargo cargo) {
        if (buscar(cargo.getNombre()) == null) {
            cargoDAO.guardar(cargo);
            return true;
        } else {
            return false;
        }
    }

    public Cargo buscar(String nombreCargo) {
        Cargo cargo = cargoDAO.buscar(nombreCargo, MenuProyectosActivity.proyecto.getId());
        if (cargo != null) {
            cargo.setProyecto(MenuProyectosActivity.proyecto);
            return cargo;
        } else {
            return null;
        }
    }

    /**
     * Metodo para eliminar un cargo
     *
     * @return
     */
    public boolean eliminar(Cargo cargo) {
        return cargoDAO.eliminar(cargo);
    }

    /**
     * Metodo para modificar un cargo
     *
     * @param nombre
     * @param descripcion
     * @param horario
     * @param salario
     * @return, el cargo modificado si se hizo satisfactoriamente
     */
  //  public boolean modificar(String nombre, String descripcion, String horario, double salario) {
//        if (nombre.equals(CargoActivity.cargo.getNombre())) {
///*            Cargo cargo = new Cargo(nombre, descripcion, horario, salario);
//            return cargoDAO.modificar(cargo);*/
//            return true;
//        } else {
//            Cargo cargo = new Cargo(nombre, descripcion, horario, salario);
//            return cargoDAO.modificar(cargo);
//        }


    //}

    public boolean modificar(String nombre, String descripcion, String horario, double salario){
        Cargo cargo = new Cargo(nombre,descripcion,horario,salario);
        return cargoDAO.modificar(cargo);
    }

    public List<Cargo> listar() {
        return cargoDAO.listar(MenuProyectosActivity.proyecto);
    }
}