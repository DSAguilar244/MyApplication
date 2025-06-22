package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.myapplication.Entities.Perro
import kotlinx.coroutines.launch

class modificar_perro : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_modificar_perro,
            container, false)
        val id= arguments?.getInt("id")?:0
        lifecycleScope.launch {
            val

                    perro=AppDatabase.getDatabase(view.context).perroDao().findById(id)

            if(perro!=null) {

                view.findViewById<EditText>(R.id.et_modificar_perro_nombre).setText(perro.nombre)
                view.findViewById<EditText>(R.id.et_modificar_perro_raza).setText(perro.raza)
                view.findViewById<EditText>(R.id.et_modificar_perro_color).setText(perro.color)
                view.findViewById<EditText>(R.id.et_modificar_perro_peso).setText(perro.peso.toString())
                view.findViewById<Button>(R.id.btn_modificar_modificar_perro).setOnClickListener {

                    lifecycleScope.launch {
                        val perro_modificado= Perro(
                            id = perro.id,
                            nombre =

                                view.findViewById<EditText>(R.id.et_modificar_perro_nombre).text.toString(),

                            raza =

                                view.findViewById<EditText>(R.id.et_modificar_perro_raza).text.toString(),

                            color =

                                view.findViewById<EditText>(R.id.et_modificar_perro_color).text.toString(),

                            peso =

                                view.findViewById<EditText>(R.id.et_modificar_perro_peso).text.toString().toDouble(),

                            )
                        val

                                resultado=AppDatabase.getDatabase(view.context).perroDao().update(perro_modificado)

                        if(resultado>0) {
                            Toast.makeText(view.context, "Registro actualizado", Toast.LENGTH_LONG)
                                .show()
                                val bundle= Bundle().apply {
                                putInt("id",perro.id)
                            }

                            view.findNavController().navigate(R.id.action_modificar_perro_to_ver_perro,bundle)

                        }
                        else{
                            Toast.makeText(view.context, "Algo salió mal", Toast.LENGTH_LONG)

                                .show()

                        }
                    }
                }

                view.findViewById<Button>(R.id.btn_modificar_to_ver).setOnClickListener {

                    val bundle= Bundle().apply {
                        putInt("id",perro.id)
                    }

                    view.findNavController().navigate(R.id.action_modificar_perro_to_ver_perro,bundle)
                }
            }

            else{

                view.findNavController().navigate(R.id.btn_crear_to_lista)

            }
        }
        return view
    }
}