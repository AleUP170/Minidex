package com.clasemovil.pokeapi.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Pokeapi {

    @GET
    fun getList(
        @Url url: String?
    ): Call<PokemonList>

    @GET
    fun getDescription(
        @Url url: String?
    ): Call<PokemonDesc>

}