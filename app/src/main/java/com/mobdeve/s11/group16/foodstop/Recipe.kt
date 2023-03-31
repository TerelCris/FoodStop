package com.mobdeve.s11.group16.foodstop

class Recipe(
    val username: String,
    val title: String,
    val date: String,
    val imageUrl: String,
    val favorite: Boolean,
    val body: String,)
     {

    private val recipeList = ArrayList<Recipe>()
}