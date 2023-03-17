package com.mobdeve.s11.group16.foodstop

class DataHelper {
    companion object {
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

        fun initializeDataFav(): ArrayList<Recipe> {
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
                    true
                )
            )

            data.add(
                Recipe(
                    postImages[1],
                    "Meal",
                    "Katsudon Pork",
                    "Marco Tsunarisha",
                    "March 12, 2023",
                    true
                )
            )

            data.shuffle()

            return data
        }

        fun initializeComment(): ArrayList<Comment> {
            val userImages = intArrayOf(
                R.drawable.monkey,
            )
            val data = ArrayList<Comment>()
            data.add(
                Comment(
                    userImages[0],
                    "Frannie Kim",
                    "1y",
                    "Nice!, It looks so yummy"
                )
            )

            return data
        }
    }
}