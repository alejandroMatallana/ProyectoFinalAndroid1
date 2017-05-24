package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class MenuActividadesActivity extends AppCompatActivity {

    public static Actividad actividad=null;
    private Button listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_actividades);
        listado = (Button) findViewById(R.id.listado);
        if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_DIRECTOR)) {
            listado.setVisibility(View.GONE);
        }
    }

    public void verdatos(View view){
        Intent intent = new Intent(this,ActividadesActivity.class);
        startActivity(intent);
    }

    public void tareasActividad(View view){
        Intent intent = new Intent(this,ListaTareasActivity.class);
        startActivity(intent);
    }

    public void listadoRecursos(View view) {
        Intent intent = new Intent(this,ListaRecursosActivity.class);
        startActivity(intent);
    }
}
