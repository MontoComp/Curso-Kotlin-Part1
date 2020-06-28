package com.bancario.montcomp.appkotlin_v1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_ADDRESS
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_DNI
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_LASTNAME
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_NAME
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_PASSWORD
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_USER

import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_third.*
import org.json.JSONObject

class SecondActivity : AppCompatActivity() {

    /*companion object{
        const val KEY_USER="admin"
        const val KEY_PASSWORD="123456"
        const val KEY_NAME="name"
        const val KEY_LASTNAME="lastname"
        const val KEY_DNI="dni"
        const val KEY_ADDRESS="address"

    }*/

    var rec_usuario : String?=null
    var rec_password: String?=null
    var rec_name: String?=null
    var rec_lasttname : String?=null
    var rec_dni : String?=null
    var rec_address : String?=null

    data class User(val user:String?, val password:String?, val name:String?,val lastname:String?,val dni:String?, val address: String? )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        val user:User? = createUser();
        if( user == null)  {
            Toast.makeText(this, "No se logró obtener el usuario", Toast.LENGTH_SHORT).show()
            return
        }
        tvuser1.text = user.name
        tvuser2.text = user.lastname
        tvuser3.text = user.dni
        tvuser4.text = user.address

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Bienvenido: ${user.name} ${user.lastname}", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        btn.setOnClickListener {
            val sharedPref = applicationContext.getSharedPreferences("SAVED_SESSION", Context.MODE_PRIVATE)
            sharedPref.edit().remove("save").apply()

            val intent1 = Intent(this,MainActivity::class.java)
            startActivity(intent1)
        }

        btn_e.setOnClickListener {
            val intent1 = Intent(this,RegisterActivity::class.java)
            startActivity(intent1)
        }


    }

    private fun createUser():User?{
        val mShared  = pShardPreferences(this)
        val session = mShared.getKey("session") ?: return null

        val sessionObj = JSONObject(session)

        val user = User(

            sessionObj.getString(KEY_USER),
            sessionObj.getString(KEY_PASSWORD),
            sessionObj.getString(KEY_NAME),
            sessionObj.getString(KEY_LASTNAME),
            sessionObj.getString(KEY_DNI),
            sessionObj.getString(KEY_ADDRESS)

            /*obtener(KEY_USER),
            obtener(KEY_PASSWORD),
            obtener(KEY_NAME),
            obtener(KEY_LASTNAME),
            obtener(KEY_DNI),
            obtener(KEY_ADDRESS)*/

        )

        return user;
    }

    private fun obtener(key: String) : String? {
        val sharedPref = applicationContext.getSharedPreferences("CURSO_KOTLIN", Context.MODE_PRIVATE)
        return sharedPref.getString(key, "none")
    }


    private fun cerrar(){

    }


    override fun onBackPressed(){

    }

}



