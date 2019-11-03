package com.example.week5app

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        // The base URL for the API we're using
        .baseUrl("https://pokeapi.co/api/v2/")
        /**
         * The response from the API will be in JSON, but we need to translate the responses to
         * instances of Java/Kotlin classes. This line tells Retrofit that we want to use
         * Google's Gson library to deserialize these responses.
         */
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val pokemonService = retrofit.create(PokemonService::class.java)

    /**
     * We'll make an HTTP call to try to get a [PokemonResponse], which will contain the data
     * we're interested in. Once we get a response or an error, either onResponse or onFailure
     * will be called, respectively.
     */
    fun getPokemon(name: String, callback: (pokemonResponse: PokemonResponse?) -> Unit) {
        pokemonService.getPokemonByName(name).enqueue(object : Callback<PokemonResponse> {
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                /**
                 * In the callback, if the response is null, we'll consider it an error. So this is
                 * an easy way of indicating that there was an error.
                 */
                callback(null)
            }

            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                callback(response.body())
            }
        })
    }
}
