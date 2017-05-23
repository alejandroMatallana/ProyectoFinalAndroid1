package matallana.alejandro.proyectofinalandroid1.Modelo;

/**
 * Created by miguel on 15/05/17.
 */

public class Recurso {

    private int id;
    private String nombre;
    private int cantidad;
    private String descripcion;
    private String ubicacion;
    private Proyecto proyecto;

    public Recurso() {
    }

    public Recurso(String nombre, int cantidad, String descripcion, String ubicacion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.proyecto = null;
    }

    public Recurso(String nombre, int cantidad, String descripcion, String ubicacion, Proyecto proyecto) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.proyecto = proyecto;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return nombre + "\nCanitdad=" + cantidad + "\nDescripcion=" + descripcion;
    }
}
