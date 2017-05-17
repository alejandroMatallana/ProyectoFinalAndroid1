package matallana.alejandro.proyectofinalandroid1.Controlador;

import android.app.Activity;

import java.util.ArrayList;

import matallana.alejandro.proyectofinalandroid1.DAO.UsuarioDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;

/**
 * Created by sebastiancardona on 17/05/17.
 */

public class ControllerListaAgregarIntegranteActivity {

    private Activity activity;

    /**
     * Constructor...
     * @param activity
     */
    public ControllerListaAgregarIntegranteActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * metodo para listar todos los usuarios registrados como tipo integrante
     * @return
     */
    public ArrayList<Usuario> listarUsuariosTipoIntegrante(){
        UsuarioDAO dao = new UsuarioDAO(activity);
        return dao.listarIntegrantes();
    }

    /**
     * metodo para buscar un usuario por su numero de documento o identificacion
     * @param identificacion, numero de documento o identificacion del usuario
     * @return
     */
    public Usuario buscarUsuario(int identificacion){
        UsuarioDAO dao = new UsuarioDAO(activity);
        return dao.buscar(identificacion);
    }
}
