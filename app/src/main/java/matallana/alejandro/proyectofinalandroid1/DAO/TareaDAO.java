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
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuActividadesActivity;

/**
 * Created by miguel on 15/05/17.
 */

public class TareaDAO {

    Conexion conexion;

    public TareaDAO(Activity activity) {
        conexion = new Conexion(activity);
    }

    public boolean crear(Tarea tarea, Actividad actividad) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues registro= new ContentValues();
        registro.put("nombre", tarea.getNombreTarea());
        registro.put("porcentajeDesarrollo", tarea.getPorcentaje());
        registro.put("fechaInicio", format.format(tarea.getFechaInicio()));
        registro.put("fechaFinal", format.format(tarea.getFechaFinal()));
        registro.put("idActividad", actividad.getId());
        return conexion.insert("Tareas", registro);
    }

    public boolean editar(Tarea tarea) {
        String tabla = "Tareas";
        String condicion = "idActividad=" + tarea.getActividad().getId();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues registro = new ContentValues();
        registro.put("nombre", tarea.getNombreTarea());
        registro.put("porcentajeDesarrollo", tarea.getPorcentaje());
        registro.put("fechaInicio", format.format(tarea.getFechaInicio()));
        registro.put("fechaFinal", format.format(tarea.getFechaFinal()));
        return conexion.update(tabla,condicion,registro);
    }

    public Tarea buscar(String nombreTarea, Actividad actividad) {
        String consulta = "SELECT porcentajeDesarrollo,fechaInicio,fechaFinal FROM Tareas " +
                "WHERE nombre='" + nombreTarea + "' and idActividad=" + actividad.getId();

        Cursor temp = conexion.search(consulta);
        if (temp.getCount()>0){
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                Tarea tarea = new Tarea();
                temp.moveToFirst();
                tarea.setFechaFinal(format.parse(temp.getString(2)));
                tarea.setFechaInicio(format.parse(temp.getString(1)));
                tarea.setNombreTarea(nombreTarea);
                tarea.setActividad(actividad);
                tarea.setPorcentaje(temp.getInt(0));
                return tarea;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        conexion.cerrarConexion();
        return null;
    }

    public boolean eliminar(Tarea tarea) {
        String tabla = "Tareas";
        String condicion = "id=" + tarea.getId();
        return conexion.delete(tabla,condicion);
    }

    public List<Tarea> listar(Actividad actividad) {
        List<Tarea> tareas = new ArrayList<>();
        System.out.println(actividad.getId());
        String consulta = "SELECT nombre,porcentajeDesarrollo,fechaInicio,fechaFinal,id FROM Tareas WHERE idActividad=" + actividad.getId();
        Cursor temp = conexion.search(consulta);
        if (temp.moveToFirst()){
            do {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    Date fechaInicio = format.parse(temp.getString(2));
                    Date fechaFinal = format.parse(temp.getString(3));
                    Tarea tarea = new Tarea(temp.getString(0),temp.getInt(1),fechaInicio,fechaFinal);
                    tarea.setId(temp.getInt(4));
                    tarea.setActividad(MenuActividadesActivity.actividad);
                    tareas.add(tarea);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (temp.moveToNext());
        }
        return tareas;
    }

}
