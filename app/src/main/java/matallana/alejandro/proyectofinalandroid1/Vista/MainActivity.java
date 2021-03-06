package matallana.alejandro.proyectofinalandroid1.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import matallana.alejandro.proyectofinalandroid1.DAO.UsuarioDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class MainActivity extends AppCompatActivity {

    UsuarioDAO dao;
    EditText userName;
    EditText password;
    public static Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.password);
        dao = new UsuarioDAO(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        usuario= null;
        limpiarCampos();
    }

    public void signIn(View view){
        Intent intent = new Intent(this,UsuarioActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        String resp = dao.buscarLogin(userName.getText().toString(),password.getText().toString());
        if (resp != null) {
            usuario=dao.buscar(UsuarioDAO.IDUsuarioLogueado);
            Intent intent = new Intent(this,ListaProyectoActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,"Usuario no existe",Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarCampos() {
        userName.setText(null);
        password.setText(null);
    }
}
