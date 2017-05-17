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
    private Proyecto proyecto;

    public Cargo() {
    }

    public Cargo(int id, String nombre, String descripcion, String horario, double salario, Proyecto proyecto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.salario = salario;
        this.proyecto = proyecto;
    }

    public Cargo(String nombre, String descripcion, String horario, double salario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.salario = salario;
        this.proyecto = null;
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

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return nombre + " - " + salario;
    }
}