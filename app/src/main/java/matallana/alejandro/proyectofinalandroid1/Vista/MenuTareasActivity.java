package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class MenuTareasActivity extends AppCompatActivity {

    public static Tarea tarea = null;
    private Button datos,comentario,verComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tareas);
        datos = (Button) findViewById(R.id.datos);
        comentario = (Button) findViewById(R.id.agregarComentario);
        verComentario = (Button) findViewById(R.id.listarComentario);
        if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_DIRECTOR)) {
            comentario.setVisibility(View.GONE);
        } else {
            verComentario.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void verDatos(View view) {
        TareaActivity.tipo = 1;
        Intent intent = new Intent(this, TareaActivity.class);
        startActivity(intent);
    }

    public void agregarComentario(View view) {
        Intent intent = new Intent(this, ComentarioActivity.class);
        startActivity(intent);
    }

    public void listarComentarios(View view) {
        Intent intent = new Intent(this, ListaComentariosActivity.class);
        startActivity(intent);
    }
}
