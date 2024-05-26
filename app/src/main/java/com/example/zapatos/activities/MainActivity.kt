package com.example.zapatos.activities


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.zapatos.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewShoesButton: Button = findViewById(R.id.view_shoes_button)
        val manageShoesButton: Button = findViewById(R.id.manage_shoes_button)

        viewShoesButton.setOnClickListener {
            val intent = Intent(this, ViewShoesActivity::class.java)
            startActivity(intent)
        }

        manageShoesButton.setOnClickListener {
            val intent = Intent(this, ManageShoesActivity::class.java)
            startActivity(intent)
        }
    }
}
