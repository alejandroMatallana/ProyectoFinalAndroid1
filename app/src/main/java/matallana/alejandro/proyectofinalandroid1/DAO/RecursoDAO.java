package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Recurso;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuActividadesActivity;

/**
 * Created by miguel on 15/05/17.
 */

public class RecursoDAO {

    Conexion conexion;

    public RecursoDAO(Activity activity) {
        conexion = new Conexion(activity);
    }

    public boolean guardar(Recurso recurso) {
        ContentValues registro= new ContentValues();
        registro.put("nombre",recurso.getNombre().toUpperCase());
        registro.put("cantidad", recurso.getCantidad());
        registro.put("descripcion",recurso.getDescripcion());
        registro.put("ubicacion", recurso.getUbicacion());
        registro.put("idProyecto", recurso.getProyecto().getId());
        return conexion.insert("Recursos",registro);
    }

    public Recurso buscar(String nombre) {
        String consulta = "SELECT id,cantidad,descripcion,ubicacion " +
                " FROM Recursos WHERE nombre='" + nombre.toUpperCase() + "'";
        Cursor temp = conexion.search(consulta);
        if (temp.getCount()>0){
            Recurso recurso = new Recurso();
            temp.moveToFirst();
            recurso.setNombre(nombre.toUpperCase());
            recurso.setId(temp.getInt(0));
            recurso.setCantidad(temp.getInt(1));
            recurso.setDescripcion(temp.getString(2));
            recurso.setUbicacion(temp.getString(3));
            return recurso;
        }
        conexion.cerrarConexion();
        return null;
    }

    public boolean editar(Recurso recurso) {
        String tabla = "Recursos";
        String condicion = "nombre='" + recurso.getNombre() + "'";
        ContentValues registro= new ContentValues();
        registro.put("nombre",recurso.getNombre().toUpperCase());
        registro.put("cantidad", recurso.getCantidad());
        registro.put("descripcion",recurso.getDescripcion());
        registro.put("ubicacion", recurso.getUbicacion());
        return conexion.update(tabla,condicion,registro);
    }

    public boolean eliminar(Recurso recurso) {
        String tabla = "Recursos";
        String condicion = "id=" + recurso.getId();
        return conexion.delete(tabla,condicion);
    }

    public List<Recurso> listar(Proyecto proyecto) {
        List<Recurso> recursos = new ArrayList<>();
        String consulta = "SELECT id,nombre,cantidad,descripcion,ubicacion" +
                " FROM Recursos WHERE idProyecto=" + proyecto.getId();
        Cursor temp = conexion.search(consulta);
        if (temp.moveToFirst()){
            do {
                Recurso recurso = new Recurso(temp.getString(1),temp.getInt(2),temp.getString(3),temp.getString(4),proyecto);
                recurso.setId(temp.getInt(0));
                recursos.add(recurso);
            } while (temp.moveToNext());
        }
        return recursos;
    }
}
