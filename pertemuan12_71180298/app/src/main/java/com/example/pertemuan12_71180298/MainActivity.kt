package com.example.pertemuan12_71180298

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var queue : RequestQueue? = null
    val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(this)

        val editKota = findViewById<EditText>(R.id.editKota)
        val btnCetak = findViewById<Button>(R.id.btnCek)

        btnCetak.setOnClickListener{
            cekCuaca(editKota.text.toString())
        }

    }

    fun cekCuaca(kota: String) {
        val url = "${BASE_URL}weather?q=${kota}&appid=0e1084c9d4188562f5bc57eedd1d5975"
        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { response->
                val textHasil = findViewById<TextView>(R.id.textHasil)
                val data = JSONObject(response)
                val description = data.getJSONArray("weather").getJSONObject(0).getString("description")
                val temp = data.getJSONObject("main").getDouble("temp")
                textHasil.text = "Today: ${description} \n(${String.format("%.2f", temp-273.15).toDouble()}\u2103)\n" +
                        "Tomorrow: ${description} \n(${String.format("%.2f", temp - 273.15).toDouble()}℃)\n" +
                        "Yesterday: ${description} \n(${String.format("%.2f", temp - 273.15).toDouble()}℃)\n"
            },
            Response.ErrorListener {
            }
        )
        queue?.add(request)
    }
}