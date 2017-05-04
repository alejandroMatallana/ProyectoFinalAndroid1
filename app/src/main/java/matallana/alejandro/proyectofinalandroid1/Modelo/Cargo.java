package matallana.alejandro.proyectofinalandroid1.Modelo;

/**
 * Created by miguel on 2/05/17.
 */

public class Cargo {

    private int id;
    private int nombre;
    private int descripcion;
    private int horario;
    private int salario;

    public Cargo() {
    }

    public Cargo(int id, int nombre, int descripcion, int horario, int salario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(int descripcion) {
        this.descripcion = descripcion;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}
