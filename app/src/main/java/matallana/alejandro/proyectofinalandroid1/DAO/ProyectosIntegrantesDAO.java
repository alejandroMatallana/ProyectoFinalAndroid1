package matallana.alejandro.proyectofinalandroid1.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import matallana.alejandro.proyectofinalandroid1.Infraestructura.Conexion;
import matallana.alejandro.proyectofinalandroid1.Modelo.Cargo;
import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.ProyectosIntegrantes;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;

/**
 * Created by sebastiancardona on 10/05/17.
 */

public class ProyectosIntegrantesDAO {

    private Conexion conex;

    public ProyectosIntegrantesDAO(Activity activity) {
        conex = new Conexion(activity);
    }

    /**
     * metodo para guardar un registro en la tabla ProyectosIntegrantes
     * @param cargo, id del cargo
     * @param proyecto, id del proyecto
     * @param usuario, id del usuario
     */
    public boolean guardar(int cargo, int proyecto, int usuario){
        ContentValues registro= new ContentValues();
        registro.put("idCargo", cargo);
        registro.put("idProyecto", proyecto);
        registro.put("idUsuario", usuario);
        return conex.insert("ProyectosIntegrantes", registro);
    }

    /**
     * metodo para eliminar un registro de la tabla ProyectosIntegrantes donde coincidan el usuario
     * y el proyecto
     * @param usuario, id del usuario
     * @param proyecto, id del proyecto
     * @return true si se eliminó el registro, false de lo contrario
     */
    public boolean eliminar(int usuario, int proyecto){
        String tabla = "ProyectosIntegrantes";
        String condicion = "idUsuario=" + usuario + " AND idProyecto=" + proyecto;
        return conex.delete(tabla,condicion);
    }

    public boolean buscarUsuarioProyecto(int usuario, int proyecto){
        String consulta = "SELECT c.id,c.nombre,c.descripcion,c.horario,c.salario" +
                " FROM ProyectosIntegrantes AS pi JOIN Cargos AS c ON pi.idCargo=c.id" +
                " WHERE pi.idProyecto="+proyecto + " AND pi.idUsuario="+usuario;

        Cursor temp = conex.search(consulta);

        if (temp.getCount()>0){
            Cargo cargo = new Cargo();
            cargo.setId(temp.getInt(0));
            cargo.setNombre(temp.getString(1));
            cargo.setDescripcion(temp.getString(2));
            cargo.setHorario(temp.getString(3));
            cargo.setSalario(temp.getDouble(4));
            return  true;

        }
        return false;
    }

    /**
     * metodo para listar todos los registros en la tabla ProyetosIntegrantes
     * que tengan como idProyecto a proyecto (parametro ingresado)
     * @param proyecto, id del proyecto
     * @return lista con los registros en los que el idProyecto sea igual al ingresado
     * por parametro
     */
    public ArrayList<ProyectosIntegrantes> listar(int proyecto){
        ArrayList<ProyectosIntegrantes> lista = new ArrayList<>();
        String consulta = "SELECT pi.id,u.id,u.tipoDocumento,u.numeroDocumento,u.nombres," +
                "u.apellidos,u.fechaNacimiento,u.pass,u.usuario,u.correoElectronico,u.tipoUsuario," +
                "c.id,c.nombre,c.descripcion,c.horario,c.salario" +
                " FROM (Usuarios AS u JOIN ProyectosIntegrantes AS pi ON u.id=pi.idUsuario)" +
                       " JOIN Cargos AS c ON pi.idCargo=c.id" +
                " WHERE pi.idProyecto="+proyecto;
        Cursor temp = conex.search(consulta);
        if (temp.moveToFirst()){
            do {
                Date fechaNacimientoUsuario = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    fechaNacimientoUsuario = format.parse(temp.getString(6));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Proyecto p = new Proyecto(proyecto,null,null,null,0);

                ProyectosIntegrantes pi = new ProyectosIntegrantes();
                pi.setId(temp.getInt(0));

                Usuario u = new Usuario(temp.getInt(1),temp.getString(2),temp.getInt(3),temp.getString(4),
                        temp.getString(5),fechaNacimientoUsuario,temp.getString(8),temp.getString(7),
                        temp.getString(9),temp.getString(10));

                Cargo c = new Cargo(temp.getInt(11),temp.getString(12),temp.getString(13),
                        temp.getString(14),temp.getDouble(15),p);

                pi.setCargo(c);
                pi.setIntegrante(u);
                pi.setProyecto(p);

                lista.add(pi);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
