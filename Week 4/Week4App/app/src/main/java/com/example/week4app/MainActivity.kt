package com.example.week4app

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var people = mutableListOf(
        Person("John", "#ff0000"),
        Person("Samantha", "#00ff00"),
        Person("Annie", "#0000ff")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            /**
             * If onCreate has been called before, then we'll have our names and colors saved in
             * savedInstanceState. We get them by providing the same keys we used to store them.
             * @see onSaveInstanceState further down in this file
             */
            val restoredNames = savedInstanceState.getStringArray("names")
            val restoredColors = savedInstanceState.getStringArray("colors")
            if (restoredNames != null && restoredColors != null) {
                // For each name in the old list of names, add a Person to our list of people,
                // pairing each name with its corresponding color in restoredColors
                people = restoredNames.mapIndexed { index, name ->
                    Person(name, restoredColors[index])
                }.toMutableList()
            }
        }

        // If we let the RecyclerView know that its size won't change, it can perform some optimizations
        recycler_view.setHasFixedSize(true)

        // The LayoutManager controls how items are arranged in the RecyclerView.
        // Try replacing "GridLayoutManager(this, 2)" with "LinearLayoutManager(this)"
        recycler_view.layoutManager = GridLayoutManager(this, 2)

        /**
         * The adapter controls what's actually shown for each element.
         * @see PersonAdapter
         */
        val peopleAdapter = PersonAdapter(people)

        recycler_view.adapter = peopleAdapter

        button_add_person.setOnClickListener {
            /*
            Here's a simple way to make sure we only accept valid input: try to parse the user's
            input as a color right here, within the safety of a try-catch. If there's no exception,
            we'll add a new Person to the list and load it in our RecyclerView.

            But if there is an exception, then we'll just display an error message to the user.
             */
            val userColorString = edit_text_color.text.toString()
            try {
                // Notice how we're not actually saving the result of this in a variable.
                // All we care about right now is whether or not it works.
                Color.parseColor(userColorString)

                people.add(Person(edit_text_name.text.toString(), userColorString))
                peopleAdapter.loadNewData(people)
            } catch (e: RuntimeException) {
                // Displays a message like: Invalid color "abcdefg"
                Toast.makeText(this, "Invalid color \"$userColorString\"", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * This method is called when the activity is about to be destroyed.
     * We can store simple values in the provided Bundle, and retrieve them later.
     *
     * It's a bit of a hassle to store an array of [Person] objects, so instead, we'll store
     * two arrays - one array of names (strings), and one array of colors (also strings).
     *
     * So instead of storing an array like this:
     *
     * [Person("John", "#ff0000"), Person("Samantha", "#00ff00"), Person("Annie", "#0000ff")]
     *
     * We would store two separate arrays, like this:
     *
     * ["John", "Samantha", "Annie"] and ["#ff0000", "#00ff00", "#0000ff"]
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        /*
        This is one way of creating an array in Kotlin.

        people.size is the size of our array, and the elements are given by the function that we're
        passing in. The function takes the current index and returns an element.

        In this case, we want each element at any given index to be set to the name of the person at
        people[that index].
        */
        val names = Array(people.size) { people[it].name }

        /*
        This is just another way you can achieve the same thing as in the previous line (but for
        the colors this time instead of the names).

        We create a list of favorite colors corresponding to our list of people.
        Then we call .toTypedArray() to turn this list into an array so that we can store it in
        instance state.
         */
        val colors = people.map { it.favoriteColor }.toTypedArray()

        // Store these two arrays in instance state.
        // We'll need to use these two keys to retrieve these lists in onCreate.
        outState.putStringArray("names", names)
        outState.putStringArray("colors", colors)
    }
}
