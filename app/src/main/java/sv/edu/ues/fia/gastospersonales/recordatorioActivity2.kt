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
import sv.edu.ues.fia.gastospersonales.datas.DataHelper


class recordatorioActivity2 : AppCompatActivity() {

    private lateinit var dbHelper: DataHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorio2)

        /*initRecyclerView()*/

        val btnAgregarRecordatorio = findViewById<Button>(R.id.btn_agregar_r)
        btnAgregarRecordatorio.setOnClickListener{
            goToCreateRecordatorio()
        }

        dbHelper = DataHelper(this)

        val recycleView = findViewById<RecyclerView>(R.id.listRecyclerRecordatorio)
        recycleView.layoutManager = LinearLayoutManager(this)

        val data = listarRecordatorios()

        val adapter = RecordatorioAdapter(data)
        recycleView.adapter = adapter
    }

    private fun listarRecordatorios():ArrayList<Recordatorio>{
        val listaRecordatorios : ArrayList<Recordatorio> = dbHelper.getAllRecordatorios()
        return listaRecordatorios
    }
    /*private fun initRecyclerView(){
        val recycleView = findViewById<RecyclerView>(R.id.listRecyclerRecordatorio)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = RecordatorioAdapter(RecordatorioProvider.recordatodioList)
    }*/

    private fun goToCreateRecordatorio(){
        val i = Intent(this, CrearRecordatorioActivity2::class.java)
        startActivity(i)
    }
}