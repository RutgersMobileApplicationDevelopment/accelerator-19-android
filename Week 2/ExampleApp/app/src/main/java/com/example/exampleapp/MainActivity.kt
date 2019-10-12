package com.example.exampleapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This app demonstrates the two kinds of intents: implicit and explicit.
        // For more about intents, see https://developer.android.com/guide/components/intents-filters

        open_website_button.setOnClickListener {
            // An implicit intent to view RUMAD's website
            val rumadUri = Uri.parse("https://rumad.club")
            val rumadIntent = Intent(Intent.ACTION_VIEW, rumadUri)
            startActivity(rumadIntent)
        }

        go_to_second_activity_button.setOnClickListener {
            // This is an explicit intent, meaning that it specifies how the intent
            // will be satisfied. In other words, instead of telling Android, "open any app that
            // can handle this task," it says "open this specific component."

            // In our case, we're opening a second Activity in our app. Once it opens, you can
            // navigate back to the first activity by tapping the back button.
            val secondActivityIntent = Intent(this, SecondActivity::class.java)
            startActivity(secondActivityIntent)
        }

        compose_sms_button.setOnClickListener {
            // An implicit intent to start composing a text message to the specified number.
            val phoneNumber = Uri.parse("sms:5555555555")
            val composeSmsIntent = Intent(Intent.ACTION_SENDTO, phoneNumber)
            startActivity(composeSmsIntent)

            // One part of intents that we didn't get to at the meeting was "extras".
            // Extras are explained in the link above. Here's an opportunity to try using them.

            //////////////////////
            // Example scenario //
            //////////////////////

            // What if, instead of starting the user off with an empty text field, we wanted to
            // include some text that would be pre-filled when the texting app was opened?

            // Why would we want to do that? Well, let's imagine for a moment that we wanted to
            // create an app that would let people easily make use of TransLoc's SMS service, which
            // lets you get estimated arrival times of Rutgers buses via SMS (and doesn't require an
            // internet connection): https://rutgers.transloc.com/info/mobile#sms

            // You can imagine that this service is probably useful to people who have prepaid data
            // plans for their phones and want to minimize their data usage. But using this service
            // requires knowing the stop codes, so it isn't very convenient compared with using a
            // Rutgers bus-tracking app.

            // What if we could somehow connect this service with an app to make it more convenient?
            // For example, our app could have a button for getting estimated arrival times for
            // Scott Hall - meaning that, when tapped, the user's texting app would open, ready to
            // send the message "RTGRS 1055" to 41411. (Try sending that from your own phone!)

            // One way you can make this happen is by including an "extra" with your intent.
            // You can see some examples of how to do this at
            // https://developer.android.com/guide/components/intents-common#Messaging

            // Try editing the code above to make that happen!

            ///////////////
            // Side note //
            ///////////////

            // One reason I picked this example is that a few years ago, I (Miles) made an app
            // centered around this exact idea. Take a look at
            // https://github.com/mileskrell/times-via-text/blob/master/app/src/main/java/com/example/mmkrell/timesviatext/StopFragment.java#L124-L127.
            // The highlighted lines in that file do the same thing that you just coded yourself!

            // Moral of the story: if you're ever looking for ideas for a side project, think about
            // what tiny inconveniences in your life you could make better with programming!

            // (With that said, please don't use any of the code in my old app as a model for how to
            // design your own apps - I made it back when I was just starting out and didn't really
            // know what I was doing. But feel free to ask me if you notice anything in there that
            // you have questions about!)
        }
    }
}
