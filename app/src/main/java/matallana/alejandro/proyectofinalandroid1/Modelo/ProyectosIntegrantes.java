package matallana.alejandro.proyectofinalandroid1.Modelo;

/**
 * entidad que describe los integrantes con su cargo en un proyecto especifico
 * Created by sebastiancardona on 16/05/17.
 */

public class ProyectosIntegrantes {

    private int id;

    private Proyecto proyecto;

    private Usuario integrante;

    private Cargo cargo;

    /**
     * Constructor vacio...
     */
    public ProyectosIntegrantes() {
        super();
    }

    /**
     * Constructor sin id...
     * @param proyecto
     * @param integrante
     * @param cargo
     */
    public ProyectosIntegrantes(Proyecto proyecto, Usuario integrante, Cargo cargo) {
        this.proyecto = proyecto;
        this.integrante = integrante;
        this.cargo = cargo;
    }

    /**
     * Constructor...
     * @param id
     * @param proyecto
     * @param integrante
     * @param cargo
     */
    public ProyectosIntegrantes(int id, Proyecto proyecto, Usuario integrante, Cargo cargo) {
        this.id = id;
        this.proyecto = proyecto;
        this.integrante = integrante;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Usuario getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Usuario integrante) {
        this.integrante = integrante;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Nombre: "+integrante.getNombres() + " " + integrante.getApellidos()
                + "\nIdentificacíon: "+integrante.getNumeroDocumento()
                + "\nCargo Asignado: "+ cargo.getNombre();
    }
}
