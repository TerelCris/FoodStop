package com.mobdeve.s11.group16.foodstop

class DataHelper {
    companion object{
        fun initializeData(): ArrayList<Recipe> {
            val postImages = intArrayOf(
                R.drawable.onepotchickenrice
            )

            val data = ArrayList<Recipe>()
            data.add(
                Recipe(
                    postImages[0],
                    "Meal",
                    "One Pot Chicken Rice",
                    "Bethany Lockhart",
                    "June 20, 2022",
                    false
                )
            )

            data.shuffle()

            return data
        }
    }
}