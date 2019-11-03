package com.example.week5app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This interface lets us model our API endpoints as methods.
 * We specify how the arguments the methods are called with should be translated into API calls.
 *
 * In [getPokemonByName], we specify that the "name" argument should be used to replace the "name"
 * part of the path. This is appended to the base URL for the API that we provide when we set up
 * our Retrofit instance. So for example, calling getPokemonByName("ditto") would result in an HTTP
 * request to "https://pokeapi.co/api/v2/pokemon/ditto".
 */
interface PokemonService {
    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Call<PokemonResponse>
}
