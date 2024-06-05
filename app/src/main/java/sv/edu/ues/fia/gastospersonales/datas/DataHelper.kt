package sv.edu.ues.fia.gastospersonales.datas

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import sv.edu.ues.fia.gastospersonales.Recordatorio
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

        val CREATE_TABLE_RECORDATORIO = (
                "CREATE TABLE $TABLE_RECORDATORIO ("
                +"$COLUMN_RECORDATORIO_IDRECORDATORIO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +"$COLUMN_RECORDATORIO_IDFRECUENCIA INTEGER NOT NULL,"
                +"$COLUMN_RECORDATORIO_NOMBRE TEXT NOT NULL,"
                +"$COLUMN_RECORDATORIO_FEHA_RECORDATORIO TEXT NOT NULL,"
                +"$COLUMN_RECORDATORIO_HORA_RECORDATORIO TEXT NOT NULL,"
                        +"FOREIGN KEY ($COLUMN_RECORDATORIO_IDFRECUENCIA) REFERENCES $TABLE_FRECUENCIA($COLUMN_FRECUENCIA_IDFRECUENCIA)"
                +")"
                )
        db.execSQL(CREATE_TABLE_RECORDATORIO)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS $TABLE_RECORDATORIO")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_FRECUENCIA")
            onCreate(db)
        }
    }

    /*fun comprobarBase(){
        val db = readableDatabase

        db.close()
    }*/

    fun addFrecuencia(){
        val nombreFrecuencias = listOf("una vez","diario","semanal")

        val colums = arrayOf(COLUMN_FRECUENCIA_IDFRECUENCIA, COLUMN_FRECUENCIA_NOMBRE)
        val db = readableDatabase
        var cursor: Cursor? = db.query(TABLE_FRECUENCIA, colums, null, null, null, null, null)

        if(cursor==null || cursor!!.count <=0){
            val values = ContentValues()
            val db = writableDatabase

            for(nombreFrecuencia in nombreFrecuencias){
                values.put(COLUMN_FRECUENCIA_NOMBRE, nombreFrecuencia)
                db.insert(TABLE_FRECUENCIA, null, values)
            }
        }
    }

    fun getAllFrecuencias():ArrayList<String>{
        var frecuenciaList = ArrayList<String>()

        val query = "SELECT $COLUMN_FRECUENCIA_NOMBRE FROM $TABLE_FRECUENCIA ORDER BY $COLUMN_FRECUENCIA_IDFRECUENCIA ASC"

        val db = readableDatabase
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FRECUENCIA_NOMBRE))
            frecuenciaList.add(nombre)
        }
        cursor.close()
        db.close()

        return frecuenciaList
    }

    fun addRecordatorio(idFrecuencia : Int, nombre : String, fecha : String, hora : String){
        val values = ContentValues()
        values.put(COLUMN_RECORDATORIO_IDFRECUENCIA, idFrecuencia)
        values.put(COLUMN_RECORDATORIO_NOMBRE, nombre)
        //val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        //val fechaStr = dateFormat.format(fecha)
        values.put(COLUMN_RECORDATORIO_FEHA_RECORDATORIO, fecha)
        //val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        //val horaStr = timeFormat.format(hora)
        values.put(COLUMN_RECORDATORIO_HORA_RECORDATORIO, hora)

        val db = this.writableDatabase
        db.insert(TABLE_RECORDATORIO, null, values)
        db.close()
    }

    fun getAllRecordatorios():ArrayList<Recordatorio>{
        val  recodatorioList = ArrayList<Recordatorio>()
        val selectQuery = "SELECT $TABLE_RECORDATORIO.$COLUMN_RECORDATORIO_IDRECORDATORIO, " +
                "$TABLE_RECORDATORIO.$COLUMN_RECORDATORIO_IDFRECUENCIA," +
                " $TABLE_RECORDATORIO.$COLUMN_RECORDATORIO_NOMBRE, "+
                "$TABLE_RECORDATORIO.$COLUMN_RECORDATORIO_FEHA_RECORDATORIO, " +
                "$TABLE_RECORDATORIO.$COLUMN_RECORDATORIO_HORA_RECORDATORIO " +
                "FROM $TABLE_RECORDATORIO INNER JOIN $TABLE_FRECUENCIA "+
                " ON $TABLE_RECORDATORIO.$COLUMN_RECORDATORIO_IDFRECUENCIA = $TABLE_FRECUENCIA.$COLUMN_FRECUENCIA_IDFRECUENCIA "

        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        while (cursor.moveToNext()){
            val idRecordatorio = cursor.getInt(cursor.getColumnIndexOrThrow(
                COLUMN_RECORDATORIO_IDRECORDATORIO))
            val idFrecuencia = cursor.getInt(cursor.getColumnIndexOrThrow(
                COLUMN_RECORDATORIO_IDFRECUENCIA))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_RECORDATORIO_NOMBRE))
            val fecha = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_RECORDATORIO_FEHA_RECORDATORIO))
            val hora = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_RECORDATORIO_HORA_RECORDATORIO))

            val nuevoRecordatorio = Recordatorio(idRecordatorio, idFrecuencia, nombre, fecha, hora)

            recodatorioList.add(nuevoRecordatorio)
        }
        cursor.close()
        db.close()
        Log.i("re",recodatorioList.toString())
        return recodatorioList
    }
}

