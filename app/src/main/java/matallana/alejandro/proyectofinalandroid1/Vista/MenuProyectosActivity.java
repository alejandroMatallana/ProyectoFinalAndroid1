package matallana.alejandro.proyectofinalandroid1.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.R;

public class MenuProyectosActivity extends AppCompatActivity {

    public static Proyecto proyecto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyectos);
    }
}
