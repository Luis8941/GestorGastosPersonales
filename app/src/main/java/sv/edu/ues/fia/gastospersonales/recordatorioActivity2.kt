package sv.edu.ues.fia.gastospersonales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class recordatorioActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorio2)

        val recordatorios = arrayOf(
            "Recordatorio 1",
            "Recordatorio 2",
            "Recordatorio 3"
        )

        val listView: ListView = findViewById(R.id.listView)

        var itemAdapter = ArrayAdapter(this,R.layout.list_item_layout, recordatorios)

        listView.adapter = itemAdapter
    }
}