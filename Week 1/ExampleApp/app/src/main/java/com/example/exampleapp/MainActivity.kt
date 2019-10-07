package com.example.exampleapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val tag = "Useful info"

    // Stores the number of times the button has been clicked
    var numClicks = 0

    /*
    It's best practice to keep all your strings in app/res/values/strings.xml, because this makes
    them much easier to translate (which could be important if you want to eventually publish
    your app). But for simplicity's sake, we're not doing that here.

    We're using this annotation so Android Studio doesn't bug us about that.
    */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        When onCreate is called, we log a message to standard output. We could also use println(),
        but using Android's Log class is preferred because it lets us specify the log level and
        include a tag.
        */
        Log.d(tag, "onCreate called")

        /*
        We're using a couple of Kotlin features here:

        1. click_me_button is an extension property of Activity, generated based on our XML code.
           See https://kotlinlang.org/docs/tutorials/android-plugin.html#view-binding
           for an explanation.

        2. We're passing a lambda as the argument to setOnClickListener().
           See https://kotlinlang.org/docs/reference/lambdas.html for more about lambdas in Kotlin.
        */
        click_me_button.setOnClickListener {
            /*
            When the button is clicked, we increment numClicks, and then set the text in
            our TextView to reflect the new number.
            */

            /*
            We're using some more neat Kotlin features here.

            1. number_of_clicks_text is another extension property of Activity,
               just like click_me_button.

            2. When we assign a value to the "text" property, under the hood it's actually calling
               the method setText(). If you Ctrl-click on this property, Android Studio will show
               you where setText() is defined. See
               https://kotlinlang.org/docs/reference/java-interop.html#getters-and-setters
               for an explanation.

            3. The "$numClicks" is called a "template expression". See
               https://kotlinlang.org/docs/reference/basic-types.html#string-templates for
               details. This is also commonly referred to as "string interpolation", and it's a
               feature of many other programming languages as well.
            */

            numClicks++
            number_of_clicks_text.text = "# of clicks: $numClicks"
        }
    }
}
