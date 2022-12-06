package com.example.wordzone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Giris ekranı kodları

        var authL = FirebaseAuth.getInstance()

        //Şifre yenileme sayfasına atan kodlar

        val forgot = findViewById<TextView>(R.id.sifreyenile)
        forgot.setOnClickListener {
            val intent = Intent(this,emaildogrulama::class.java)
            startActivity(intent)
        }

        val email = findViewById<EditText>(R.id.kullaniciAdigrs)
        val pas = findViewById<EditText>(R.id.sifregrs)
        //Kullanıcı girişi sağlayan kodlar
        val butonGiris = findViewById<Button>(R.id.btnGiris)
        butonGiris.setOnClickListener {
            authL.signInWithEmailAndPassword(email.text.toString(),pas.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    val intent = Intent(this,Menu::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Giriş Başarılı", Toast.LENGTH_SHORT).show()

                }
                else{
                    Toast.makeText(this,"Hatalı giriş", Toast.LENGTH_SHORT).show()

                }
            }

        }
        //kullanıcıyı kayıt ol sayfasına atan kodlar
        val butonKayitOl = findViewById<Button>(R.id.btnkayitOl)
        butonKayitOl.setOnClickListener {
            val intent = Intent(this,KayitOl::class.java)
            startActivity(intent)
        }
    }
}