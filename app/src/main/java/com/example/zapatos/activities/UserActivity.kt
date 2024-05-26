package com.example.zapatos.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.zapatos.R

class UserActivity : AppCompatActivity() {

    private lateinit var btnGoToRegister: Button
    private lateinit var btnGoToLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Inicialización de vistas
        btnGoToRegister = findViewById(R.id.btnGoToRegister)
        btnGoToLogin = findViewById(R.id.btnGoToLogin)

        // Configuración de onClickListener para el botón de registro
        btnGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Configuración de onClickListener para el botón de inicio de sesión
        btnGoToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
