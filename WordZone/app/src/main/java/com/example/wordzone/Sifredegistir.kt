package com.example.wordzone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Sifredegistir : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sifredegistir)
        auth=FirebaseAuth.getInstance()

        //Kullanıcının şifresini değiştirmesi için gerekli kodlar
        //Şifre değiştirme ekranından Profile geri gitmeyi sağlayan kod
        val profileGit = findViewById<ImageButton>(R.id.profileGeriGit)
        profileGit.setOnClickListener {
            val intent = Intent(this,kulProfil::class.java)
            startActivity(intent)
        }
        val eskiSifre=findViewById<EditText>(R.id.eskiSifreniz)
        val yeniSifre=findViewById<EditText>(R.id.yenisifreniz1)
        val yeniSifre2=findViewById<EditText>(R.id.yenisifreniz2)
        val butonDegis = findViewById<Button>(R.id.btnSifre)
        butonDegis.setOnClickListener {
            if (eskiSifre.text.isNotEmpty()&&yeniSifre.text.isNotEmpty()&&yeniSifre2.text.isNotEmpty())
            {
                if (yeniSifre.text.toString().equals(yeniSifre2.text.toString()))
                {
                    val user: FirebaseUser?=auth.currentUser
                    if (user!=null)
                    {
                        val credential = EmailAuthProvider
                            .getCredential(user.email.toString(), eskiSifre.text.toString())


                        user.reauthenticate(credential)
                            .addOnCompleteListener {
                                if (it.isSuccessful){
                                    Toast.makeText(this, "İşlem Başarılı", Toast.LENGTH_SHORT).show()
                                    user!!.updatePassword(yeniSifre.text.toString())
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(this, "Şifre değiştirildi", Toast.LENGTH_SHORT).show()
                                                auth.signOut()

                                            }
                                        }
                                }
                                else{
                                    Toast.makeText(this, "İşlem Başarısız,Şifreleri kontro ediniz", Toast.LENGTH_SHORT).show()

                                }
                            }
                    }
                    else{
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                }else{
                    Toast.makeText(this, "şifre eşleştiriliyor", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "İşlem başarısız", Toast.LENGTH_SHORT).show()

                }

            }else
            {
                Toast.makeText(this, "Tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
            }

        }
    }
}