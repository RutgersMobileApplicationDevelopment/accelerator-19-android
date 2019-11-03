package com.example.week5app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pokemon_view_holder.view.*

class PokemonAdapter(var pokemon: List<PokemonResponse>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    fun loadNewData(newPokemon: List<PokemonResponse>) {
        pokemon = newPokemon
        notifyItemInserted(pokemon.lastIndex)
    }

    /**
     * This method is called when we need to create a ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.pokemon_view_holder, parent, false)

        return PokemonViewHolder(v)
    }

    // The number of items in our list
    override fun getItemCount() = pokemon.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.setUpForItem(pokemon[position])
    }

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setUpForItem(pokemon: PokemonResponse) {
            itemView.text_view_pokemon.text = "${pokemon.name.capitalize()} weights ${pokemon.weight} pounds"
        }
    }
}
