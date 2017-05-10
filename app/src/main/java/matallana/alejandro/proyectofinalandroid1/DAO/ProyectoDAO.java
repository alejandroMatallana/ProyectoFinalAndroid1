package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        String condicion = "nombre=" + p.getNombre();
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
                "from Proyectos p JOIN ProyectosIntegrantes pi on p.id=pi.idProyecto" +
                "where p.nombre=" + nombre + " and pi.idUsuario="+UsuarioDAO.IDUsuarioLogueado;

        Cursor temp = conex.search(consulta);


        return p;
    }

}
