package com.example.week4app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.person_view_holder.view.*

class PersonAdapter(var people: List<Person>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    fun loadNewData(newPeople: List<Person>) {
        people = newPeople
        notifyItemInserted(people.lastIndex)
    }

    /**
     * This method is called when we need to create a ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.person_view_holder, parent, false)

        return PersonViewHolder(v)
    }

    // The number of items in our list
    override fun getItemCount() = people.size

    /**
     * This method is called by RecyclerView to display the data at the specified position.
     * I've added a method to PersonViewHolder that will take care of displaying the data for
     * whatever Person it's passed.
     *
     * This is just an organizational choice, though. If I wanted, I could manipulate the layout
     * directly from within this method instead.
     */
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.setUpForItem(people[position])
    }

    class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setUpForItem(person: Person) {
            itemView.text_view_person_name.text = person.name
            itemView.view_color_block.setBackgroundColor(Color.parseColor(person.favoriteColor))
        }
    }
}
