package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import matallana.alejandro.proyectofinalandroid1.Modelo.Actividad;
import matallana.alejandro.proyectofinalandroid1.R;

public class MenuActividadesActivity extends AppCompatActivity {


    public static Actividad actividad=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_actividades);
    }

    public void tareasActividad(View view){
        Intent intent = new Intent(this,ListaTareasActivity.class);
        startActivity(intent);
    }
}
