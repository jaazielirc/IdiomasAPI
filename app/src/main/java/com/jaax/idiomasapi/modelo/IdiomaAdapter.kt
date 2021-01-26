package com.jaax.idiomasapi.modelo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaax.idiomasapi.R

class IdiomaAdapter: RecyclerView.Adapter<IdiomaAdapter.ViewHolder>() {
    private var listaIdiomas = ArrayList<Idioma>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.idiomas_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = listaIdiomas[position].id.toString()
        holder.codigoIso.text = listaIdiomas[position].codigoISO
        holder.nombre.text = listaIdiomas[position].nombre
        holder.hablantes.text = listaIdiomas[position].numHablantes.toString()
    }

    override fun getItemCount(): Int {
        return listaIdiomas.size
    }

    fun addListaIdiomas(lista: ArrayList<Idioma>) {
        listaIdiomas.addAll(lista)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        var id: TextView = view!!.findViewById(R.id.id)
        var codigoIso: TextView = view!!.findViewById(R.id.codigoISO)
        var nombre: TextView = view!!.findViewById(R.id.nombre)
        var hablantes: TextView = view!!.findViewById(R.id.numHablantes)
    }
}