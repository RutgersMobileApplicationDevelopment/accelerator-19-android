package com.example.week5app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // This is a way of having a non-null variable without having to initialize it immediately.
    // See https://kotlinlang.org/docs/reference/properties.html#late-initialized-properties-and-variables
    private lateinit var pokemonViewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Get a reference to a [PokemonViewModel]. The same PokemonViewModel will be retrieved
         * each time onCreate is called, so we'll retain any data the ViewModel holds. This also
         * means that we don't need to manually save and restore data using [onSaveInstanceState].
         */
        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(this, 2)

        /**
         * We initialize [PokemonViewModel.pokemon] to contain an empty list, so we know that
         * this value won't ever be null. That's why we can safely use the not-null assertion
         * operator. See https://kotlinlang.org/docs/reference/null-safety.html#the--operator
         */
        val pokemonAdapter = PokemonAdapter(pokemonViewModel.pokemon.value!!)

        recycler_view.adapter = pokemonAdapter

        /**
         * One big benefit to using LiveData is that it allows us to "observe" our data, and to call
         * some function whenever it changes. In our case, whenever our list of Pokémon changes, we
         * load the new list into our [PokemonAdapter].
         */
        pokemonViewModel.pokemon.observe(this, Observer { pokemon ->
            pokemonAdapter.loadNewData(pokemon)
        })

        /**
         * If there's an error making a network call, display a message to the user. Then, reset the
         * stored "error" value to false.
         */
        pokemonViewModel.error.observe(this, Observer { error ->
            if (error == true) {
                Toast.makeText(this, "Error getting Pokémon", Toast.LENGTH_LONG).show()
                pokemonViewModel.error.value = false
            }
        })

        /**
         * Whenever the "add Pokémon" button is tapped, we call
         * [PokemonViewModel.attemptToAddPokemon] (and then clear the EditText).
         *
         * We pass in the user's input in lower case because that's what this endpoint requires.
         */
        button_add_pokemon.setOnClickListener {
            // Only do anything if the user has actually entered some text
            if (edit_text_pokemon.text.isNotEmpty()) {
                pokemonViewModel.attemptToAddPokemon(edit_text_pokemon.text.toString().toLowerCase())
                edit_text_pokemon.text.clear()
            }
        }
    }
}
