package com.example.week5app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class PokemonViewModel : ViewModel() {
    private val pokemonRepository = PokemonRepository()

    /**
     * We're storing our list of Pokémon inside of [MutableLiveData]. This allows us to "observe"
     * the data contained within, so whenever the data changes, we can "automatically" load it into
     * our UI.
     */
    val pokemon = MutableLiveData<List<PokemonResponse>>(listOf())

    /**
     * A second [MutableLiveData], which we'll use to communicate errors back to our activity.
     * We'll observe this from the activity. If it's updated to a value of "true", we'll display an
     * error message to the user, and then reset it to a value of "false".
     */
    val error = MutableLiveData<Boolean>(false)

    fun attemptToAddPokemon(name: String) {
        /**
         * The [thread] function creates a new thread that runs the block of code passed in.
         * Threads allow us to run multiple things in parallel. If we made our network calls on the
         * same thread that manages the UI, our entire app would freeze up (in fact, the Android
         * system will throw an exception if you even try to make network calls on the UI thread).
         */
        thread {
            /**
             * We call [PokemonRepository.getPokemon], passing in two things: the name of the
             * Pokémon, and a function to be called once we have a response.
             *
             * When we get a response, we update the value in [pokemon] by adding the new Pokémon
             * to the list.
             */
            pokemonRepository.getPokemon(name) { newPokemon ->
                /**
                 * Remember, the only reason we can safely use the not-null assertion operator is
                 * because we initialize the value inside [pokemon] to an empty list, so we know
                 * that it won't ever be null.
                 */
                if (newPokemon != null) {
                    pokemon.postValue(pokemon.value!!.plus(newPokemon))
                } else {
                    // Let the activity know that there was an error
                    error.postValue(true)
                }
            }
        }
    }
}
