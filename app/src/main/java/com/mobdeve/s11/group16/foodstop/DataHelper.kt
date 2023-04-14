package com.mobdeve.s11.group16.foodstop
//
//class DataHelper {
//    companion object {
//        fun initializeData(): ArrayList<Recipe> {
//            val postImages = intArrayOf(
//                R.drawable.onepotchickenrice,
//                R.drawable.katsudonjapanesepork
//            )
//
//            val data = ArrayList<Recipe>()
//            data.add(
//                Recipe(
//                    postImages[0],
//                    "One Pot Chicken Rice",
//                    "Bethany Lockhart",
//                    "March 10, 2023",
//                    false,
//                    "Sliced carrots are sauteed in butter and homemade seasoned salt then simmered with dried minced onions and garlic, white rice, diced chicken, and rich chicken stock until thick and irresistible. It is finished with a couple pats of butter. Did I mention it’s irresistible? This recipe makes enough to feed a crowd and anything we don’t finish reheats wonderfully, too. It is not difficult to say that my entire community loves this dish!\n" +
//                            "\n" +
//                            "INGREDIENTS\n" +
//                            "\n" +
//                            "- 4 - 6 Tablespoons butter, divided\n" +
//                            "- 1 heaping cup chopped carrots (from 1 cup baby carrots or 2 large carrots)\n" +
//                            "- homemade seasoned salt and pepper (see notes)\n" +
//                            "- 2 scant cups long grain white rice (I like Lundberg White Jasmine Rice)\n" +
//                            "- 1 Tablespoon dried minced onion\n" +
//                            "- 1 teaspoon dried minced garlic\n" +
//                            "- 2 Tablespoons dried parsley flakes\n" +
//                            "- 8 cups gluten free chicken stock\n" +
//                            "- 2 small chicken breasts (14oz), cut into bite-sized pieces\n" +
//                            "\n" +
//                            "PROCEDURE\n" +
//                            "\n" +
//                            "1. Melt 2 Tablespoons butter in a soup pot over medium heat. Add carrots, season with seasoned salt and pepper, then place a lid on top and cook until carrots are tender, 5-6 minutes, stirring occasionally.\n" +
//                            "\n" +
//                            "2. Add rice, dried onions and dried garlic then stir to coat in butter and saute for 1 minute. Add dried parsley and chicken stock then turn heat up to high to bring to a boil, stirring occasionally to ensure rice doesn’t stick to the bottom of the pot as it comes to a boil.\n" +
//                            "\n" +
//                            "3. Turn heat down to medium-low then simmer for 10 minutes, stirring occasionally. Season chicken with seasoned salt and pepper then add to the pot, turn heat up slightly to bring back up to a bubble, then turn back down to medium-low and continue to simmer until chicken is cooked through and rice is al dente, 12-15 more minutes, stirring occasionally and more frequently near the end.\n" +
//                            "\n" +
//                            "4. Place a lid on top of the pot then remove from heat and let sit for 5 minutes. Stir in remaining 2 - 4 Tablespoons butter (however much you like!) then season with additional seasoned salt and pepper if necessary. Scoop into bowls then serve.\n" +
//                            "\n" +
//                            "5. Leave a comment if you love the recipe."
//                )
//            )
//
//            data.add(
//                Recipe(
//                    postImages[1],
//                    "Katsudon Pork",
//                    "Marco Tsunarisha",
//                    "March 12, 2023",
//                    false,
//                    "If you’ve never heard of it, Katsudon is basically katsu (a pork chop breaded with panko and fried) over rice with onion and egg. It was awesome, and it became my go-to choice whenever I went to a Japanese place–a fish-less, teriyaki-less haven.\n" +
//                            "        Since then, I’ve discovered that sushi is, in fact, delicious and amazing, and I now order either sushi or sashimi at any Japanese restaurant I go to.\n" +
//                            "\n" +
//                            "        INGREDIENTS\n" +
//                            "\n" +
//                            "        - Tonkatsu – regular deep-fried version or baked version\n" +
//                            "        - Onion\n" +
//                            "        - Broth – dashi (Japanese soup stock), sugar, mirin, and soy sauce\n" +
//                            "        - Eggs\n" +
//                            "        - Green onion\n" +
//                            "        - Freshly steamed, Japanese short-grain rice\n" +
//                            "\n" +
//                            "        PROCEDURE\n" +
//                            "        1. Make Tonkatsu (I have a very detailed recipe here) if you haven’t made yet.\n" +
//                            "        2. Cook the onion in the savory broth till tender.\n" +
//                            "        3. Place Tonkatsu on top to let it absorb the flavors of the broth.\n" +
//                            "        4. Add the beaten egg mixture and cook till just set.\n" +
//                            "        5. Serve over steamed rice and enjoy!"
//                )
//            )
//
//            data.shuffle()
//
//            return data
//        }
//
//        private fun Recipe(
//            username: Int,
//            title: String,
//            date: String,
//            imageUrl: String,
//            favorite: Boolean,
//            body: String
//        ): Recipe {
//            TODO("Not yet implemented")
//        }
//
//        fun initializeComment(): ArrayList<CommentModel> {
//            val data = ArrayList<CommentModel>()
//            data.add(
//                CommentModel(
//                    "Frannie Kim",
//                    "1y",
//                    "Nice!, It looks so yummy"
//                )
//            )
//
//            return data
//        }
//    }
//}