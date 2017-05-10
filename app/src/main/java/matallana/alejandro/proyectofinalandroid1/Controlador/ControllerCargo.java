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

    /**
     * Metodo para eliminar un cargo
     * @param nombre, el nombre del cargo que se va a eliminar
     * @return
     */
    public boolean eliminar (String nombre){
        Cargo cargo = new Cargo(nombre,"","",0); // Aca faltaria la foranea al final
        return cargoDAO.eliminar(cargo);

    }

    /**
     * Metodo para modificar un cargo
     * @param nombre
     * @param descripcion
     * @param horario
     * @param salario
     * @param nomProyecto
     * @return, el cargo modificado si se hizo satisfactoriamente
     */
    public boolean modificar(String nombre, String descripcion, String horario, double salario, String nomProyecto){
        Proyecto proyecto = proyectoDAO.buscar(nomProyecto);
        Cargo cargo = new Cargo(nombre,descripcion,horario,salario);// Aca faltaria poner el proyecto
        return  cargoDAO.modificar(cargo);

    }
}
