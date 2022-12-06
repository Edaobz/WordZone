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
import javax.security.auth.callback.Callback

class kel_tr_ing : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kel_tr_ing)
        //Ana sayfaya dönmek için gerekli kodlar
        val btngeri = findViewById<ImageButton>(R.id.imageBtn3)
        btngeri.setOnClickListener {
            val intent = Intent(this,Menu::class.java)
            startActivity(intent)}
        //Kelimeyi türkçedden ingilizceye çevirmek için gerekli kodlar
        //Bunun için 2 farklı api kullandım
        //ilk api girilen türkçe keimeyi ingilizceye çevirmek için gerekli
        //diğer api ise çevirilen kelimenin ingilizce karşılığını göstermektedir
        val buton4Ara = findViewById<ImageButton>(R.id.btncheck) //butonu atama
        buton4Ara.setOnClickListener {
            var girilenKelimetr=findViewById<EditText>(R.id.keltrtoing).text.toString();
            val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
            var URL = "https://translate281.p.rapidapi.com/=$girilenKelimetr"

            if(URL.isNotEmpty()){
                val client = OkHttpClient();
                val bodyy = RequestBody.create(mediaType, "text=$girilenKelimetr&from=tr&to=en")
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
                                val gelensonuc= gson.fromJson(body,AramaSonuctr1::class.java);
                                findViewById<TextView>(R.id.textView7).text=gelensonuc.response.toString();
                                //////////////

                                //diğer api başlangıç kodları

                                var gelenkelime=findViewById<TextView>(R.id.textView7).text.toString();

                                var URL="https://dictionary-by-api-ninjas.p.rapidapi.com/v1/dictionary?word=$gelenkelime";
                                if(URL.isNotEmpty()){
                                    val client = OkHttpClient()

                                    val request = Request.Builder()
                                        .url(URL)
                                        .get()
                                        .addHeader("X-RapidAPI-Key", "89f3db7710msh1b0a74885d7d329p15e8a9jsn8d6b3da6ab80")
                                        .addHeader("X-RapidAPI-Host", "dictionary-by-api-ninjas.p.rapidapi.com")
                                        .build();
                                    client.newCall(request).enqueue(object: okhttp3.Callback {
                                        override fun onFailure(call: Call, e: IOException) {
                                            e.printStackTrace()
                                        }

                                        override fun onResponse(call: Call, response: Response) {
                                            response.use {
                                                if (!response.isSuccessful) {
                                                    val x: String = "something didn't load"

                                                } else {
                                                    var body = response.body?.string();
                                                    val gson = GsonBuilder().create();
                                                    val getRes = gson.fromJson(body, gelenSonuc::class.java)
                                                    findViewById<TextView>(R.id.sonuctrtoing).text=getRes.definition.toString();

                                                }

                                            }
                                        }
                                    })
                                }
                            }
                        }
                    }




                            })}}}}

    class AramaSonuctr1(val ok:Boolean,val text:String,val from:String,val to:String,val response:String);
class gelenSonuc(val definition:String? ,val word:String?,val valid:Boolean);



