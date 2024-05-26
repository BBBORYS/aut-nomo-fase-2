package com.example.zapatos.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.zapatos.DBHelper
import com.example.zapatos.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ManageShoesActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var imageUrlEditText: EditText
    private lateinit var addButton: Button
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_shoes)

        // Inicializar vistas
        nameEditText = findViewById(R.id.nameEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        priceEditText = findViewById(R.id.priceEditText)
        imageUrlEditText = findViewById(R.id.imageUrlEditText)
        addButton = findViewById(R.id.addButton)

        // Inicializar com.example.zapatos.DBHelper
        dbHelper = DBHelper(this)

        // Configurar clic del botón agregar
        addButton.setOnClickListener {
            // Obtener los detalles del zapato de los EditTexts
            val name = nameEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()
            val price = priceEditText.text.toString().toDoubleOrNull() ?: 0.0
            val imageUrl = imageUrlEditText.text.toString().trim()

            // Validar que se hayan ingresado los detalles del zapato
            if (name.isNotEmpty() && price > 0) {
                // Agregar el zapato a la base de datos en un contexto de Coroutine
                CoroutineScope(Dispatchers.IO).launch {
                    val id = dbHelper.insertShoe(name, description, price, imageUrl)
                    withContext(Dispatchers.Main) {
                        if (id > 0) {
                           showMessage()
                            clearEditTexts()
                        } else {
                             showMessage()
                        }
                    }
                }
            } else {
                  showMessage()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }

    private fun showMessage() {
    }

    private fun clearEditTexts() {
        // Limpia los EditTexts después de agregar un zapato con éxito
        nameEditText.text.clear()
        descriptionEditText.text.clear()
        priceEditText.text.clear()
        imageUrlEditText.text.clear()
    }
}
