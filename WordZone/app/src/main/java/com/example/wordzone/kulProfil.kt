package com.example.wordzone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class kulProfil : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kul_profil)

        //Kullanıcı Profili
        auth=FirebaseAuth.getInstance()
        val adiniz=findViewById<EditText>(R.id.isimGir)
        val adsonuc=findViewById<TextView>(R.id.profilad)

        //isim değiştirme kodları
        val btnisim = findViewById<ImageButton>(R.id.check)
        btnisim.setOnClickListener {

            adsonuc.setText("hi "+adiniz.getText());
            adiniz.text.clear()
        }
        //Şifre değiştirme syafasına atan kod
        val sifresayfasi = findViewById<Button>(R.id.sifrenidegistir)
        sifresayfasi.setOnClickListener {
            val intent = Intent(this,Sifredegistir::class.java)
            startActivity(intent)}
        //Ana sayfaya atan kodlar
        val btnprofilgeri = findViewById<ImageButton>(R.id.profilgeri)
        btnprofilgeri.setOnClickListener {
            val intent = Intent(this,Menu::class.java)
            startActivity(intent)}
        //Giris Sayfasına atan kodlar
        val btncikis = findViewById<Button>(R.id.cikisYap)
        btncikis.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Çıkış Yapıldı", Toast.LENGTH_SHORT).show()
        }

}
}