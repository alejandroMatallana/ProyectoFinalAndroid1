package matallana.alejandro.proyectofinalandroid1.Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sebastiancardona on 24/05/17.
 */

public class ComentarioTarea {

    private int id;
    /**
     * fecha en la que se escribio el comentario
     */
    private Date fecha;
    private String titulo;
    private String comentario;
    /**
     * tarea a la que pertence este comentario
     */
    private Tarea tarea;

    /**
     * Constructor vacio...
     */
    public ComentarioTarea() {
        super();
    }

    /**
     * constructor sin id...
     * @param fecha
     * @param titulo
     * @param comentario
     */
    public ComentarioTarea(Date fecha, String titulo, String comentario, Tarea tarea) {
        this.fecha = fecha;
        this.titulo = titulo;
        this.comentario = comentario;
        this.tarea = tarea;
    }

    /**
     * Constructor con id...
     * @param id
     * @param fecha
     * @param titulo
     * @param comentario
     */
    public ComentarioTarea(int id, Date fecha, String titulo, String comentario, Tarea tarea) {
        this.id = id;
        this.fecha = fecha;
        this.titulo = titulo;
        this.comentario = comentario;
        this.tarea = tarea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy/MM/dd");
        return "Fecha de publicación: " + format.format(fecha)
                + "\nTitulo: "+ titulo
                + "\nComentario: "+ comentario;
    }
}
