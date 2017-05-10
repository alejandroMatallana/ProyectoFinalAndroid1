package matallana.alejandro.proyectofinalandroid1.Modelo;

/**
 * Created by miguel on 2/05/17.
 */

public class Cargo {

    private int id;
    private String nombre;
    private String descripcion;
    private String horario;
    private double salario;
    private Proyecto idProyecto;

    public Cargo() {
    }

    public Cargo(int id, String nombre, String descripcion, String horario, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.salario = salario;
        this.idProyecto = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }
}