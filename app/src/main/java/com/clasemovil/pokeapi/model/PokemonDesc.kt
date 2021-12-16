package com.clasemovil.pokeapi.model

import com.google.gson.annotations.SerializedName

class PokemonDesc {

     @SerializedName("name")
     var name: String? = null

     @SerializedName("abilities")
     var abilities: List<Abilities>? = null

     @SerializedName("height")
     var height: String? = null

     @SerializedName("weight")
     var weight: String? = null

     @SerializedName("types")
     var types: List<Types>? = null

     @SerializedName("sprites")
     var sprites: Sprites? = null
}

class Abilities{
     @SerializedName("ability")
     var ability: Ability? = null
}

class Ability{
     @SerializedName("name")
     var name: String? = null
}

class Types{
     @SerializedName("type")
     var type: Type? = null
}

class Type{
     @SerializedName("name")
     var name: String? = null
}

class Sprites{
     @SerializedName("other")
     var other: Other? = null
}

class Other{
     @SerializedName("official-artwork")
     var front: Front? = null
}

class Front{
     @SerializedName("front_default")
     var imageURL: String? = null
}
