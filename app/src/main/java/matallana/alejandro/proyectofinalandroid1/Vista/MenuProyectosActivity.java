package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import matallana.alejandro.proyectofinalandroid1.Modelo.Proyecto;
import matallana.alejandro.proyectofinalandroid1.R;

public class MenuProyectosActivity extends AppCompatActivity {

    public static Proyecto proyecto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyectos);
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
}
