package com.example.halaman_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //variable username dan password
        val username = findViewById<EditText>(R.id.namareg)
        val password = findViewById<EditText>(R.id.pwreg)

        //fungsi pengecekan username
        fun validateUsername() :Boolean {
            if(username.text.toString().isEmpty()) {
                username.error = "Username Tidak Boleh Kosong"
                return false
            }
            return true
        }

        //fungsi pengecekan password
        fun validatePassword() :Boolean {
            if(password.text.toString().isEmpty()) {
                password.error = "Password Tidak Boleh Kosong"
                return false
            }
            else if(password.text.contentEquals("123456")) {
                return true
            }
            password.error = "Password Salah"
            return false
        }

        //untuk button login
        val button_login = findViewById<Button>(R.id.btnmsk)
        button_login.setOnClickListener {
            //layar akan pindah jika input sudah sesuai fungsi validasi
            if(validateUsername() && validatePassword()) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("nama", username.text.toString())
                startActivity(intent)
            }
        }

        //untuk button daftar
        val button = findViewById<Button>(R.id.btndaftar)
        button.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}