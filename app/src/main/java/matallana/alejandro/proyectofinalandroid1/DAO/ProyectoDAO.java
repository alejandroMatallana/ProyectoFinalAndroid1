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

/**
 * Created by sebastiancardona on 9/05/17.
 */

public class ProyectoDAO {

    Conexion conex;

    public ProyectoDAO(Activity activity) {
        conex = new Conexion(activity);
    }

    /**
     * Metodo para guardar un proyecto en la base de datos
     * @param p, proyecto que será guardado
     * @return true si se guardó el proyecto
     */
    public  boolean guardar (Proyecto p){
        //Objeto que cotendra la informacion a almacenar
        Date fechaInicio = p.getFechaInicio();
        Date fechaFin = p.getFechaFin();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues registro= new ContentValues();
        registro.put("nombre", p.getNombre());
        registro.put("fechaInicio", format.format(fechaInicio));
        registro.put("fechaFinal", format.format(fechaFin));
        registro.put("etapa", p.getEtapa());
        return conex.insert("Proyectos", registro);
    }

    /**
     * Metodo para editar un proyecto
     * @param p, proyecto que será editado
     * @return true si se editó el proyecto con exito
     */
    public  boolean modificar (Proyecto p){
        String tabla = "Proyectos";
        String condicion = "nombre='" + p.getNombre() + "'";
        Date fechaInicio = p.getFechaInicio();
        Date fechaFin = p.getFechaFin();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        ContentValues registro = new ContentValues();
        registro.put("nombre", p.getNombre());
        registro.put("fechaInicio", format.format(fechaInicio));
        registro.put("fechaFinal", format.format(fechaFin));
        registro.put("etapa", p.getEtapa());
        return conex.update(tabla,condicion,registro);
    }

    /**
     * metodo para buscar un proyecto por su nombre
     * @param nombre, nombre del proyecto que se va a buscar
     * @return un proyecto encontrado por ese nombre
     */
    public Proyecto buscar (String nombre){
        Proyecto p = null;
        String consulta = "select p.id,p.nombre,p.fechaInicio,p.fechaFinal,p.etapa " +
                "from Proyectos p JOIN ProyectosIntegrantes pi on p.id=pi.idProyecto " +
                "where p.nombre=" + nombre + " and pi.idUsuario="+UsuarioDAO.IDUsuarioLogueado;

        Cursor temp = conex.search(consulta);

        if (temp.getCount()>0){
            p = new Proyecto();
            temp.moveToFirst();
            p.setId(temp.getInt(0));
            p.setNombre(temp.getString(1));
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try {
                p.setFechaInicio(format.parse(temp.getString(2)));
                p.setFechaFin(format.parse(temp.getString(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            p.setEtapa(temp.getDouble(4));
        }
        return p;
    }

    /**
     * metodo para buscar el id y solo el id del ultimo proyecto registrado
     * @return el id del ultimo proyecto registrado
     */
    public Proyecto obtenerIdUltimoProyecto (){
        Proyecto p = null;
        String consulta = "SELECT max(id) FROM Proyectos";

        Cursor temp = conex.search(consulta);

        if (temp.getCount()>0){
            p = new Proyecto();
            temp.moveToFirst();
            p.setId(temp.getInt(0));
            System.out.println(temp.getInt(0));
        }
        return p;
    }

    /**
     * metodo para buscar el id y solo el id de un proyecto por su nombre
     * @param nombre, nombre del proyecto que se va a buscar
     * @return el id de un proyecto encontrado por ese nombre
     */
    public Proyecto buscarIdProyecto (String nombre){
        Proyecto p = null;
        String consulta = "SELECT Proyectos.id " +
                "FROM Proyectos JOIN ProyectosIntegrantes ON Proyectos.id=ProyectosIntegrantes.idProyecto " +
                "WHERE Proyectos.nombre='" + nombre + "' AND ProyectosIntegrantes.idUsuario="+UsuarioDAO.IDUsuarioLogueado;

        Cursor temp = conex.search(consulta);

        if (temp.getCount()>0){
            p = new Proyecto();
            temp.moveToFirst();
            p.setId(temp.getInt(0));
            System.out.println(temp.getInt(0));
        }
        return p;
    }

    /**
     * metodo para eliminar un proyecto
     * @param p, proyecto que será eliminado
     * @return true si el proyecto fue eliminado
     */
    public boolean eliminar (Proyecto p){
        String tabla = "Proyectos";
        String condicion = "nombre=" + p.getNombre();
        return conex.delete(tabla,condicion);
    }


    /**
     * metodo para listar los proyectos del usuario que esté logueado
     * @return
     */
    public ArrayList<Proyecto> listar(){
        ArrayList<Proyecto> lista = new ArrayList<>();
        String consulta = "SELECT p.id,p.nombre,p.fechaInicio,p.fechaFinal,p.etapa " +
                "FROM Proyectos p JOIN ProyectosIntegrantes pi on p.id=pi.idProyecto " +
                "WHERE pi.idUsuario="+UsuarioDAO.IDUsuarioLogueado + " ORDER BY p.etapa";
        Cursor temp = conex.search(consulta);
        if (temp.moveToFirst()){
            do {
                Date fechaInicio = null;
                Date fechaFin = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    fechaInicio = format.parse(temp.getString(2));
                    fechaFin = format.parse(temp.getString(3));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Proyecto p = new Proyecto(temp.getInt(0),temp.getString(1),fechaInicio,fechaFin,temp.getDouble(4));
                lista.add(p);
            } while (temp.moveToNext());
        }
        return lista;
    }

    /**
     * metodo para verificar si ya se registro un integrante al proyecto
     * @param idProyecto, id del proyecto
     * @return true si ya hay un integrante registrado al proyecto
     */
    public boolean existeEnProyectosIntegrantes(int idProyecto){
        String consulta = "SELECT count(p.id) FROM ProyectosIntegrantes AS p WHERE p.idProyecto="+idProyecto;
        Cursor temp = conex.search(consulta);
        if (temp.getCount()>0){
            temp.moveToFirst();
            if(temp.getInt(0) > 1){
                return true;
            }
        }
        return false;
    }

    public void cerrarConexionBaseDeDatos(){
        conex.cerrarConexion();
    }

}
