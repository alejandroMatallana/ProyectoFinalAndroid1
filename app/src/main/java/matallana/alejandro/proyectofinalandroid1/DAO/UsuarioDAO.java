package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;

/**
 * Created by AlejandroM on 26/04/2017.
 */
public class UsuarioDAO {

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
        //registro.put("cargo", usuario.getCargo().getId());
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
    public Usuario buscar (String numeroDocumento) {
        Usuario usuario = null;
        String consulta = "select tipoDocumento,nombres,apellidos,fechaNacimiento,pass,usuario," +
                "correoElectronico,tipoUsuario from Usuarios where numeroDocumento=" + numeroDocumento;

        Cursor temp = conex.search(consulta);
        if (temp.getCount()>0){
            usuario = new Usuario();
            temp.moveToFirst();
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
            usuario.setNumeroDocumento(Integer.parseInt(numeroDocumento));
        }
        conex.cerrarConexion();
        return usuario;
    }

    public String buscarLogin(String username, String password) {
        String consulta = "select tipoUsuario from Usuarios where usuario='" + username + "' and pass='" + password + "'";
        Cursor temp = conex.search(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            conex.cerrarConexion();
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