package com.ejemplo1.apipayphone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton


class HistorialAdapter(items: List<HistorialPagoItem>, context: Context) :
    RecyclerView.Adapter<HistorialAdapter.ViewHolder>() {
    private val hData: List<HistorialPagoItem>
    private val mInflater: LayoutInflater
    private val context: Context
    override fun getItemCount(): Int {
        return hData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.historial_pagoitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(hData[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var subtitle: TextView
        var btnVerEstado: MaterialButton
        fun bindData(historialPagoItem: HistorialPagoItem) {
            title.setText(historialPagoItem.titulo)
            subtitle.setText(historialPagoItem.titulo)
            btnVerEstado.text = "Ver Estado"
            btnVerEstado.setOnClickListener {
                Toast.makeText(
                    context,
                    historialPagoItem.idTransaction,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        init {
            title = itemView.findViewById(R.id.pago_item_title)
            subtitle = itemView.findViewById(R.id.pago_item_subtitle)
            btnVerEstado = itemView.findViewById(R.id.pago_item_btn)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
        this.context = context
        hData = items
    }
}
