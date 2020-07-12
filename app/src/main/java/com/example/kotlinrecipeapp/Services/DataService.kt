package com.example.kotlinrecipeapp.Services


import com.example.kotlinrecipeapp.Model.Recipe
import com.google.firebase.firestore.FirebaseFirestore

object DataService {

    var db = FirebaseFirestore.getInstance()
    var list = mutableListOf<Recipe>()
    lateinit var recipe: Recipe


    fun addRecipe(recipe: Recipe?) {
        val map = mutableMapOf<String, Any?>()

        map["type"] = recipe?.type
        map["title"] = recipe?.title
        map["prepTime"] = recipe?.prepTime
        map["ingredients"] = recipe?.ingredients
        map["directions"] = recipe?.directions
        map["image"] = recipe?.image

        db.collection("recipes")
            .add(map)
    }

    fun getRecipes() {

        db.collection("recipes")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        println(document.id)
                        val map = document?.data as Map<String, Any>
                            val recipe = Recipe(
                                document.id,
                                map["type"].toString(),
                                map["title"].toString(),
                                map["prepTime"].toString().toInt(),
                                map["ingredients"] as List<String>?,
                                map["directions"] as List<String>?,
                                map["image"].toString()
                            )
                            list.add(recipe)
                    }
                } else {
                    print(task.exception)
                }
            }

    }

    fun getRecipesByType(recipeType: String): List<Recipe> {
        val recipesByType = mutableListOf<Recipe>()

        for (recipe in list) {
            if (recipe.type == recipeType) {
                recipesByType.add(recipe)
            }
        }
        return recipesByType
    }

    fun updateRecipe(id: String, recipe: Map<String, Any?>) {

        db.collection("recipes")
            .document(id)
            .update(recipe)
    }

    fun deleteRecipe(id: String) {

        db.collection("recipes")
            .document(id)
            .delete()
    }

    fun readObserveRecipe(id: String?): Recipe {
        db.collection("recipes")
            .document(id!!)
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                var map = documentSnapshot?.data!!
                recipe = Recipe(
                    id,
                    map["type"].toString(),
                    map["title"].toString(),
                    map["prepTime"].toString().toInt(),
                    map["ingredients"] as List<String>?,
                    map["directions"] as List<String>?,
                    map["image"].toString()
                )
            }
        return recipe
    }






//    fun getRecipes(category: String) : List<Recipe> {
//        return when(category) {
//            "Keto" -> ketoRecipies
//            "Quick and Easy" -> easyRecipies
//            else -> easyRecipies
//        }
//    }


//    val ketoRecipies = listOf(
//        Recipe("Keto", "Keto Chocolate Mousse",
//            20,
//            listOf("3 ounces cream cheese, softened," +
//                    "½ cup heavy cream",
//                "1 teaspoon vanilla extract",
//                "¼ cup powdered zero-calorie sweetener",
//                "2 tablespoons cocoa powder"),
//            listOf("Place cream cheese in a large bowl and beat using an electric mixer until light and fluffy.",
//            "Turn mixer to low speed and slowly add heavy cream and vanilla extract.",
//            "Add sweetener, cocoa powder and salt, mixing until well incorporated.",
//            "Turn mixer to high, and mix until light and fluffy, 1 to 2 minutes more."),
//            "https://thehealthcreative.com/wp-content/uploads/2018/12/IMG_5531-826x1024.jpg"
//        ),
//        Recipe("Keto", "Keto Bread",
//            60,
//            listOf("7 eaches eggs, at room temperature",
//            "½ cup butter, melted and cooled",
//            "2 tablespoons olive oil",
//                "2 cups blanched almond flour",
//                "1 teaspoon baking powder"),
//            listOf("Preheat the oven to 350 degrees F (175 degrees C).",
//            "Whisk eggs in a bowl until smooth and creamy, about 3 minutes. Add melted butter and olive oil.",
//            "Combine almond flour, baking powder, xanthan gum, and salt in a separate bowl; mix well. Add gradually to the egg mixture.",
//            "Pour batter into the prepared pan and smooth the top with a spatula.",
//            "Bake in the preheated oven until a toothpick inserted into the center comes out clean, about 45 minutes."),
//            "https://p4x7m4n3.stackpathcdn.com/wp-content/uploads/2018/01/keto-bread-lighter.jpg"
//        ),
//        Recipe("Keto", "Keto Chicken Parmesan",
//            30,
//            listOf("1 (8 ounce) skinless, boneless chicken breast",
//            "1 tablespoon heavy whipping cream",
//            "1 ½ ounces pork rinds, crushed",
//            "1 ounce grated Parmesan cheese"),
//            listOf("Set oven rack about 6 inches from the heat source and preheat the oven's broiler.",
//            "Slice chicken breast through the middle horizontally from one side to within 1/2 inch of the other side.",
//            "Beat egg and cream together in a bowl.",
//            "Place chicken in the pan; cook until no longer pink in the center and the juices run clear, about 3 minutes per side.",
//            "Transfer chicken to a baking sheet. Cover with tomato sauce; top with mozzarella cheese."),
//            "https://kicking-carbs.com/wp-content/uploads/2019/02/keto-chicken-parmesan-2.jpg"
//        ),
//        Recipe("Keto", "Low Carb Instant Pot Soup",
//            25,
//            listOf("1 tablespoon olive oil",
//            "1 large yellow onion, diced",
//            "2 cloves garlic, minced",
//            "1 head cauliflower, coarsely chopped",
//            "2 cups shredded Cheddar cheese"),
//            listOf("Turn on a multi-cooker (such as Instant Pot®) and select the Saute function.",
//            "Add olive oil, onion, and garlic; cook until browned, about 3 minutes.",
//            "Pour in chicken stock; close and lock the lid. Select Soup function; set timer for 15 minutes."),
//            "https://static-communitytable.parade.com/wp-content/uploads/2017/09/wholesomeyum_broccoli-cheese-soup-low-carb-gluten-free.jpg"
//        ),
//        Recipe("Keto", "Keto Chocolate Mousse",
//            20,
//            listOf("3 ounces cream cheese, softened," +
//                    "½ cup heavy cream",
//                "1 teaspoon vanilla extract",
//                "¼ cup powdered zero-calorie sweetener",
//                "2 tablespoons cocoa powder"),
//            listOf("Place cream cheese in a large bowl and beat using an electric mixer until light and fluffy.",
//            "Turn mixer to low speed and slowly add heavy cream and vanilla extract.",
//            "Add sweetener, cocoa powder and salt, mixing until well incorporated.",
//            "Turn mixer to high, and mix until light and fluffy, 1 to 2 minutes more."),
//            "https://thehealthcreative.com/wp-content/uploads/2018/12/IMG_5531-826x1024.jpg"
//        ),
//        Recipe("Keto", "Keto Bread",
//            60,
//            listOf("7 eaches eggs, at room temperature",
//            "½ cup butter, melted and cooled",
//            "2 tablespoons olive oil",
//                "2 cups blanched almond flour",
//                "1 teaspoon baking powder"),
//            listOf("Preheat the oven to 350 degrees F (175 degrees C).",
//            "Whisk eggs in a bowl until smooth and creamy, about 3 minutes. Add melted butter and olive oil.",
//            "Combine almond flour, baking powder, xanthan gum, and salt in a separate bowl; mix well. Add gradually to the egg mixture.",
//            "Pour batter into the prepared pan and smooth the top with a spatula.",
//            "Bake in the preheated oven until a toothpick inserted into the center comes out clean, about 45 minutes."),
//            "https://p4x7m4n3.stackpathcdn.com/wp-content/uploads/2018/01/keto-bread-lighter.jpg"
//        ),
//        Recipe("Keto", "Keto Chicken Parmesan",
//            30,
//            listOf("1 (8 ounce) skinless, boneless chicken breast",
//            "1 tablespoon heavy whipping cream",
//            "1 ½ ounces pork rinds, crushed",
//            "1 ounce grated Parmesan cheese"),
//            listOf("Set oven rack about 6 inches from the heat source and preheat the oven's broiler.",
//            "Slice chicken breast through the middle horizontally from one side to within 1/2 inch of the other side.",
//            "Beat egg and cream together in a bowl.",
//            "Place chicken in the pan; cook until no longer pink in the center and the juices run clear, about 3 minutes per side.",
//            "Transfer chicken to a baking sheet. Cover with tomato sauce; top with mozzarella cheese."),
//            "https://kicking-carbs.com/wp-content/uploads/2019/02/keto-chicken-parmesan-2.jpg"
//        ),
//        Recipe("Keto", "Low Carb Instant Pot Soup",
//            25,
//            listOf("1 tablespoon olive oil",
//            "1 large yellow onion, diced",
//            "2 cloves garlic, minced",
//            "1 head cauliflower, coarsely chopped",
//            "2 cups shredded Cheddar cheese"),
//            listOf("Turn on a multi-cooker (such as Instant Pot®) and select the Saute function.",
//            "Add olive oil, onion, and garlic; cook until browned, about 3 minutes.",
//            "Pour in chicken stock; close and lock the lid. Select Soup function; set timer for 15 minutes."),
//            "https://static-communitytable.parade.com/wp-content/uploads/2017/09/wholesomeyum_broccoli-cheese-soup-low-carb-gluten-free.jpg"
//        )
//    )
//
//    val easyRecipies = listOf(
//        Recipe("Quick and Easy", "Loaded Breakfast Skillet",
//            10,
//            listOf(""),
//            listOf(""),
//            "easy01"
//        ),
//        Recipe("Quick and Easy", "Quick Black Forest Cake",
//            15,
//            listOf(""),
//            listOf(""),
//            "easy02"
//        ),
//        Recipe("Quick and Easy", "One Pot Cheese and Macaroni",
//            18,
//            listOf(""),
//            listOf(""),
//            "easy03"
//        ),
//        Recipe("Quick and Easy", "Quick Cassoulet",
//            13,
//            listOf(""),
//            listOf(""),
//            "easy04"
//        )
//    )
}