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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_recordatorio2)

        val btnGuardar =  findViewById<Button>(R.id.btn_guardar)

        val spinnerFrecuencia = findViewById<Spinner>(R.id.spinner_frecu)

        val optionDoctor = arrayOf("Una vez","Diario","Semanal","Mensual","Año")
        spinnerFrecuencia.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionDoctor)

        btnGuardar.setOnClickListener{
            Toast.makeText(applicationContext, "Recordatorio Registrado Exitosamente", Toast.LENGTH_SHORT).show()
            finish()
        }

        //llamando a la base de datos
        dbHelper = DataHelper(this)

        dbHelper.comprobarBase()
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
}