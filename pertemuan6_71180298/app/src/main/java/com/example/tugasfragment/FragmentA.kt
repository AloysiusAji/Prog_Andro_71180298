//71180298
package com.example.tugasfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentA: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.a_fragment, container, false)
        val btnA = v.findViewById<Button>(R.id.btnFA)

        //pindah dari Fragment ke Activity
        btnA.setOnClickListener {

            //Toast untuk menampilkan pesan halaman ketika baru dibuka
            Toast.makeText(context, "Halaman 2", Toast.LENGTH_LONG).show()

            val intent = Intent(getActivity(), PageTwoActivity::class.java)
            startActivity(intent)

        }
        return v
    }
}