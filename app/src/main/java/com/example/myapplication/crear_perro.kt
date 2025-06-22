package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Entities.Perro
import kotlinx.coroutines.launch

class crear_perro : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_crear_perro,
            container, false)

        view
            .findViewById<Button>(R.id.btn_crear_nuevo_perro)
            .setOnClickListener {
                var nombre= view.findViewById<EditText>(R.id.et_nuevo_perro_nombre)
                    .text
                    .toString()
                var raza= view.findViewById<EditText>(R.id.et_nuevo_perro_raza)
                    .text
                    .toString()
                var color= view.findViewById<EditText>(R.id.et_nuevo_perro_color)
                    .text
                    .toString()
                var peso= view.findViewById<EditText>(R.id.et_nuevo_perro_peso)
                    .text
                    .toString()
                    .toDouble()
                var nuevo_perro = Perro(
                    nombre =nombre,
                    raza = raza,
                    color = color,
                    peso = peso
                )
                lifecycleScope.launch {
                    var respuesta=AppDatabase
                        .getDatabase(view.context)
                        .perroDao()
                        .store(nuevo_perro)
                    if(respuesta >0)
                    {
                        Toast.makeText(
                            context,
                            "Perro guardado exitosamente",
                            Toast.LENGTH_LONG).show()
                        findNavController()
                            .navigate(R.id.action_crear_perro_to_lista_perros)
                    }
                    else{
                        Toast.makeText(
                            context,
                            "Error al guardar el perrito",
                            Toast.LENGTH_LONG).show()
                    }
                }

            }
        return view
    }
}