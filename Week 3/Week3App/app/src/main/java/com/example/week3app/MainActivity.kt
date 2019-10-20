package com.example.week3app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // We create one instance of each of our fragments here
        val potatoFragment = PotatoFragment()
        val pineappleFragment = PineappleFragment()

        // Each time we click one of these two buttons, we do a fragment transaction to
        // replace what's in the FrameLayout with a particular fragment

        button_potato.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, potatoFragment)
                .addToBackStack(null)
                .commit()
        }

        button_pineapple.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, pineappleFragment)
                .addToBackStack(null)
                .commit()
        }

        /**
         * When this button is clicked, we get the text that the user has entered in the EditText
         * and use it as the argument to [PotatoFragment.changeFavoritePotato]
         *
         * Important note: if we call changeFavoritePotato when there's no PotatoFragment in the
         * FrameLayout, our app will crash. See [PotatoFragment.changeFavoritePotato] for details.
         */
        button_set_favorite_potato.setOnClickListener {
            potatoFragment.changeFavoritePotato(edit_text_favorite_potato.text.toString())
        }
    }

    /**
     * [PotatoFragment] calls this when the potato is tapped
     */
    fun setPotatoCount(numClicks: Int) {
        text_view_potato_counter.text = "You've clicked the potato $numClicks times"
    }
}
