package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;
import matallana.alejandro.proyectofinalandroid1.Modelo.ComentarioTarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;

/**
 * Created by sebastiancardona on 24/05/17.
 */

public class ComentarioDAO {

    private Conexion conex;

    public ComentarioDAO(Activity activity) {
        conex = new Conexion(activity);
    }

    /**
     * metodo para guardar un comentario de una tarea
     * @param comentario, el comentario que será guardado
     * @return true si el comentario se registro corectamente, false de lo contrario
     */
    public boolean guardar(ComentarioTarea comentario) {
        ContentValues registro= new ContentValues();
        Date fecha = comentario.getFecha();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(format.format(fecha));
        registro.put("fecha", format.format(fecha));
        registro.put("titulo", comentario.getTitulo());
        registro.put("comentario", comentario.getComentario());
        registro.put("idTarea", comentario.getTarea().getId());
        return conex.insert("ComentarioTareas", registro);
    }

    /**
     * metodo para listar los comentarios de una tarea
     * @param tarea, la tarea
     * @return la lista de comentarios que se han registrado de esa tarea
     */
    public ArrayList<ComentarioTarea> listar(Tarea tarea){
        ArrayList<ComentarioTarea> lista = new ArrayList<>();
        String consulta = "SELECT ct.id, ct.fecha, ct.titulo, ct.comentario" +
                " FROM ComentarioTareas AS ct WHERE ct.idTarea="+tarea.getId() + " ORDER BY fecha";
        Cursor temp = conex.search(consulta);
        if (temp.moveToFirst()){
            do {
                Date fecha = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    fecha = format.parse(temp.getString(1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ComentarioTarea comentario = new ComentarioTarea(temp.getInt(0),fecha,
                        temp.getString(2),temp.getString(3),tarea);

                lista.add(comentario);

            } while (temp.moveToNext());
        }
        return lista;
    }
}
