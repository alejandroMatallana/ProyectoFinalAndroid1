package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import matallana.alejandro.proyectofinalandroid1.Modelo.Tarea;
import matallana.alejandro.proyectofinalandroid1.R;

public class MenuTareasActivity extends AppCompatActivity {

    public static Tarea tarea = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tareas);
    }

    @Override
    public void finish() {
        super.finish();
        //tarea = null;
    }

    public void verDatos(View view) {
        TareaActivity.tipo = 1;
        Intent intent = new Intent(this, TareaActivity.class);
        startActivity(intent);
    }
}
