package matallana.alejandro.proyectofinalandroid1.Modelo;

import java.util.Date;

/**
 * Created by sebastiancardona on 9/05/17.
 */

public class Proyecto {

    private String nombre;

    /**
     * fecha en la que se inició el proyecto
     */
    private Date fechaInicio;

    /**
     * fecha en la que se finalizará el proyecto
     */
    private Date fechaFin;

    /**
     * porcentaje de finalizacion en el que se encuentra el proyecto
     */
    private double estado;

    /**
     * Constructor...
     */
    public Proyecto() {
        super();
    }

    /**
     * constructor...
     * @param nombre
     * @param fechaInicio
     * @param fechaFin
     * @param estado
     */
    public Proyecto(String nombre, Date fechaInicio, Date fechaFin, double estado) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getEstado() {
        return estado;
    }

    public void setEstado(double estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre + ", Estado " + (estado*100);
    }
}
