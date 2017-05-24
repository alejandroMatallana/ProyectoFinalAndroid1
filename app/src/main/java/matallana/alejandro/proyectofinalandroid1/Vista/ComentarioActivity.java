package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import matallana.alejandro.proyectofinalandroid1.Controlador.ControllerComentario;
import matallana.alejandro.proyectofinalandroid1.Modelo.ComentarioTarea;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class ComentarioActivity extends AppCompatActivity {

    public static ComentarioTarea comentario = null;
    private EditText titulo, comentarios, fecha;
    private Button crear;
    private ControllerComentario controllerComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        titulo = (EditText) findViewById(R.id.titulo);
        comentarios = (EditText) findViewById(R.id.comentario);
        fecha = (EditText) findViewById(R.id.fecha);
        crear = (Button) findViewById(R.id.agregar);
        controllerComentario = new ControllerComentario(this);
        if (MainActivity.usuario.getTipoUsuario().equalsIgnoreCase(Usuario.TIPO_DIRECTOR)) {
            titulo.setEnabled(false);
            comentarios.setEnabled(false);
            crear.setVisibility(View.GONE);
            fecha.setEnabled(false);
            cargarComentario();
        } else {
            fecha.setVisibility(View.GONE);
        }
    }

    public void crear(View view) {
        if (titulo.getText().toString().isEmpty() || comentarios.getText().toString().isEmpty()) {
            Toast.makeText(this,"Debe ingresar todos los datos",Toast.LENGTH_SHORT).show();
        } else {
            ComentarioTarea comentarioTarea = new ComentarioTarea();
            comentarioTarea.setComentario(comentarios.getText().toString());
            comentarioTarea.setFecha(new Date());
            comentarioTarea.setTitulo(titulo.getText().toString());
            comentarioTarea.setTarea(TareaActivity.tarea);
            if (controllerComentario.crear(comentarioTarea)) {
                Toast.makeText(this,"Se registro el comentario",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this,"Ocurrior un error al registrar el comentario",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void cargarComentario() {
        titulo.setText(comentario.getTitulo());
        comentarios.setText(comentario.getComentario());
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        fecha.setText(format.format(comentario.getFecha()));
    }

    public void limpiarCampos() {
        titulo.setText(null);
        comentarios.setText(null);
    }
}
