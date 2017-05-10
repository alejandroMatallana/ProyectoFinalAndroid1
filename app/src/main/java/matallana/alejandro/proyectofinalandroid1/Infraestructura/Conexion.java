package matallana.alejandro.proyectofinalandroid1.Infraestructura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sebastia,Matallana,Miguel on 26/04/2017.
 */
public class Conexion extends SQLiteOpenHelper {

    private static final String database = "proyectoFinalA1.db";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 12;
    SQLiteDatabase bd;

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    public Conexion(Context context) {
        super(context, database, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
            //db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table usuario("
//                + "cedula text primary key, "
//                + "nombre text, "
//                + "apellido text, "
//                + "edad integer, "
//                + "genero integer REFERENCES genero ON DELETE CASCADE)");

        db.execSQL("CREATE TABLE \"Proyectos\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"nombre\" VARCHAR NOT NULL , \"fechaInicio\" DATETIME NOT NULL ," +
                " \"fechaFinal\" DATETIME NOT NULL , \"etapa\" DOUBLE NOT NULL)");
        db.execSQL("CREATE TABLE \"Cargos\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"nombre\" VARCHAR NOT NULL , \"descripcion\" VARCHAR NOT NULL ," +
                " \"horario\" VARCHAR NOT NULL , \"salario\" DOUBLE NOT NULL" +
                " \"idProyecto\" INTEGER REFERENCES Proyectos)");
        db.execSQL("CREATE TABLE \"Usuarios\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"tipoDocumento\" VARCHAR NOT NULL , \"numeroDocumento\" INTEGER NOT NULL  UNIQUE ," +
                " \"nombres\" VARCHAR NOT NULL , \"apellidos\" VARCHAR NOT NULL , " +
                "\"fechaNacimiento\" DATETIME NOT NULL , \"pass\" VARCHAR NOT NULL , " +
                "\"usuario\" VARCHAR NOT NULL  UNIQUE , \"correoElectronico\" VARCHAR NOT NULL , " +
                "\"tipoUsuario\" VARCHAR NOT NULL)");
        db.execSQL("CREATE TABLE \"Actividades\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"nombre\" VARCHAR NOT NULL , \"descripcion\" VARCHAR NOT NULL ," +
                " \"fechaInicio\" DATETIME NOT NULL , \"fechaFinal\" DATETIME NOT NULL ," +
                " \"idResponsable\" INTEGER REFERENCES Usuarios," +
                " \"idProyecto\" INTEGER REFERENCES Proyectos)");
        db.execSQL("CREATE TABLE \"Tareas\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"nombre\" VARCHAR NOT NULL , \"porcentajeDesarrollo\" DOUBLE NOT NULL ," +
                " \"fechaInicio\" DATETIME NOT NULL , \"fechaFinal\" DATETIME NOT NULL ," +
                " \"estado\" VARCHAR NOT NULL , \"idActividad\" INTEGER REFERENCES Actividades)");
        db.execSQL("CREATE TABLE \"Recursos\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"nombre\" VARCHAR NOT NULL , \"cantidad\" INTEGER NOT NULL ," +
                " \"descripcion\" VARCHAR NOT NULL , \"ubicacion\" VARCHAR NOT NULL," +
                " \"idTarea\" INTEGER REFERENCES Tareas) ");
        db.execSQL("CREATE TABLE \"Reuniones\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"coordenadas\" VARCHAR NOT NULL , \"mensaje\" VARCHAR NOT NULL ," +
                " \"tematica\" VARCHAR NOT NULL, \"idProyecto\" INTEGER REFERENCES Proyectos)");
        db.execSQL("CREATE TABLE \"ProyectosIntegrantes\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"idCargo\" INTEGER REFERENCES Cargos, \"idProyecto\" INTEGER REFERENCES Proyectos," +
                " \"idUsuario\" INTEGER REFERENCES Usuarios)");
        db.execSQL("CREATE TABLE \"TareasRecursos\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " \"idTarea\" INTEGER REFERENCES Tareas, \"idRecurso\" INTEGER REFERENCES Recursos)");

        db.execSQL("INSERT INTO Cargos VALUES ('Lider','Lider de Proyecto','8 a 12 - 14 a 18',1200000)");

        //db.execSQL("INSERT INTO Usuarios VALUES ('Cedula',123456789,'alejandro','mata', '2017/05/10', '1234')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists Usuarios");
        db.execSQL("drop table if exists Tareas");
        db.execSQL("drop table if exists Reuniones");
        db.execSQL("drop table if exists Recursos");
        db.execSQL("drop table if exists Proyectos");
        db.execSQL("drop table if exists Cargos");
        db.execSQL("drop table if exists Actividades");
        onCreate(db);
    }

    public void cerrarConexion() {
        bd.close();
    }

    public boolean insert(String tabla, ContentValues registro) {
        try {
            // Objeto para lectura y escritura en la base de datos
            bd = this.getWritableDatabase();
            // Se hace un insert indicando la tabla y mandando los datos, el
            // null es los campos que no se van a registrar
            int res = (int) bd.insert(tabla, null, registro);
            cerrarConexion();
            if (res != -1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String tabla, String condicion) {
        bd = this.getWritableDatabase();
        int cant = bd.delete(tabla, condicion, null);
        cerrarConexion();
        if (cant >= 1) {
            return true;
        } else {
            return false;
        }
    }




    public boolean update(String tabla, String condicion,
                          ContentValues registro) {
        try {
            bd = this.getWritableDatabase();
            int cant = bd.update(tabla, registro, condicion, null);
            cerrarConexion();
            if (cant == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Cursor search(String consulta) {
        try {
            // Objeto para lectura y escritura en la base de datos
            bd = this.getWritableDatabase();
            // Definimos un objeto de tipo cursor que almacena la info de la
            // base de datos, ademas ejecutamos una consulta sql
            Cursor fila = bd.rawQuery(consulta, null);
            return fila;
        } catch (Exception e) {
            cerrarConexion();
            return null;
        }
    }
}