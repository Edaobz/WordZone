package com.example.wordzone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class KayitOl : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit_ol)

        //Yeni kullanıcı kaydı oluşturmak için gerekli kodlar bulunmaktadır

        //Kayıt Ol ekranından giriş ekranına dönen buton kodu aşağıdaki gibidir
        val butongeri = findViewById<ImageButton>(R.id.btngeri)
        butongeri.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        var authR = FirebaseAuth.getInstance()
            //Tanımlamalar aşağıdaki gibidir
        val btnKayitYap = findViewById<Button>(R.id.butonKayıt)
        val email = findViewById<EditText>(R.id.eposta)
        val pas = findViewById<EditText>(R.id.sifreniz)
            //Kayit ol butonuna tıkladıktan sonra kayıt olma kodları aşaağıdaki gibidir
        btnKayitYap.setOnClickListener {
            authR.createUserWithEmailAndPassword(email.text.toString(),pas.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    val curentUser = authR.currentUser
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Kayıt Başarılı,Giriş Yapınız", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Kayıt esnasında hata oluştu,Tekrar deneyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}