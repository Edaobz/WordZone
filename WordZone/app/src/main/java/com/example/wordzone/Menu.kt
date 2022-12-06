package com.example.wordzone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Menu : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //kullanıcı profiline atan kod
        val btnset = findViewById<ImageButton>(R.id.settings)
        btnset.setOnClickListener {
            val intent = Intent(this,kulProfil::class.java)
            startActivity(intent)
        }
        //İngilizceden türkçeye cümle çeviren ekrana atan kod
        val btnCeviring = findViewById<Button>(R.id.cumleCeviring)
        btnCeviring.setOnClickListener {
            val intent = Intent(this,cumleal::class.java)
            startActivity(intent)
    }
        //Türkçeden ingilizceye cümle çeviren ekrana atan kod

        val btnCevirTr= findViewById<Button>(R.id.cumleCevirtr)
        btnCevirTr.setOnClickListener {
            val intent = Intent(this,cumle_tr_ing::class.java)
            startActivity(intent)
        }
        //Türkçeden ingilizceye kelime çeviren ekrana atan kod

        val btnkelimeTr = findViewById<Button>(R.id.kelCevtr)
        btnkelimeTr.setOnClickListener {
            val intent = Intent(this,kel_tr_ing::class.java)
            startActivity(intent)
        }
        //İngilizceden türkçeye kelime çeviren ekrana atan kod

        val btnkelimeing = findViewById<Button>(R.id.kelCeviring)
        btnkelimeing.setOnClickListener {
            val intent = Intent(this,kel_ing_tr::class.java)
            startActivity(intent)
        }



}}