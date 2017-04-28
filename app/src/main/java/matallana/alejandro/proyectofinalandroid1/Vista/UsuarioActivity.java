package matallana.alejandro.proyectofinalandroid1.Vista;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import matallana.alejandro.proyectofinalandroid1.R;

public class UsuarioActivity extends AppCompatActivity {

    Spinner tipoDocumento;
    EditText numeroDoc;
    EditText nombre;
    EditText apellido;
    EditText fechaNacimiento;
    EditText correo;
    EditText password;
    Spinner tipoUsuario;

    Calendar myCalendar = Calendar.getInstance();


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
        password = (EditText)findViewById(R.id.password);
        tipoUsuario = (Spinner) findViewById(R.id.tipoUsu);

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
    }

    public void crear(View view){

    }

    public void buscar(View view){

    }

    public void eliminar(View view){

    }

    public void editar(View view){

    }
}
