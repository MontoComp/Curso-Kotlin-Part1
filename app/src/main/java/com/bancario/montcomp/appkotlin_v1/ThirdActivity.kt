package com.bancario.montcomp.appkotlin_v1

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_third.*
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdActivity : AppCompatActivity() {

    var rec_usuario2 : String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        setSupportActionBar(toolbar)

        rec_usuario2= intent.getStringExtra("usuario2")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Bienvenido: $rec_usuario2", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        tv_lastname.text = "My lastname is: $rec_usuario2 "
    }

}
