package com.jaax.idiomasapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaax.idiomasapi.modelo.Idioma
import com.jaax.idiomasapi.modelo.RetrofitClient
import com.jaax.idiomasapi.modelo.IdiomaAdapter
import com.jaax.idiomasapi.modelo.IdiomaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var idiomaService: IdiomaService
    private lateinit var idiomaAdapter: IdiomaAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.listaIdiomas)
    }

    override fun onStart() {
        super.onStart()
        idiomaAdapter = IdiomaAdapter()
        recyclerView.setHasFixedSize( true )
        recyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
        )
        val tmpList = ArrayList<Idioma>(0)
        tmpList.add( Idioma(0, "es", "Español", 100 ))
        tmpList.add( Idioma(0, "en", "Inglés", 1000 ))
        tmpList.add( Idioma(0, "la", "Latin", 3000 ))
        tmpList.add( Idioma(0, "ko", "Coreano", 500 ))
        tmpList.add( Idioma(0, "ka", "Georgian", 9000 ))
        tmpList.add( Idioma(0, "ja", "Japonés", 70000 ))

        for( i: Int in 0 until tmpList.size ){
            addIdioma( tmpList[i] )
        }
        recyclerView.adapter = idiomaAdapter
        listarIdiomas()
    }

    private fun listarIdiomas() {
        idiomaService = RetrofitClient().getIdiomaService()
        val idiomaCall = idiomaService.getAllIdiomas()

        idiomaCall.enqueue( object : Callback<ArrayList<Idioma>> {
            override fun onResponse(call: Call<ArrayList<Idioma>>, response: Response<ArrayList<Idioma>>) {
                if( response.isSuccessful ){
                    val idiomasRespuesta = response.body()
                    idiomaAdapter.addListaIdiomas( idiomasRespuesta!! )
                    Log.i("onResponse:", "Success")
                }
            }
            override fun onFailure(call: Call<ArrayList<Idioma>>, t: Throwable) {
                Log.e("onFailure:", t.message!!)
            }

        })
    }

    private fun addIdioma( idioma: Idioma ) {
        idiomaService = RetrofitClient().getIdiomaService()
        val call = idiomaService.post( idioma )

        call.enqueue( object : Callback<Idioma> {
            override fun onResponse(call: Call<Idioma>, response: Response<Idioma>) {
                Toast.makeText(this@MainActivity, "AGREGADO :3", Toast.LENGTH_SHORT).show()
                idiomaAdapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<Idioma>, t: Throwable) {
                Log.e("onFailure:", t.message!!)
            }

        })
    }
}