package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
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
        ContentValues registro= new ContentValues();
        registro.put("cedula", usuario.getCedula());
        registro.put("nombre", usuario.getNombre());
        registro.put("apellido", usuario.getApellido());
        registro.put("edad", usuario.getEdad());
        return conex.ejecutarInsert("usuario", registro);
    }

    /**
     * Metodo para modificar los usuarios
     * @param usuario
     * @return
     */
    public  boolean modificar (Usuario usuario){
        String tabla = "usuario";
        String condicion = "cedula= " + usuario.getCedula();

        ContentValues registro = new ContentValues();
        registro.put("nombre", usuario.getNombre());
        registro.put("apellido", usuario.getApellido());
        registro.put("edad", usuario.getEdad());
        return conex.ejecutarUpdate(tabla,condicion,registro);
    }

    /**
     * Metodo para buscar un usuario con la colsulta
     * @param cedula
     * @return
     */
    public Usuario buscar (String cedula){
        Usuario usuario = null;
        String consulta = "select nombre, apellido, edad " + "from usuario where" + " cedula= " + cedula + "";

        Cursor temp = conex.ejecutarSearch(consulta);

        //El resultado tiene mas de un registro
        if (temp.getCount()>0){
            temp.moveToFirst();
            usuario = new Usuario(cedula, temp.getString(0), temp.getString(1), Integer.parseInt(temp.getString(2)));
        }
        conex.cerrarConexion();
        return usuario;

    }

    public boolean eliminar (Usuario usuario){
        String tabla = "usuario";
        String condicion = "cedula="  +  usuario.getCedula() + "";
        return conex.ejecutarDelete(tabla,condicion);
    }

    public List<Usuario> listar(){
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String consulta = "select cedula,nombre,apellido,edad from usuario";
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.moveToFirst()){
            do {
                Usuario usuario = new Usuario(temp.getString(0), temp.getString(1),temp.getString(2),
                        Integer.parseInt(temp.getString(3)));
                listaUsuarios.add(usuario);

            } while (temp.moveToNext());


        }
        return listaUsuarios;
    }



}
