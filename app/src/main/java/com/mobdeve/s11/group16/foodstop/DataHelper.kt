package com.mobdeve.s11.group16.foodstop

class DataHelper {
    companion object{
        fun initializeData(): ArrayList<Recipe> {
            val postImages = intArrayOf(
                R.drawable.onepotchickenrice,
                R.drawable.katsudonjapanesepork
            )

            val data = ArrayList<Recipe>()
            data.add(
                Recipe(
                    postImages[0],
                    "Meal",
                    "One Pot Chicken Rice",
                    "Bethany Lockhart",
                    "March 10, 2023",
                    false
                )
            )

            data.add(
                Recipe(
                    postImages[1],
                    "Meal",
                    "Katsudon Pork",
                    "Marco Tsunarisha",
                    "March 12, 2023",
                    false
                )
            )

            data.shuffle()

            return data
        }
    }
}