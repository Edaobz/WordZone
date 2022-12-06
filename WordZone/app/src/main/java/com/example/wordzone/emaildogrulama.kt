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

class emaildogrulama : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emaildogrulama)

        //Bu sayfada kullanıcının Şifresini değiştirmek için eposta adresini doğrulama kodları bulunmaktadır.

        val dogrulamageri = findViewById<ImageButton>(R.id.profileGeriGit)
        dogrulamageri.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val btndogrulama=findViewById<Button>(R.id.btndogrula)
        btndogrulama.setOnClickListener{
            val email:String=findViewById<EditText>(R.id.maildog).text.toString().trim{ it<=' '}
            if (email.isEmpty())
            {
                Toast.makeText(this, "E-mail adresinizi giriniz", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Şifrenizi yenilemek için E-mail adresinizi kontrol ediniz", Toast.LENGTH_SHORT).show()
                            finish()

                        }else{
                            Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}