package sv.edu.ues.fia.gastospersonales.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sv.edu.ues.fia.gastospersonales.R
import sv.edu.ues.fia.gastospersonales.Recordatorio

class RecordatorioAdapter(private val rList: List<Recordatorio>) : RecyclerView.Adapter<RecordatorioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordatorioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecordatorioViewHolder(layoutInflater.inflate(R.layout.list_element_recordatorio, parent, false))
    }

    override fun getItemCount(): Int = rList.size

    override fun onBindViewHolder(holder: RecordatorioViewHolder, position: Int) {
        val item = rList[position]
        holder.render(item)
    }
}