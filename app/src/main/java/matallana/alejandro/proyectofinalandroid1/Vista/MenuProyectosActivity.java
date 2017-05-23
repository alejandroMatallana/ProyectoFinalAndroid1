package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class MenuProyectosActivity extends AppCompatActivity {

    public static Proyecto proyecto = null;
    private Button btnVerCargosProyecto, btnAbrirIntegrantesActivity, btnVerRecurosProyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyectos);
        btnVerCargosProyecto = (Button) findViewById(R.id.btnVerCargosProyecto);
        btnAbrirIntegrantesActivity = (Button) findViewById(R.id.btnAbrirIntegrantesActivity);
        btnVerRecurosProyecto = (Button) findViewById(R.id.btnVerRecurosProyecto);
        ocultarBotones();
    }

    /**
     * metodo para mostrar u ocultar botones segun el usuario que se logueo.
     * Usuario tipo integrante, oculta ver cargos, ver integrantes y ver recursos.
     * Usuario tipo Director, muestra todos los botones
     */
    public void ocultarBotones(){
        if(MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_DIRECTOR)){
            btnAbrirIntegrantesActivity.setVisibility(View.VISIBLE);
            btnVerCargosProyecto.setVisibility(View.VISIBLE);
            btnVerRecurosProyecto.setVisibility(View.VISIBLE);
        } else if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_INTEGRANTE)){
            btnAbrirIntegrantesActivity.setVisibility(View.INVISIBLE);
            btnVerCargosProyecto.setVisibility(View.INVISIBLE);
            btnVerRecurosProyecto.setVisibility(View.INVISIBLE);
        }
    }

    public void gestionarCargos(View view) {
        Intent intent = new Intent(this,ListaCargosActivity.class);
        startActivity(intent);
    }

    public void gestionProyectosVerDatos(View view) {
        Intent intent = new Intent(this,GestionProyectoActivity.class);
        startActivity(intent);
    }

    public void gestionActividades(View view){
        Intent intent = new Intent(this,ListaActividadesActivity.class);
        startActivity(intent);
    }

    public void gestionIntegrantes(View view){
        Intent intent = new Intent(this,ListaIntegrantesActivity.class);
        startActivity(intent);
    }

    public void verRecursos(View view) {
        Intent intent = new Intent(this, ListaRecursosActivity.class);
        startActivity(intent);
    }
}
