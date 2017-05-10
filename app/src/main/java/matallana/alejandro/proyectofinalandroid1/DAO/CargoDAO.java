package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;

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

    /**
     * Metodo para modificar un cargo
     * @param cargo
     * @return
     */
    public boolean modificar(Cargo cargo) {
        String tabla = "Cargos" ;
        String condicion = "nombre=" +  cargo.getNombre();
        ContentValues registro = new ContentValues();
        registro.put("descripcion", cargo.getDescripcion());
        registro.put("horario", cargo.getHorario());
        registro.put("salario",cargo.getSalario());
        registro.put("idProyecto", cargo.getIdProyecto().getNombre()); // Aca va la foranea del id del proyecto
        return  conex.update(tabla,condicion,registro);

    }

    public Cargo buscar(String nombreCargo) {
        return null;
    }

    /**
     * Metodo para eliminar un cargo
     * @param cargo
     * @return
     */
    public boolean eliminar(Cargo cargo) {
        String tabla = "Cargos" ;
        String condicion = "nombre=" +  cargo.getNombre();
        return conex.delete(tabla,condicion);
    }
}