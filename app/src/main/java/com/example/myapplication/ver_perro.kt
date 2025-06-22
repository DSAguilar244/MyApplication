package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import kotlinx.coroutines.launch

class ver_perro : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ver_perro, container, false)
        val id = arguments?.getInt("id") ?: 0
        lifecycleScope.launch {
            val perro = AppDatabase.getDatabase(view.context).perroDao().findById(id)
            if (perro != null) {
                view.findViewById<TextView>(R.id.tv_ver_perro_nombre).setText(perro.nombre)
                view.findViewById<TextView>(R.id.tv_ver_perro_raza).setText(perro.raza)
                view.findViewById<TextView>(R.id.tv_ver_perro_color).setText(perro.color)
                view.findViewById<TextView>(R.id.tv_ver_perro_peso).setText(perro.peso.toString())
                view.findViewById<Button>(R.id.btn_ver_editar_perro).setOnClickListener {
                    val bundle = Bundle().apply {
                        putInt("id", perro.id)
                    }
                    view.findNavController()
                        .navigate(R.id.action_ver_perro_to_modificar_perro, bundle)
                }
                view.findViewById<Button>(R.id.btn_ver_atras_perro).setOnClickListener {
                    view.findNavController().navigate(R.id.action_ver_perro_to_lista_perros)
                }
                view.findViewById<Button>(R.id.btn_ver_eliminar_perro).setOnClickListener {
                    lifecycleScope.launch {var res = AppDatabase.getDatabase(view.context).perroDao().delete(perro)
                        if (res > 0) {
                            Toast.makeText(view.context, "Registro borrado exitosamente",Toast.LENGTH_LONG).show()

                            view.findNavController().navigate(R.id.action_ver_perro_to_lista_perros)
                        } else {
                            Toast.makeText(view.context, "No se ha podido borrar el registro",Toast.LENGTH_LONG).show()

                        }
                    }
                }
            }
        }
        return view
    }
}