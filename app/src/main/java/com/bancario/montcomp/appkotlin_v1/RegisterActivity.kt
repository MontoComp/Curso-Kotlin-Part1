package com.bancario.montcomp.appkotlin_v1

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

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    var rec_usuario : String?=null
    var rec_password: String?=null
    var rec_name: String?=null
    var rec_lasttname : String?=null
    var rec_dni : String?=null
    var rec_address : String?=null

    data class User(val user:String?, val password:String?, val name:String?,val lastname:String?,val dni:String?, val address: String? )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)

        //
        val user:User? = createUser();
        if( user == null)  {
            Toast.makeText(this, "No se logró obtener el usuario", Toast.LENGTH_SHORT).show()
        }



        et_username.setText(user?.user)
        et_password.setText(user?.password)
        et_firstname.setText(user?.name)
        et_lastname.setText(user?.lastname)
        et_dni.setText(user?.dni)
        et_address.setText(user?.address)

        //

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        btn.setOnClickListener { view ->
            if(et_password.text.toString() == et_confirmpassword.text.toString()) {


                guardar(et_username.text.toString(), et_password.text.toString(),
                    et_firstname.text.toString(), et_lastname.text.toString(),
                    et_dni.text.toString(), et_address.text.toString())

                /*intent.putExtra(SecondActivity.KEY_USER, et_firstname.text.toString())
                intent.putExtra(SecondActivity.KEY_PASSWORD, et_lastname.text.toString() )
                intent.putExtra(SecondActivity.KEY_NAME, "Jean")
                intent.putExtra(SecondActivity.KEY_LASTNAME, "Montoya")
                intent.putExtra(SecondActivity.KEY_DNI, "73463412")
                intent.putExtra(SecondActivity.KEY_ADDRESS, "El Obelisco")*/

                Snackbar.make(view, "Las Usuario guardado", Snackbar.LENGTH_LONG).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else {
                Snackbar.make(view, "Las Contraseñas no son las mismas", Snackbar.LENGTH_LONG).show()
            }

        }
    }

    private fun guardarJson(usuario:String, password:String, name:String, lastname:String, dni:String, address:String ){
        val json = JSONObject()
        json.put(KEY_USER, usuario)
        json.put(KEY_PASSWORD, password)
        json.put(KEY_NAME, name)
        json.put(KEY_LASTNAME, lastname)
        json.put(KEY_DNI, dni)
        json.put(KEY_ADDRESS, address)
        /*json.put(SecondActivity.KEY_USER, usuario)
        json.put(SecondActivity.KEY_PASSWORD, password)
        json.put(SecondActivity.KEY_NAME, name)
        json.put(SecondActivity.KEY_LASTNAME, lastname)
        json.put(SecondActivity.KEY_DNI, dni)
        json.put(SecondActivity.KEY_ADDRESS, address)*/
        val sp = pShardPreferences(this)
        sp.put("session",json.toString())
        sp.save()
    }

    private fun guardar(usuario:String, password:String, name:String, lastname:String, dni:String, address:String) {
        guardarJson(et_username.text.toString(), et_password.text.toString(),
            et_firstname.text.toString(), et_lastname.text.toString(),
            et_dni.text.toString(), et_address.text.toString())

        // val sharedPref = applicationContext.getSharedPreferences("CURSO_KOTLIN", Context.MODE_PRIVATE)
        // val editor: SharedPreferences.Editor = sharedPref.edit()

        val sp = pShardPreferences(this)
        sp.put(KEY_USER, usuario)
        sp.put(KEY_PASSWORD, password)
        sp.put(KEY_NAME, name)
        sp.put(KEY_LASTNAME, lastname)
        sp.put(KEY_DNI, dni)
        sp.put(KEY_ADDRESS, address)
        sp.save()
    }



    private fun createUser():User?{
        val mShared  = pShardPreferences(this)
        val session = mShared.getKey("session")

        if(session=="none"){
            return  null
        }else {

            val sessionObj = JSONObject(session)
            val user = User(

                sessionObj.getString(KEY_USER),
                sessionObj.getString(KEY_PASSWORD),
                sessionObj.getString(KEY_NAME),
                sessionObj.getString(KEY_LASTNAME),
                sessionObj.getString(KEY_DNI),
                sessionObj.getString(KEY_ADDRESS)

                /*sessionObj.getString(KEY_USER),
            sessionObj.getString(KEY_PASSWORD),
            sessionObj.getString(KEY_NAME),
            sessionObj.getString(KEY_LASTNAME),
            sessionObj.getString(KEY_DNI),
            sessionObj.getString(KEY_ADDRESS)*/
            )
            return user;
        }
    }

}
