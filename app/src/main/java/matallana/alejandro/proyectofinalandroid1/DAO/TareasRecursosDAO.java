package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;
import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Recurso;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.TareasRecursos;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuActividadesActivity;
import matallana.alejandro.proyectofinalandroid1.Vista.MenuProyectosActivity;
import matallana.alejandro.proyectofinalandroid1.Vista.RecursoActivity;

/**
 * Created by miguel on 23/05/17.
 */

public class TareasRecursosDAO {

    Conexion conexion;

    public TareasRecursosDAO(Activity activity) {
        conexion = new Conexion(activity);
    }

    public boolean crear(TareasRecursos tareasRecursos) {
        ContentValues registro= new ContentValues();
        registro.put("idTarea", tareasRecursos.getTarea().getId());
        registro.put("idRecurso", tareasRecursos.getRecurso().getId());
        return conexion.insert("TareasRecursos", registro);
    }

    public Actividad buscarActividad(int id){
        String consulta ="select nombre, descripcion, fechaInicio, fechaFinal, idResponsable from Actividades" +
                " where id=" + id;
        Cursor temp = conexion.search(consulta);
        if (temp.getCount()>0){
            Actividad  actividad = new  Actividad();
            temp.moveToFirst();
            actividad.setNombre(temp.getString(0));
            actividad.setDescripcion(temp.getString(1));
            Calendar calendar = Calendar.getInstance();
            String[] datos = temp.getString(2).split("/");
            calendar.set(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),Integer.parseInt(datos[2]));
            actividad.setFechaIni(calendar.getTime());
            datos = temp.getString(3).split("/");
            calendar.set(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),Integer.parseInt(datos[2]));
            actividad.setFechaFin(calendar.getTime());
            actividad.setId(id);
            actividad.setProyecto(MenuProyectosActivity.proyecto);
            return actividad;
        }
        conexion.cerrarConexion();
        return null;
    }

    public Tarea buscarTarea(int id) {
        String consulta ="select nombre, porcentajeDesarrollo, fechaInicio, fechaFinal, idActividad" +
                " from Tareas where id=" + id;
        Cursor temp = conexion.search(consulta);
        if (temp.getCount()>0){
            temp.moveToFirst();
            Tarea tarea = new Tarea();
            tarea.setId(id);
            tarea.setNombreTarea(temp.getString(0));
            tarea.setPorcentaje(temp.getInt(1));
            String[] datos = temp.getString(2).split("/");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),Integer.parseInt(datos[2]));
            tarea.setFechaInicio(calendar.getTime());
            datos = temp.getString(3).split("/");
            calendar.set(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),Integer.parseInt(datos[2]));
            tarea.setFechaFinal(calendar.getTime());
            tarea.setActividad(buscarActividad(temp.getInt(4)));
            return tarea;
        }
        return null;
    }

    public Tarea buscarTareaNombre(String nombreTarea, List<Tarea> tareas) {
        int posicion = 0;
        while(!nombreTarea.equalsIgnoreCase(tareas.get(posicion).getNombreTarea())) {
            posicion ++;
        }
        return tareas.get(posicion);
    }

    public boolean eliminar(TareasRecursos tareasRecursos) {
        String tabla = "TareasRecursos" ;
        String condicion = "id=" + tareasRecursos.getId();
        return conexion.delete(tabla,condicion);
    }

    public List<Tarea> listarTareas(Proyecto proyecto) {
        List<Tarea> tareas = new ArrayList<>();
        String consulta = "SELECT ta.id,ta.nombre,ta.porcentajeDesarrollo,ta.fechaInicio,ta.fechaFinal FROM Tareas ta " +
                "JOIN Actividades ac on ta.idActividad=ac.id WHERE ac.idProyecto=" + proyecto.getId();
        Cursor temp = conexion.search(consulta);
        if (temp.moveToFirst()){
            do {
                Calendar calendar = Calendar.getInstance();
                String[] datos = temp.getString(3).split("/");
                calendar.set(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),Integer.parseInt(datos[2]));
                Date fechaInicio = calendar.getTime();
                datos = temp.getString(4).split("/");
                calendar.set(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),Integer.parseInt(datos[2]));
                Date fechaFinal = calendar.getTime();
                Tarea tarea = new Tarea(temp.getString(1),temp.getInt(2),fechaInicio,fechaFinal);
                tarea.setId(temp.getInt(0));
                tarea.setActividad(MenuActividadesActivity.actividad);
                tareas.add(tarea);
            } while (temp.moveToNext());
        }
        return tareas;
    }

    public List<TareasRecursos> listarTareasConRecursos(Proyecto proyecto, Recurso recurso) {
        List<TareasRecursos> tareas = new ArrayList<>();
        String consulta = "SELECT ta.id,ta.idTarea " +
                "FROM TareasRecursos ta JOIN Recursos re on ta.idRecurso=re.id WHERE " +
                "re.idProyecto=" + proyecto.getId() + " AND ta.idRecurso=" + recurso.getId();
        Cursor temp = conexion.search(consulta);
        if (temp.moveToFirst()){
            do {
                TareasRecursos tareasRecursos = new TareasRecursos();
                tareasRecursos.setRecurso(RecursoActivity.recurso);
                tareasRecursos.setTarea(buscarTarea(temp.getInt(1)));
                tareasRecursos.setId(temp.getInt(0));
                tareas.add(tareasRecursos);
            } while (temp.moveToNext());
        }
        return tareas;
    }
}
