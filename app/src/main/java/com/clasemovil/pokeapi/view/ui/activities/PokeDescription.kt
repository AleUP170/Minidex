package com.clasemovil.pokeapi.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.clasemovil.pokeapi.R
import com.clasemovil.pokeapi.databinding.ActivityPokeDescriptionBinding
import com.clasemovil.pokeapi.model.Pokeapi
import com.clasemovil.pokeapi.model.PokemonDesc
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeDescription : AppCompatActivity() {

    private lateinit var binding : ActivityPokeDescriptionBinding
    private val BASE_URL = "https://pokeapi.co/"

    // Type map
    val typeMap = mapOf("bug" to 1, "dark" to 2, "dragon" to 3, "electric" to 4, "fairy" to 5,
    "fighting" to 6, "fire" to 7, "flying" to 8, "ghost" to 9, "grass" to 10, "ground" to 11,
    "ice" to 12, "normal" to 13, "poison" to 14, "psychic" to 15, "rock" to 16, "steel" to 17,
    "water" to 18)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_description)

        binding = ActivityPokeDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras


        // By default, in case of error, return Bulbasaur
        val url = bundle?.getString("url", "https://pokeapi.co/api/v2/pokemon/1/")?.substring(19)


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val pokeApi: Pokeapi = retrofit.create(Pokeapi::class.java)

        val call: Call<PokemonDesc> = pokeApi.getDescription(url)

        call.enqueue(object: Callback<PokemonDesc> {
            override fun onResponse(call: Call<PokemonDesc>, response: Response<PokemonDesc>) {
                with(binding){
                    binding.loadDesc.visibility = View.INVISIBLE

                    // Load image
                    Glide.with(this@PokeDescription)
                        .load(response.body()?.sprites?.other?.front?.imageURL)
                        .into(binding.pokeSprite)

                    // Name
                    name.text = response.body()?.name!!.capitalize()

                    // Abilities check
                    // Rarely, pokemon only have one
                    ab1.text = response.body()?.abilities!![0].ability?.name!!.capitalize()
                    ab2.text = "-"
                    ab3.text = "-"

                    // Most pokemon have two abilities
                    if(response.body()?.abilities!!.size >= 2){
                        ab2.text = response.body()?.abilities!![1].ability?.name!!.capitalize()
                    }
                    // Only some pokemon have 3 abilities
                    if(response.body()?.abilities!!.size == 3){
                        ab3.text = response.body()?.abilities!![2].ability?.name!!.capitalize()
                    }

                    // Height and weight
                    height.text = getString(R.string.height,(response.body()?.height!!.toFloat()/10).toString())
                    weight.text = getString(R.string.weight,(response.body()?.weight!!.toFloat()/10).toString())

                    setTypeImage1(typeMap[response.body()?.types!![0].type?.name]!!)
                    // Only some pokemon have a second type
                    if(response.body()?.types!!.size == 2){
                        setTypeImage2(typeMap[response.body()?.types!![1].type?.name]!!)
                    }
                }

            }

            override fun onFailure(call: Call<PokemonDesc>, t: Throwable) {
                Toast.makeText(this@PokeDescription, R.string.conError, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setTypeImage1(type: Int){
        when(type){
            1 -> binding.typeImage1.setImageResource(R.drawable.type1)
            2 -> binding.typeImage1.setImageResource(R.drawable.type2)
            3 -> binding.typeImage1.setImageResource(R.drawable.type3)
            4 -> binding.typeImage1.setImageResource(R.drawable.type4)
            5 -> binding.typeImage1.setImageResource(R.drawable.type5)
            6 -> binding.typeImage1.setImageResource(R.drawable.type6)
            7 -> binding.typeImage1.setImageResource(R.drawable.type7)
            8 -> binding.typeImage1.setImageResource(R.drawable.type8)
            9 -> binding.typeImage1.setImageResource(R.drawable.type9)
            10 -> binding.typeImage1.setImageResource(R.drawable.type10)
            11 -> binding.typeImage1.setImageResource(R.drawable.type11)
            12 -> binding.typeImage1.setImageResource(R.drawable.type12)
            13 -> binding.typeImage1.setImageResource(R.drawable.type13)
            14 -> binding.typeImage1.setImageResource(R.drawable.type14)
            15 -> binding.typeImage1.setImageResource(R.drawable.type15)
            16 -> binding.typeImage1.setImageResource(R.drawable.type16)
            17 -> binding.typeImage1.setImageResource(R.drawable.type17)
            18 -> binding.typeImage1.setImageResource(R.drawable.type18)
        }
    }

    private fun setTypeImage2(type: Int){
        when(type){
            1 -> binding.typeImage2.setImageResource(R.drawable.type1)
            2 -> binding.typeImage2.setImageResource(R.drawable.type2)
            3 -> binding.typeImage2.setImageResource(R.drawable.type3)
            4 -> binding.typeImage2.setImageResource(R.drawable.type4)
            5 -> binding.typeImage2.setImageResource(R.drawable.type5)
            6 -> binding.typeImage2.setImageResource(R.drawable.type6)
            7 -> binding.typeImage2.setImageResource(R.drawable.type7)
            8 -> binding.typeImage2.setImageResource(R.drawable.type8)
            9 -> binding.typeImage2.setImageResource(R.drawable.type9)
            10 -> binding.typeImage2.setImageResource(R.drawable.type10)
            11 -> binding.typeImage2.setImageResource(R.drawable.type11)
            12 -> binding.typeImage2.setImageResource(R.drawable.type12)
            13 -> binding.typeImage2.setImageResource(R.drawable.type13)
            14 -> binding.typeImage2.setImageResource(R.drawable.type14)
            15 -> binding.typeImage2.setImageResource(R.drawable.type15)
            16 -> binding.typeImage2.setImageResource(R.drawable.type16)
            17 -> binding.typeImage2.setImageResource(R.drawable.type17)
            18 -> binding.typeImage2.setImageResource(R.drawable.type18)
        }
    }
}