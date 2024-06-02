package sv.edu.ues.fia.gastospersonales.datas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "gestorPresonalApp.db"
        private const val DATABASE_VERSION = 1

        // definiendo nombres de la tabla categoria
        private const val TABLE_FRECUENCIA = "Frecuencia"
        private const val COLUMN_FRECUENCIA_IDFRECUENCIA = "idFrecuencia"
        private const val COLUMN_FRECUENCIA_NOMBRE = "nombre"

        // definiendo nombres de la tabla Recordatorio
        private const val TABLE_RECORDATORIO = "Recordatorio"
        private const val COLUMN_RECORDATORIO_IDRECORDATORIO = "idRecordatorio"
        private const val COLUMN_RECORDATORIO_IDFRECUENCIA = "idFrecuencia"
        private const val COLUMN_RECORDATORIO_NOMBRE = "nombre"
        private const val COLUMN_RECORDATORIO_FEHA_RECORDATORIO = "fecha_recordatorio"
        private const val COLUMN_RECORDATORIO_HORA_RECORDATORIO = "hora_recordatorio"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_FRECUENCIA = (
                "CREATE TABLE  $TABLE_FRECUENCIA ("
                    +"$COLUMN_FRECUENCIA_IDFRECUENCIA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    +"$COLUMN_FRECUENCIA_NOMBRE TEXT NOT NULL"
                    +")"
                )
        db.execSQL(CREATE_TABLE_FRECUENCIA)
        Log.i("DATA",CREATE_TABLE_FRECUENCIA)
        val CREATE_TABLE_RECORDATORIO = (
                "CREATE TABLE $TABLE_RECORDATORIO ("
                +"$COLUMN_RECORDATORIO_IDRECORDATORIO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +"$COLUMN_RECORDATORIO_IDFRECUENCIA INTEGER NOT NULL,"
                +"$COLUMN_RECORDATORIO_NOMBRE TEXT NOT NULL,"
                +"$COLUMN_RECORDATORIO_FEHA_RECORDATORIO DATE NOT NULL,"
                +"$COLUMN_RECORDATORIO_HORA_RECORDATORIO TEXT NOT NULL,"
                        +"FOREIGN KEY ($COLUMN_RECORDATORIO_IDFRECUENCIA) REFERENCES $TABLE_FRECUENCIA($COLUMN_FRECUENCIA_IDFRECUENCIA)"
                +")"
                )
        db.execSQL(CREATE_TABLE_RECORDATORIO)
        Log.i("DATA",CREATE_TABLE_RECORDATORIO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun comprobarBase(){
        val db = readableDatabase

        db.close()
    }
}