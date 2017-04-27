package matallana.alejandro.proyectofinalandroid1.Infraestructura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AlejandroM on 29/03/2017.
 */
public class Conexion extends SQLiteOpenHelper {


    private static final String dataBase = "proyectoFinalA1.db";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;
    SQLiteDatabase bd;


    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory){
        super(context, name, factory, version);
    }

    public Conexion(Context context){
        super(context, dataBase, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table usuario("+"cedula text primary key, " + "nombre text," +  " apellido text ," + "edad integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
        onCreate(db);
    }

    public void cerrarConexion(){bd.close();}


    public  boolean ejecutarInsert(String tabla , ContentValues registro){
        try{
            // Objeto para lectura y escritura de la BD
             bd = this.getWritableDatabase();
            // Se hace un insert indicandole la tabla y mandandole los parametros
            // null es los campos que no se van a registrar
            int res = (int) bd.insert(tabla, null, registro);
            cerrarConexion();
            if (res !=-1){
                return true;
            } else {
                return false;
            }

        } catch (Exception e){
            return false;
        }
    }

    public boolean  ejecutarUpdate(String tabla, String condicion, ContentValues registro){
        try{

            bd = this.getWritableDatabase();
             int cant = bd.update(tabla, registro, condicion, null);

            cerrarConexion();
            if (cant == 1){
                return true;
            } else {
                return false;
            }


        }catch (Exception e){
            return false;
        }
    }

    public Cursor ejecutarSearch (String consulta){
        try{
            //Objeto para lectura y escritura en la BD
            bd = this.getWritableDatabase();

            //Definimos un objeto de tipo cursor que almacena la info de la BD , ademas ejecutamos una consulta sql
            //en el null se especifican  los parametros, dado el caso que en el sql no, como con
            Cursor fila = bd.rawQuery(consulta, null);
            return fila;

        } catch (Exception e){
            cerrarConexion();
            return null;
        }
    }

    public  boolean ejecutarDelete(String tabla, String condicion){
        bd = this.getWritableDatabase();


        int cant = bd.delete(tabla, condicion, null);
        cerrarConexion();

        if (cant >= 1){
            return true;
        } else {
            return false;
        }

    }
}
