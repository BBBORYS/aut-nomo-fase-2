package com.example.zapatos.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zapatos.R
import com.example.zapatos.Shoe
import com.example.zapatos.adapters.ShoeAdapter

class ViewShoesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var shoeAdapter: ShoeAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_shoes)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener la lista de zapatos (supongamos que se pasa como parámetro)
        val shoesList = getShoesList()

        // Inicializar y configurar el adaptador del RecyclerView
        shoeAdapter = ShoeAdapter(this, shoesList)
        recyclerView.adapter = shoeAdapter
    }

    // Método ficticio para obtener la lista de zapatos (debería ser reemplazado con la implementación real)
    private fun getShoesList(): List<Shoe> {
        return emptyList()
    }
}
