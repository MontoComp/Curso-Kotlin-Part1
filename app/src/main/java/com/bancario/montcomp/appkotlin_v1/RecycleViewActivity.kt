package com.bancario.montcomp.appkotlin_v1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.bancario.montcomp.appkotlin_v1.Adapters.RecyclerAdapter
import com.bancario.montcomp.appkotlin_v1.Adapters.User
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_ADDRESS
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_DNI
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_LASTNAME
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_NAME
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_PASSWORD
import com.bancario.montcomp.appkotlin_v1.mykeys.Companion.KEY_USER
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.fragment_recycle_view.*
import kotlinx.android.synthetic.main.list_users.*
import org.json.JSONObject

class FeedActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        /*val user:User? = createUser();
        if( user == null)  {
            Toast.makeText(this, "No se logró obtener el usuario", Toast.LENGTH_SHORT).show()
        }*/



        val userList = ArrayList<User>()
        userList.add(User("jemonto", "123123","Jean","Montoya","73463412","obelisco", "https://www.pexels.com/es-es/foto/hombre-persona-manos-escritorio-3197390/"))
        userList.add(User("miguel", "123123","Miguel","Montoya","73463413","urrunaga","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRPla3rTBqTsLRZLSyj7Zv_DV_WLc6LWwZnlw&usqp=CAU"))
        userList.add(User("pepe", "123123","Pedro","Montoya","73463414","sanmartin","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRPla3rTBqTsLRZLSyj7Zv_DV_WLc6LWwZnlw&usqp=CAU"))


        adapter = RecyclerAdapter(userList)
        linearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager= linearLayoutManager
        rv.adapter = adapter
    }

}