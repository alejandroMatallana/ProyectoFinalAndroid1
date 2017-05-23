package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import matallana.alejandro.proyectofinalandroid1.R;

public class MenuRecursosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_recursos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (RecursoActivity.tipo == 2) {
            RecursoActivity.tipo = 0;
            RecursoActivity.recurso = null;
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RecursoActivity.recurso = null;
    }

    public void verDatos(View view) {
        RecursoActivity.tipo = 1;
        Intent intent = new Intent(this,RecursoActivity.class);
        startActivity(intent);
    }

    public void agregar(View view) {
        ListaRecursosTareasActivity.tipo = 0;
        Intent intent = new Intent(this,ListaRecursosTareasActivity.class);
        startActivity(intent);
    }

    public void quitar(View view) {
        ListaRecursosTareasActivity.tipo = 1;
        Intent intent = new Intent(this,ListaRecursosTareasActivity.class);
        startActivity(intent);
    }
}
