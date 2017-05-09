package matallana.alejandro.proyectofinalandroid1.Vista;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import matallana.alejandro.proyectofinalandroid1.DAO.UsuarioDAO;
import matallana.alejandro.proyectofinalandroid1.Modelo.Usuario;
import matallana.alejandro.proyectofinalandroid1.R;

public class UsuarioActivity extends AppCompatActivity {

    Usuario usuario = null;
    UsuarioDAO dao;
    Spinner tipoDocumento;
    EditText numeroDoc;
    EditText nombre;
    EditText apellido;
    EditText fechaNacimiento;
    EditText correo;
    EditText password;
    Spinner tipoUsuario;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        tipoDocumento = (Spinner) findViewById(R.id.tipoDocumento);
        numeroDoc = (EditText)findViewById(R.id.numDocumento);
        nombre = (EditText)findViewById(R.id.nombreUSu);
        apellido = (EditText)findViewById(R.id.apellidoUsu);
        fechaNacimiento = (EditText)findViewById(R.id.fechaNacimiento);
        correo = (EditText)findViewById(R.id.correoUsu);
        password = (EditText)findViewById(R.id.contrasenaUsu);
        tipoUsuario = (Spinner) findViewById(R.id.tipoUsu);
        username = (EditText) findViewById(R.id.usernameUsu);

        // En este parte se ejecuta lo del datePicker para que se muestre en la ventana
        // y se cirre se le puede configurar el formato en el que se captura
        fechaNacimiento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                DatePickerDialog mDatePicker=new DatePickerDialog(UsuarioActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        fechaNacimiento.setText(selectedyear+"/"+selectedmonth+"/"+selectedday);
                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
        dao = new UsuarioDAO(this);
        cargarSpinner();
    }

    public void crear(View view){
        if (tipoDocumento.getSelectedItem().toString().isEmpty() || numeroDoc.getText().toString().isEmpty() ||
                nombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty() ||
                fechaNacimiento.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                username.getText().toString().isEmpty() || correo.getText().toString().isEmpty() ||
                tipoUsuario.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this,"Debe ingresar todos los campos",Toast.LENGTH_SHORT).show();
        } else {
            if (dao.buscar(numeroDoc.getText().toString()) == null) {
                if (!dao.verificarUsername(username.getText().toString())) {
                    Calendar fechaNaci = Calendar.getInstance();
                    String[] datos = fechaNacimiento.getText().toString().split("/");
                    fechaNaci.set(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
                    Usuario usuario = new Usuario(tipoDocumento.getSelectedItem().toString(),
                            Integer.parseInt(numeroDoc.getText().toString()), nombre.getText().toString(),
                            apellido.getText().toString(), fechaNaci.getTime(),
                            password.getText().toString(), username.getText().toString(),
                            correo.getText().toString(), tipoUsuario.getSelectedItem().toString());
                    boolean resp = dao.guardar(usuario);
                    if (resp) {
                        Toast.makeText(this, "Se registró el usuario", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    }
                } else {
                    Toast.makeText(this, "El Username que ingreso ya existe", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,"La cedula del usuario que desea registrar ya existe",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void buscar(View view){
        if (numeroDoc.getText().toString().isEmpty()) {
            Toast.makeText(this,"Debe ingresar la cedula del usuario que va a buscar",Toast.LENGTH_SHORT).show();
        } else {
            usuario = dao.buscar(numeroDoc.getText().toString());
            if (usuario != null) {
                if (usuario.getTipoDocumento().equalsIgnoreCase("Cedula")) {
                    tipoDocumento.setSelection(1);
                } else if (usuario.getTipoDocumento().equalsIgnoreCase("Cedula Extranjera")) {
                    tipoDocumento.setSelection(2);
                } else if (usuario.getTipoDocumento().equalsIgnoreCase("Tarjeta Identidad")) {
                    tipoDocumento.setSelection(3);
                } else if (usuario.getTipoDocumento().equalsIgnoreCase("Pasaporte")) {
                    tipoDocumento.setSelection(4);
                }
                nombre.setText(usuario.getNombres());
                apellido.setText(usuario.getApellidos());
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                fechaNacimiento.setText(format.format(usuario.getFechaNacimiento()));
                correo.setText(usuario.getCorreoElectronico());
                password.setText(usuario.getPass());
                if (usuario.getTipoUsuario().equalsIgnoreCase("Director de proyecto")) {
                    tipoUsuario.setSelection(1);
                } else {
                    tipoUsuario.setSelection(2);
                }
                username.setText(usuario.getUsuario());
            } else {
                Toast.makeText(this,"El usuario que busca por la cedula " + numeroDoc.getText().toString() +
                        " no existe",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminar(View view){
        if(usuario != null) {
            if(dao.eliminar(usuario)) {
                Toast.makeText(this, "Se elimino el usuario correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "No se puedo eliminar el usuario", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe primero buscar un Usuario", Toast.LENGTH_SHORT).show();
        }
    }

    public void editar(View view){
        if(usuario != null) {
            if((!username.getText().toString().equals(usuario.getUsuario()) && !dao.verificarUsername(username.getText().toString())) || username.getText().toString().equals(usuario.getUsuario())) {
                if(Integer.parseInt(numeroDoc.getText().toString()) == usuario.getNumeroDocumento() /*|| (!numeroDoc.getText().toString().equals(usuario.getNumeroDocumento()) && dao.buscar(numeroDoc.getText().toString()) == null) */) {
                    Calendar fechaNaci = Calendar.getInstance();
                    String[] datos = fechaNacimiento.getText().toString().split("/");
                    fechaNaci.set(Integer.parseInt(datos[0]), (Integer.parseInt(datos[1]))-1, Integer.parseInt(datos[2]));
                    usuario.setTipoDocumento(tipoDocumento.getSelectedItem().toString());
                    usuario.setNumeroDocumento(Integer.parseInt(numeroDoc.getText().toString()));
                    usuario.setNombres(nombre.getText().toString());
                    usuario.setApellidos(apellido.getText().toString());
                    usuario.setFechaNacimiento(fechaNaci.getTime());
                    usuario.setPass(password.getText().toString());
                    usuario.setUsuario(username.getText().toString());
                    usuario.setCorreoElectronico(correo.getText().toString());
                    usuario.setTipoUsuario(tipoUsuario.getSelectedItem().toString());
                    dao.modificar(usuario);
                    Toast.makeText(this, "Se modifico el usuario con exito", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else {
                    Toast.makeText(this, "La cedula ingresada ya existe", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "El username ingresado ya existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe primero buscar un Usuario", Toast.LENGTH_SHORT).show();
        }
    }

    public void cargarSpinner() {
        ArrayList<String> tiposDocumento = new ArrayList<>();
        tiposDocumento.add("Seleccione Tipo Documento");
        tiposDocumento.add("Cedula");
        tiposDocumento.add("Cedula Extranjera");
        tiposDocumento.add("Tarjeta Identidad");
        tiposDocumento.add("Pasaporte");
        ArrayAdapter<String> adapterDocumento = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tiposDocumento);
        adapterDocumento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDocumento.setAdapter(adapterDocumento);

        ArrayList<String> tiposUsuario = new ArrayList<>();
        tiposUsuario.add("Seleccione Tipo Usuario");
        tiposUsuario.add("Director de proyecto");
        tiposUsuario.add("Integrante del proyecto");
        ArrayAdapter<String> adapterUsuario = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tiposUsuario);
        adapterUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoUsuario.setAdapter(adapterUsuario);
    }

    public void limpiarCampos() {
        tipoDocumento.setSelection(0);
        numeroDoc.setText(null);
        nombre.setText(null);
        apellido.setText(null);
        fechaNacimiento.setText(null);
        correo.setText(null);
        password.setText(null);
        tipoUsuario.setSelection(0);
        username.setText(null);
        usuario = null;
    }
}
