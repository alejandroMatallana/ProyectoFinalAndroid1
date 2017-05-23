package matallana.alejandro.proyectofinalandroid1.Modelo;

/**
 * Created by miguel on 23/05/17.
 */

public class TareasRecursos {

    private int id;
    private Tarea tarea;
    private Recurso recurso;

    public TareasRecursos() {
    }

    public TareasRecursos(Tarea tarea, Recurso recurso) {
        this.tarea = tarea;
        this.recurso = recurso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    @Override
    public String toString() {
        return "Tarea: " + tarea.getNombreTarea() + "\nActividad: " + tarea.getActividad().getNombre();
    }
}
