package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import matallana.alejandro.proyectofinalandroid1.DAO.UsuarioDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;

/**
 * Created by AlejandroM on 26/04/2017.
 */
public class ControllerUsuario {

    UsuarioDAO usuarioDAO;

    public ControllerUsuario(Activity activity){
        usuarioDAO = new UsuarioDAO(activity);
    }

    public boolean guardar(Usuario usuario) {
        if (usuarioDAO.verificarUsername(usuario.getUsuario())) {
            return false;
        } else {
            Usuario usuario1 = buscar(usuario.getNumeroDocumento());
            if (usuario1 == null) {
                return usuarioDAO.guardar(usuario);
            } else {
                return false;
            }
        }
    }

    public Usuario buscar(int numeroDocumento) {
        return usuarioDAO.buscar(numeroDocumento);
    }

    public boolean modificar(Usuario usuario) {
        return usuarioDAO.modificar(usuario);
    }

    public boolean eliminar(Usuario usuario) {
        return usuarioDAO.eliminar(usuario);
    }
}
