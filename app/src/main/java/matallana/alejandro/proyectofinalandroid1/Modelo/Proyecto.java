package matallana.alejandro.proyectofinalandroid1.Modelo;

import java.util.Date;

/**
 * Created by sebastiancardona on 9/05/17.
 */

public class Proyecto {

    private int id;

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
    private double etapa;

    /**
     * Constructor...
     */
    public Proyecto() {
        super();
    }

    /**
     * Constructor sin id...
     * @param nombre
     * @param fechaInicio
     * @param fechaFin
     * @param etapa
     */
    public Proyecto(String nombre, Date fechaInicio, Date fechaFin, double etapa) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.etapa = etapa;
    }

    /**
     * constructor...
     * @param nombre
     * @param fechaInicio
     * @param fechaFin
     * @param etapa
     */
    public Proyecto(int id, String nombre, Date fechaInicio, Date fechaFin, double etapa) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.etapa = etapa;
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

    public double getEtapa() {
        return etapa;
    }

    public void setEtapa(double etapa) {
        this.etapa = etapa;
    }

    @Override
    public String toString() {
        return nombre + ", Estado " + (etapa*100)+"%";
    }
}
