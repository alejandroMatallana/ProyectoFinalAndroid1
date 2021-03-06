package matallana.alejandro.proyectofinalandroid1.Modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by miguel on 15/05/17.
 */

public class Tarea {

    private int id;
    private String nombreTarea;
    private int porcentaje;
    private Date fechaInicio;
    private Date fechaFinal;
    private Actividad actividad;

    public Tarea() {
    }

    public Tarea(String nombreTarea, int porcentaje, Date fechaInicio, Date fechaFinal) {
        this.nombreTarea = nombreTarea;
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        actividad = null;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return nombreTarea + " - " + porcentaje + "% - " + format.format(fechaInicio) + " - " + format.format(fechaFinal);
    }
}
