package sv.edu.ues.fia.gastospersonales.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sv.edu.ues.fia.gastospersonales.R
import sv.edu.ues.fia.gastospersonales.Recordatorio

class RecordatorioViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val  nombreRe = view.findViewById<TextView>(R.id.nombre_r)
    val  fechaRe = view.findViewById<TextView>(R.id.fecha_r)
    val  montoRe = view.findViewById<TextView>(R.id.monto)
    fun render(recordatorio: Recordatorio) {
        nombreRe.text = recordatorio.nombreR
        fechaRe.text = recordatorio.fecha
        montoRe.text = recordatorio.monto.toString()
    }
}