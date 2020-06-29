package com.bancario.montcomp.appkotlin_v1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_ADDRESS
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_DNI
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_LASTNAME
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_NAME
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_PASSWORD
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_USER

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var _islogin : Boolean = true

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
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Log.d("Estado","onCreate")



        val sharedPref = applicationContext.getSharedPreferences("SAVED_SESSION", Context.MODE_PRIVATE)
        var myboolean =  sharedPref.getBoolean("save",false)

        if(myboolean==true){
            val intent1 = Intent(this,SecondActivity::class.java)
            startActivity(intent1)
        }
        /*Toast.makeText(this,"lo que me devuelve es : $myboolean",Toast.LENGTH_SHORT).show()*/

        btn_r.setOnClickListener {
            val intent1 = Intent(this,RegisterActivity::class.java)
            startActivity(intent1)
        }

        btn.setOnClickListener { view ->

            val user:User? = createUser();
            if( user == null)  {
                Toast.makeText(this, "No se logró obtener el usuario", Toast.LENGTH_SHORT).show()

            }else{
                if(et_firstname.text.toString() == user.user && et_lastname.text.toString()==user.password) {


                    /*guardar(et_firstname.text.toString(), et_lastname.text.toString(),
                        "Jean", "Montoya",
                        "73463412", "El Obelisco")*/

                    ingresar()

                }else {
                    Snackbar.make(view, "Usuario o Contraseña incorrecto", Snackbar.LENGTH_LONG).show()
                }
            }



        }

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


    private fun ingresar(){
        if(chb.isChecked==true){
            val sharedPref = applicationContext.getSharedPreferences("SAVED_SESSION", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putBoolean("save",_islogin)
            editor.commit()
        }
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    /*private fun guardarJson(usuario:String, password:String, name:String, lastname:String, dni:String, address:String ){
        val json = JSONObject()
        json.put(SecondActivity.KEY_USER, usuario)
        json.put(SecondActivity.KEY_PASSWORD, password)
        json.put(SecondActivity.KEY_NAME, name)
        json.put(SecondActivity.KEY_LASTNAME, lastname)
        json.put(SecondActivity.KEY_DNI, dni)
        json.put(SecondActivity.KEY_ADDRESS, address)
        val sp = pShardPreferences(this)
        sp.put("session",json.toString())
        sp.save()
    }

    private fun guardar(usuario:String, password:String, name:String, lastname:String, dni:String, address:String) {
        guardarJson(et_firstname.text.toString(), et_lastname.text.toString(),
            "Jean", "Montoya",
            "73463412", "El Obelisco")

        // val sharedPref = applicationContext.getSharedPreferences("CURSO_KOTLIN", Context.MODE_PRIVATE)
        // val editor: SharedPreferences.Editor = sharedPref.edit()

        val sp = pShardPreferences(this)
        sp.put(SecondActivity.KEY_USER, usuario)
        sp.put(SecondActivity.KEY_PASSWORD, password)
        sp.put(SecondActivity.KEY_NAME, name)
        sp.put(SecondActivity.KEY_LASTNAME, lastname)
        sp.put(SecondActivity.KEY_DNI, dni)
        sp.put(SecondActivity.KEY_ADDRESS, address)
        sp.save()
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Estado","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Estado","onResumen")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Estado","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Estado","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Estado","onDestroy")
    }
}
