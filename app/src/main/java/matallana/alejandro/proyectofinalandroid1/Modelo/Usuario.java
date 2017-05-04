package matallana.alejandro.proyectofinalandroid1.Modelo;

import java.util.Date;

/**
 * Created by AlejandroM on 29/03/2017.
 */
public class Usuario {

    private int id;
    private String tipoDocumento;
    private int numeroDocumento;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String pass;
    private String usuario;
    private String correoElectronico;
    private String tipoUsuario;
    private Cargo cargo;

    public Usuario() {
    }

    public Usuario(String tipoDocumento, int numeroDocumento, String nombres, String apellidos,
                   Date fechaNacimiento, String pass, String usuario, String correoElectronico,
                   String tipoUsuario) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.pass = pass;
        this.usuario = usuario;
        this.correoElectronico = correoElectronico;
        this.tipoUsuario = tipoUsuario;
        this.cargo = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}

