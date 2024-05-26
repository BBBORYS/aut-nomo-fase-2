package com.example.zapatos.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zapatos.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var etRegisterUsername: EditText
    private lateinit var etRegisterPassword: EditText
    private lateinit var btnRegister: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicialización de vistas
        etRegisterUsername = findViewById(R.id.etRegisterUsername)
        etRegisterPassword = findViewById(R.id.etRegisterPassword)
        btnRegister = findViewById(R.id.btnRegister)

        // Configuración de onClickListener para el botón de registro
        btnRegister.setOnClickListener {
            val username = etRegisterUsername.text.toString().trim()
            val password = etRegisterPassword.text.toString().trim()
   if (username.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                 } else {
                Toast.makeText(this, "Por favor ingresa un nombre de usuario y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
