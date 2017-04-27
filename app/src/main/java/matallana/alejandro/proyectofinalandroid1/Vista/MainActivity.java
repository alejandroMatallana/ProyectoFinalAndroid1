package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import matallana.alejandro.proyectofinalandroid1.DAO.UsuarioDAO;
import matallana.alejandro.proyectofinalandroid1.R;

public class MainActivity extends AppCompatActivity {

    UsuarioDAO dao;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.password);
        dao = new UsuarioDAO(this);
    }

    public void login(View view){

        Intent intent = new Intent(this,UsuarioActivity.class);
        startActivity(intent);
    }

}
