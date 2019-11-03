package com.example.week5app;

/**
 * This class models two of the fields in the responses that we get from the endpoint we're using.
 * The response actually contains a lot more information than this, but we don't actually *need* to
 * model all of its fields if we're not going to use them.
 */
data class PokemonResponse(
    val name: String,
    val weight: Int
)
