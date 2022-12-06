package com.example.wordzone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.gson.GsonBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException
import javax.security.auth.callback.Callback

class cumleal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cumleal)
        //Bu sayfada cümlelerin ingilizceden Türkçe'ye çevirmek için kullandığım API ve kodlar bulunmaktadır
        // ana sayfaya dönmek için gerki kod
        val butongeri2 = findViewById<ImageButton>(R.id.imageBtn)
        butongeri2.setOnClickListener {
            val intent = Intent(this,Menu::class.java)
            startActivity(intent)
        }
        val butonAra = findViewById<ImageButton>(R.id.btncheck) //butonu atama
        butonAra.setOnClickListener {
            var girilenKelime=findViewById<EditText>(R.id.kelimeGir).text.toString();
            val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
            var URL = "https://translate281.p.rapidapi.com/=$girilenKelime"

            if(URL.isNotEmpty()){
                val client = OkHttpClient();
                val bodyy = RequestBody.create(mediaType, "text=$girilenKelime&from=en&to=tr")
                val request = Request.Builder()
                    .url(URL)
                    .get()
                    .post(bodyy)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .addHeader("X-RapidAPI-Key", "89f3db7710msh1b0a74885d7d329p15e8a9jsn8d6b3da6ab80")
                    .addHeader("X-RapidAPI-Host", "translate281.p.rapidapi.com")
                    .build();
                client.newCall(request).enqueue(object : Callback, okhttp3.Callback {
                    override fun onFailure(call:okhttp3.Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call:okhttp3.Call, response: Response) {
                        response.use {
                            if (!response.isSuccessful) {
                                val x: String = "something didn't load"

                            } else {
                                var body = response.body?.string();
                                val gson = GsonBuilder().create();
                                val gelensonuc= gson.fromJson(body,AramaSonuc::class.java);

                                findViewById<TextView>(R.id.sonuctr).text=gelensonuc.response.toString();
                            }}}})}}}

    class AramaSonuc(val ok:Boolean,val text:String,val from:String,val to:String,val response:String);}

