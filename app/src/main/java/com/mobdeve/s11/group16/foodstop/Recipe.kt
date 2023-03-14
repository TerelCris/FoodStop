package com.mobdeve.s11.group16.foodstop

import java.util.Date

class Recipe(
    var imageId: Int,
    var category: String,
    var title: String,
    var author: String,
    val date: String,
    var favorite: Boolean) {
}