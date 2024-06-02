package sv.edu.ues.fia.gastospersonales

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sv.edu.ues.fia.gastospersonales.adapter.RecordatorioAdapter


class recordatorioActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorio2)

        initRecyclerView()

        val btnAgregarRecordatorio = findViewById<Button>(R.id.btn_agregar_r)
        btnAgregarRecordatorio.setOnClickListener{
            goToCreateRecordatorio()
        }
    }

    private fun initRecyclerView(){
        val recycleView = findViewById<RecyclerView>(R.id.listRecyclerRecordatorio)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = RecordatorioAdapter(RecordatorioProvider.recordatodioList)
    }

    private fun goToCreateRecordatorio(){
        val i = Intent(this, CrearRecordatorioActivity2::class.java)
        startActivity(i)
    }
}