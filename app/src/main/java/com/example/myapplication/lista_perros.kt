package com.example.myapplication
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class lista_perros : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_lista_perros,
            container, false
        )
        val rv_perros = view.findViewById<RecyclerView>(R.id.rv_perros)
        view.findViewById<FloatingActionButton>(R.id.btn_lista_perro_to_crear_perro)
            .setOnClickListener{
                view.findNavController().navigate(R.id.action_lista_perros_to_crear_perro)
            }
        lifecycleScope.launch {
            var db=AppDatabase.getDatabase(requireContext())
            var listadoDePerros= db.perroDao().all()
            rv_perros.layoutManager =
                LinearLayoutManager(requireContext())
            rv_perros.adapter=lista_perro_adapter(listadoDePerros)
        }
        return view
    }
}