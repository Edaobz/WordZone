package com.example.wordzone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

class kel_ing_tr : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kel_ing_tr)
        //ana sayfaya geri git için gerekli imagebutton kodları
        val btnGeri = findViewById<ImageButton>(R.id.imageBtn3)
        btnGeri.setOnClickListener {
            val intent = Intent(this,Menu::class.java)
            startActivity(intent)}

        //Kelimenin İngilizceden Türkçeye çevirmek için 2 farklı Apı kullandım
        //İlk APİden gelen sonucu diğer apiye aktardım ve böylece sonucun türkçe olmasını sağladım


        val buttoN=findViewById<ImageButton>(R.id.btncheck);
        buttoN.setOnClickListener {
            var girilenKelime=findViewById<EditText>(R.id.kelimeing).text.toString();

            var URL="https://dictionary-by-api-ninjas.p.rapidapi.com/v1/dictionary?word=$girilenKelime";
            if(URL.isNotEmpty()){
                val client = OkHttpClient()

                val request = Request.Builder()
                    .url(URL)
                    .get()
                    .addHeader("X-RapidAPI-Key", "89f3db7710msh1b0a74885d7d329p15e8a9jsn8d6b3da6ab80")
                    .addHeader("X-RapidAPI-Host", "dictionary-by-api-ninjas.p.rapidapi.com")
                    .build();
                client.newCall(request).enqueue(object: Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        response.use {
                            if (!response.isSuccessful) {
                                val x: String = "something didn't load"

                            } else {
                                var body5 = response.body?.string();
                                val gson = GsonBuilder().create();
                                val getRes = gson.fromJson(body5, getResult1::class.java)
                                findViewById<TextView>(R.id.sonucilk).text=getRes.definition.toString();
                                ////////////////
                                //ikinci api başlangıç kısmı aşağıdaki gibidir
                                var girilenKelime2=findViewById<TextView>(R.id.sonucilk).text.toString();
                                val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
                                var URL = "https://translate281.p.rapidapi.com/=$girilenKelime2"

                                if(URL.isNotEmpty()){
                                    val client = OkHttpClient();
                                    val bodyy = RequestBody.create(mediaType, "text=$girilenKelime2&from=en&to=tr")
                                    val request = Request.Builder()
                                        .url(URL)
                                        .get()
                                        .post(bodyy)
                                        .addHeader("content-type", "application/x-www-form-urlencoded")
                                        .addHeader("X-RapidAPI-Key", "89f3db7710msh1b0a74885d7d329p15e8a9jsn8d6b3da6ab80")
                                        .addHeader("X-RapidAPI-Host", "translate281.p.rapidapi.com")
                                        .build();
                                    client.newCall(request).enqueue(object : javax.security.auth.callback.Callback, okhttp3.Callback {
                                        override fun onFailure(call:okhttp3.Call, e: IOException) {
                                            e.printStackTrace()
                                        }

                                        override fun onResponse(call:okhttp3.Call, response: Response) {
                                            response.use {
                                                if (!response.isSuccessful) {
                                                    val x: String = "something didn't load"

                                                } else {
                                                    var body3 = response.body?.string();
                                                    val gson = GsonBuilder().create();
                                                    val gelensonuc5= gson.fromJson(body3,AramaSonuc1::class.java);

                                                    findViewById<TextView>(R.id.sonucTr).text=gelensonuc5.response.toString();
                                                }}}})}}

                            }

                        }
                    })
                }
            }
        }

}
class getResult1(val definition:String? ,val word:String?,val valid:Boolean);
    class AramaSonuc1(val ok:Boolean,val text:String,val from:String,val to:String,val response:String);


