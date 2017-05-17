package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;

/**
 * Created by AlejandroM on 26/04/2017.
 */
public class UsuarioDAO {

    public static int IDUsuarioLogueado = 0;
    Conexion conex;

    public UsuarioDAO(Activity activity){
        //Creacion o conexion de la BD
        // Ademas se indica el numero de la vercion anterior y la actual
        conex = new Conexion(activity);
    }

    /**
     * Metodo que guarda los usuarios
     * @param usuario
     * @return
     */
    public  boolean guardar (Usuario usuario){
        //Objeto que cotendra la informacion a almacenar
        Date fecha = usuario.getFechaNacimiento();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ContentValues registro= new ContentValues();
        registro.put("tipoDocumento", usuario.getTipoDocumento());
        registro.put("numeroDocumento", usuario.getNumeroDocumento());
        registro.put("nombres", usuario.getNombres());
        registro.put("apellidos", usuario.getApellidos());
        registro.put("fechaNacimiento", format.format(fecha));
        registro.put("pass", usuario.getPass());
        registro.put("usuario", usuario.getUsuario());
        registro.put("correoElectronico", usuario.getCorreoElectronico());
        registro.put("tipoUsuario", usuario.getTipoUsuario());
        return conex.insert("Usuarios", registro);
    }

    /**
     * Metodo para modificar los usuarios
     * @param usuario
     * @return
     */
    public  boolean modificar (Usuario usuario){
        String tabla = "Usuarios";
        String condicion = "numeroDocumento=" + usuario.getNumeroDocumento();
        Date fecha = usuario.getFechaNacimiento();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        ContentValues registro = new ContentValues();
        registro.put("tipoDocumento", usuario.getTipoDocumento());
        registro.put("numeroDocumento", usuario.getNumeroDocumento());
        registro.put("nombres", usuario.getNombres());
        registro.put("apellidos", usuario.getApellidos());
        registro.put("fechaNacimiento", format.format(fecha));
        registro.put("pass", usuario.getPass());
        registro.put("usuario", usuario.getUsuario());
        registro.put("correoElectronico", usuario.getCorreoElectronico());
        registro.put("tipoUsuario", usuario.getTipoUsuario());
        //registro.put("cargo", usuario.getCargo().getId());
        return conex.update(tabla,condicion,registro);
    }

    /**
     * Metodo para buscar un usuario con la colsulta
     * @param numeroDocumento
     * @return
     */
    public Usuario buscar (int numeroDocumento) {
        String consulta = "select tipoDocumento,nombres,apellidos,fechaNacimiento,pass,usuario," +
                "correoElectronico,tipoUsuario,id from Usuarios where numeroDocumento=" + numeroDocumento;

        Cursor temp = conex.search(consulta);
        if (temp.getCount()>0){
            Usuario usuario = new Usuario();
            temp.moveToFirst();
            usuario.setId(temp.getInt(8));
            usuario.setTipoDocumento(temp.getString(0));
            usuario.setNombres(temp.getString(1));
            usuario.setApellidos(temp.getString(2));
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try {
                usuario.setFechaNacimiento(format.parse(temp.getString(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            usuario.setPass(temp.getString(4));
            usuario.setUsuario(temp.getString(5));
            usuario.setCorreoElectronico(temp.getString(6));
            usuario.setTipoUsuario(temp.getString(7));
            usuario.setNumeroDocumento(numeroDocumento);
            conex.cerrarConexion();
            return usuario;
        }
        conex.cerrarConexion();
        return null;
    }

    public String buscarLogin(String username, String password) {
        String consulta = "select tipoUsuario,id from Usuarios where usuario='" + username + "' and pass='" + password + "'";
        Cursor temp = conex.search(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            conex.cerrarConexion();
            System.out.println(temp.getInt(1));
            IDUsuarioLogueado = temp.getInt(1);
            return temp.getString(0);
        }
        conex.cerrarConexion();
        return null;
    }

    public boolean verificarUsername(String username) {
        String consulta = "select tipoUsuario from Usuarios where usuario='" + username + "'";
        Cursor temp = conex.search(consulta);
        if (temp.getCount() > 0) {
            conex.cerrarConexion();
            return true;
        }
        conex.cerrarConexion();
        return false;
    }

    public boolean eliminar (Usuario usuario){
        String tabla = "Usuarios";
        String condicion = "numeroDocumento=" + usuario.getNumeroDocumento();
        return conex.delete(tabla,condicion);
    }

    /**
     * metodo para listar todos los usuario registrados como tipo integrante
     * @return
     */
    public ArrayList<Usuario> listarIntegrantes(){
        ArrayList<Usuario> lista = new ArrayList<>();
        String consulta = "SELECT u.id,u.tipoDocumento,u.numeroDocumento,u.nombres,u.apellidos," +
                "u.fechaNacimiento,u.pass,u.usuario,u.correoElectronico,u.tipoUsuario" +
                " FROM Usuarios AS u WHERE u.tipoUsuario='Integrante del proyecto'";
        Cursor temp = conex.search(consulta);
        if (temp.moveToFirst()){
            do {
                Date fechaNacimientoUsuario = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    fechaNacimientoUsuario = format.parse(temp.getString(5));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Usuario usuario = new Usuario(temp.getInt(0),temp.getString(1),temp.getInt(2),
                        temp.getString(3),temp.getString(4),fechaNacimientoUsuario,temp.getString(7),
                        temp.getString(6),temp.getString(8),temp.getString(9));

                lista.add(usuario);

            } while (temp.moveToNext());
        }
        return lista;
    }

    public List<Usuario> listar(){
        /*List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String consulta = "select cedula,nombre,apellido,edad from usuario";
        Cursor temp = conex.search(consulta);

        if (temp.moveToFirst()){
            do {
                Usuario usuario = new Usuario(temp.getString(0), temp.getString(1),temp.getString(2),
                        Integer.parseInt(temp.getString(3)));
                listaUsuarios.add(usuario);

            } while (temp.moveToNext());


        }
        return listaUsuarios;*/
        return null;
    }



}