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
import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.Vista.ListaProyectoActivity;
import matallana.alejandro.proyectofinalandroid1.Vista.MainActivity;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuProyectosActivity;

/**
 * Created by AlejandroM on 15/05/2017.
 */
public class ActividadDAO {


    Conexion conex;
    public static int IDUsuarioLogueado = 0;

    public ActividadDAO(Activity activity) {
        conex = new Conexion(activity);
    }

    /**
     * Metodo para guardar una actividad
     * @param actividad
     * @return
     */
    public boolean guardar(Actividad actividad, Usuario usuario, Proyecto proyecto){

        Date fechaInicio = actividad.getFechaIni();
        Date fechaFin =actividad.getFechaFin();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues registro= new ContentValues();
        registro.put("nombre",actividad.getNombre());
        registro.put("descripcion", actividad.getDescripcion());
        registro.put("idResponsable", usuario.getId());
        registro.put("idProyecto", proyecto.getId());
        //registro.put("usuario",usuario);
        //registro.put("proyecto", proyecto);
        registro.put("fechaInicio", format.format(fechaInicio));
        registro.put("fechaFinal", format.format(fechaFin));
        return conex.insert("Actividades",registro);
    }


    /**
     * Metodo para buscar una actividad
     * @param nombre
     * @return
     */
    public Actividad buscar(String nombre, Usuario usuario, Proyecto proyecto){
        String consulta ="select a.descripcion, a.fechaInicio, a.fechaFinal from Actividades a" +
                " where a.nombre=" + nombre + "and idProyecto=" + proyecto.getId() + "and idResponsable=" +usuario.getId() ;

        Cursor temp = conex.search(consulta);
        if (temp.getCount()>0){

            Actividad  actividad = new  Actividad();
            temp.moveToFirst();
            actividad.setNombre(nombre);
            actividad.setDescripcion(temp.getString(0));
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try {
                actividad.setFechaIni(format.parse(temp.getString(1)));
                actividad.setFechaFin(format.parse(temp.getString(2)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            actividad.setProyecto(MenuProyectosActivity.proyecto);
            actividad.setUsuario(MainActivity.usuario);
            return  actividad;
        }

        conex.cerrarConexion();
        return null;

    }

    /**
     * MEtodo para buscar una actividad solo por su nombre
     * @param nombre
     * @return
     */
    public Actividad buscarNombre(String nombre){
        Actividad a = null;
        String consulta ="select a.id from Actividades a" +
                " where a.nombre='" + nombre  + "'";

        Cursor temp = conex.search(consulta);

        if (temp.getCount()>0){

            a = new Actividad();
            temp.moveToFirst();
            a.setId(temp.getInt(0));

        }
        return a;
    }



    /**
     * Metodo para modificar una actividad
     * @param actividad
     * @return
     */
    public boolean modificar(Actividad actividad, Usuario usuario, Proyecto proyecto){
        String tabla = "Actividades";
        String condicion = "nombre='" + actividad.getNombre() + "'";

        Date fechaInicio = actividad.getFechaIni();
        Date fechaFin =actividad.getFechaFin();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues registro= new ContentValues();
        registro.put("nombre",actividad.getNombre());
        registro.put("descripcion", actividad.getDescripcion());
        registro.put("idResponsable", usuario.getId());
        registro.put("idProyecto", proyecto.getId());
        registro.put("fechaInicio", format.format(fechaInicio));
        registro.put("fechaFinal", format.format(fechaFin));

        return conex.update(tabla,condicion,registro);
    }


    /**
     * MEetodo para eliminar una actividad
     * @param actividad
     * @return
     */
    public boolean eliminar(Actividad actividad){
        String tabla = "Actividades";
        String condicion = "nombre='" + actividad.getNombre() + "'";
        return conex.delete(tabla,condicion);
    }


    /**
     * Lista de actividades
     * @return
     */
    public List<Actividad> listaActividades (Usuario usuario, Proyecto proyecto){
        ArrayList<Actividad> lista = new ArrayList<>();
        String consulta ="select id, nombre, descripcion, fechaInicio,fechaFinal from Actividades " +
                " WHERE idResponsable="+ usuario.getId() + " and idProyecto=" + proyecto.getId();

        Cursor temp = conex.search(consulta);
        if (temp.moveToFirst()){
            do {
                Date fechaInicio = null;
                Date fechaFin = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    fechaInicio = format.parse(temp.getString(3));
                    fechaFin = format.parse(temp.getString(4));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Actividad a = new Actividad(temp.getString(1), temp.getString(2), fechaInicio, fechaFin, usuario, proyecto);
                a.setId(temp.getInt(0));
                lista.add(a);
            } while (temp.moveToNext());
        }
        return lista;

    }






}
