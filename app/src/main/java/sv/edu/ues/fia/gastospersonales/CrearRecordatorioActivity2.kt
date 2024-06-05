package sv.edu.ues.fia.gastospersonales

import android.app.DatePickerDialog
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sv.edu.ues.fia.gastospersonales.datas.DataHelper
import java.util.Calendar

class CrearRecordatorioActivity2 : AppCompatActivity() {

    private lateinit var dbHelper: DataHelper

    private lateinit var spinnerFrecuencias : Spinner
    private lateinit var editTxtdigiteNom : EditText
    private lateinit var etFecha : EditText
    private lateinit var editTxtTime : EditText
    private lateinit var btnGuardar : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_recordatorio2)

        /*val btnGuardar =  findViewById<Button>(R.id.btn_guardar)

        val spinnerFrecuencia = findViewById<Spinner>(R.id.spinner_frecu)

        val optionDoctor = arrayOf("Una vez","Diario","Semanal","Mensual","Año")
        spinnerFrecuencia.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionDoctor)

        btnGuardar.setOnClickListener{
            Toast.makeText(applicationContext, "Recordatorio Registrado Exitosamente", Toast.LENGTH_SHORT).show()
            finish()
        }*/

        //llamando a la base de datos
        dbHelper = DataHelper(this)

        //dbHelper.comprobarBase()

        //Accdiendo a los valores de los controles
        spinnerFrecuencias = findViewById(R.id.spinner_frecu)
        editTxtdigiteNom = findViewById(R.id.digite_nom)
        etFecha = findViewById(R.id.et_fecha)
        editTxtTime = findViewById(R.id.editTextTime)


        btnGuardar = findViewById(R.id.btn_guardar)

        btnGuardar.setOnClickListener {
            val nombre = editTxtdigiteNom.text.toString()
            val idFrecuencia = spinnerFrecuencias.selectedItemId.toInt() + 1
            val fechaRe = etFecha.text.toString()
            val horaRe = editTxtTime.text.toString()

            dbHelper.addRecordatorio(idFrecuencia, nombre, fechaRe, horaRe)

            Toast.makeText(this, "Recordatorio Agregado", Toast.LENGTH_SHORT).show()
            clearCamposRecordatorio()
        }

        //Metodo para el llenado del Spinner Frecuencia
        llenarTablaFrecuencia()
        llenarSpinner()
    }

    fun onClickScheduledDate(v: View?){
        val etScheduledDate = findViewById<EditText>(R.id.et_fecha)
        val selectedCalendar = Calendar.getInstance()
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val day = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        var listener = DatePickerDialog.OnDateSetListener{ datePicker, y, m, d ->
            etScheduledDate.setText("$y-$m-$d")
        }
        DatePickerDialog(this, listener, year, month, day).show()
    }

    fun llenarTablaFrecuencia(){
        dbHelper.addFrecuencia()
    }

    private fun llenarSpinner(){
        val nombreFrecuencia : ArrayList<String> = dbHelper.getAllFrecuencias()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombreFrecuencia)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrecuencias.adapter = adapter
    }

    private fun clearCamposRecordatorio(){
        spinnerFrecuencias = findViewById(R.id.spinner_frecu)
        editTxtdigiteNom = findViewById(R.id.digite_nom)
        etFecha = findViewById(R.id.et_fecha)
        editTxtTime = findViewById(R.id.editTextTime)

        editTxtdigiteNom.setText("")
        etFecha.setText("")
        editTxtTime.setText("")
        spinnerFrecuencias.setSelection(0)
    }
}