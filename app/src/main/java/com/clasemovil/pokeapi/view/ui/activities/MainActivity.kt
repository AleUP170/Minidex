package com.clasemovil.pokeapi.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.clasemovil.pokeapi.R
import com.clasemovil.pokeapi.databinding.ActivityMainBinding
import com.clasemovil.pokeapi.model.Pokeapi
import com.clasemovil.pokeapi.model.PokemonList
import com.clasemovil.pokeapi.model.Results
import com.clasemovil.pokeapi.view.adapter.pokeapiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), pokeapiAdapter.OnItemListener {

    private lateinit var binding: ActivityMainBinding

    // For api calls
    private val BASE_URL = "https://pokeapi.co/"
    private val API_CALL = "api/v2/pokemon?limit=151"
    private val LOGTAG = "LOGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApi: Pokeapi = retrofit.create(Pokeapi::class.java)

        val call: Call<PokemonList> = pokeApi.getList(API_CALL)

        call.enqueue(object: Callback<PokemonList>{
            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                val adapter = pokeapiAdapter(this@MainActivity, response.body()?.results!!, this@MainActivity)

                with(binding){
                    loading.visibility = View.INVISIBLE
                    pokeList.layoutManager = LinearLayoutManager(this@MainActivity)
                    pokeList.adapter = adapter
                }
            }

            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                Log.d(LOGTAG, "Failed")
                Toast.makeText(this@MainActivity, R.string.conError, Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun OnItemClick(poke: Results){
        val params = Bundle()

        params.putString("url", poke.url)

        val intent = Intent(this, PokeDescription::class.java)

        intent.putExtras(params)

        startActivity(intent)
    }
}