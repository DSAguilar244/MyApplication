package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Entities.Perro

class lista_perro_adapter(private var lista:List<Perro>):
    RecyclerView.Adapter<PerroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PerroViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.lista_perro_item, parent, false)
        return PerroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: PerroViewHolder, position: Int) {
        val perro = lista[position]
        holder.nombre_perro.text = perro.nombre
        holder.btn_ver_perro.setOnClickListener {
            val bundle = Bundle()
                .apply {
                    putInt("id", perro.id)
                }
            holder
                .itemView
                .findNavController()
                .navigate(R.id.action_lista_perros_to_ver_perro, bundle)

        }
    }
}
class PerroViewHolder(view: View): RecyclerView.ViewHolder(view){
    var nombre_perro=
        view.findViewById<TextView>(R.id.tv_lista_perro_nombre)
    var btn_ver_perro=
        view.findViewById<Button>(R.id.btn_lista_perro_ver)
}