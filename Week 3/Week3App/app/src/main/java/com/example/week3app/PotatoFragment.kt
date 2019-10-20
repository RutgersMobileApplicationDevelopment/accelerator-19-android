package com.example.week3app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_potato.*

class PotatoFragment : Fragment() {

    private var numPotatoClicks = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_potato, container, false)
    }

    /**
     * [onViewCreated] is called immediately after [onCreateView]. The only reason we're overriding
     * it is because the Kotlin Android Extensions for view binding only work once onCreateView
     * has returned.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /**
         * When the potato is tapped, we increment the variable [numPotatoClicks].
         * Then, we get the activity containing this fragment, and cast it to [MainActivity] so that
         * we can call [MainActivity.setPotatoCount].
         */
        image_view_potato.setOnClickListener {
            numPotatoClicks++
            (activity as MainActivity).setPotatoCount(numPotatoClicks)
        }
    }

    /**
     * This method gets called from MainActivity. The [name] parameter that [MainActivity] uses
     * is coming from an EditText that's outside of this fragment.
     *
     * If there's no PotatoFragment in the FrameLayout, the app will crash when running this method.
     * That's because, since the contents of PotatoFragment won't actually be a part of the view
     * hierarchy, Android won't be able to find the view referenced by R.id.text_view_potato.
     */
    fun changeFavoritePotato(name: String) {
        text_view_potato.text = "My favorite potato is $name"
    }
}
