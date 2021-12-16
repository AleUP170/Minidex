package com.clasemovil.pokeapi.model

import com.google.gson.annotations.SerializedName

class PokemonList {

    @SerializedName("results")
    var results: List<Results>? = null

}

class Results{
    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null
}