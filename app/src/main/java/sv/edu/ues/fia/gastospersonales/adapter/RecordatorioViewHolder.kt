package sv.edu.ues.fia.gastospersonales.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sv.edu.ues.fia.gastospersonales.R
import sv.edu.ues.fia.gastospersonales.Recordatorio

class RecordatorioViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val  descripcion = view.findViewById<TextView>(R.id.descripcion)
    val  fecha_vencimiento = view.findViewById<TextView>(R.id.fecha_vencimiento)
    val  monto = view.findViewById<TextView>(R.id.monto)
    fun render(recordatorio: Recordatorio) {
        descripcion.text = recordatorio.descripcion
        fecha_vencimiento.text = recordatorio.fecha_vencimiento
        monto.text = recordatorio.monto
    }
}