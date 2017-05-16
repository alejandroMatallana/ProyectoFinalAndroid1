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
    public boolean guardar(Actividad actividad){

        Date fechaInicio = actividad.getFechaIni();
        Date fechaFin =actividad.getFechaFin();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues registro= new ContentValues();
        registro.put("nombre",actividad.getNombre());
        registro.put("descripcion", actividad.getDescripcion());
        registro.put("usuario", String.valueOf(actividad.getUsuario()));
        registro.put("proyecto", String.valueOf(actividad.getProyecto()));
        registro.put("fechaInicio", format.format(fechaInicio));
        registro.put("fechaFinal", format.format(fechaFin));
        return conex.insert("Actividades",registro);
    }


    /**
     * Metodo para buscar una actividad
     * @param nombre
     * @return
     */
    public Actividad buscar(String nombre){
        String consulta ="select a.descripcion, a.fechaInicio, a.fechaFinal, u.nombre, p.nombre from Actividades a join Proyectos p" +
                "on p.id=a.idProyecto JOIN Usuarios u on u.id=a.idResponsable where a.nombre=" + nombre;

        Cursor temp = conex.search(consulta);
        if (temp.getCount()>0){

            Actividad  actividad = new  Actividad();
            temp.moveToFirst();
            actividad.setNombre(nombre);
            actividad.setDescripcion(temp.getString(3));
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try {
                actividad.setFechaIni(format.parse(temp.getString(4)));
                actividad.setFechaFin(format.parse(temp.getString(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //actividad.setProyecto(temp.getString(6));




            //actividad.setUsuario(temp.getInt(7));
            return  actividad;
        }

        conex.cerrarConexion();
        return null;

    }



    /**
     * Metodo para modificar una actividad
     * @param actividad
     * @return
     */
    public boolean modificar(Actividad actividad){
        String tabla = "Actividades";
        String condicion = "nombre='" + actividad.getNombre() + "'";

        Date fechaInicio = actividad.getFechaIni();
        Date fechaFin =actividad.getFechaFin();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues registro= new ContentValues();
        registro.put("nombre",actividad.getNombre());
        registro.put("descripcion", actividad.getDescripcion());
        registro.put("usuario", String.valueOf(actividad.getUsuario()));
        registro.put("proyecto", String.valueOf(actividad.getProyecto()));
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
    public List<Actividad> listaActividades (){
        ArrayList<Actividad> lista = new ArrayList<>();
        String consulta ="select nombre, descripcion, fechaInicio,fechaFinal,idResponsable,idProyecto from Actividades a" +
                "join ProyectosIntegrantes pi on p.id=pi.idProyecto WHERE pi.idUsuario="+UsuarioDAO.IDUsuarioLogueado;


        Cursor temp = conex.search(consulta);
        if (temp.moveToFirst()){
            do {
                Date fechaInicio = null;
                Date fechaFin = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    fechaInicio = format.parse(temp.getString(4));
                    fechaFin = format.parse(temp.getString(5));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Actividad a = new Actividad(temp.getInt(0),temp.getString(1), temp.getString(2), fechaInicio, fechaFin, temp.getInt(5));
                //Proyecto p = new Proyecto(temp.getInt(0),temp.getString(1),fechaInicio,fechaFin,temp.getDouble(4));
                //lista.add(a);
            } while (temp.moveToNext());
        }
        return lista;

    }






}
