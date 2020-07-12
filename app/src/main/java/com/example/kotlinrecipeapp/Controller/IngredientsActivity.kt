package com.example.kotlinrecipeapp.Controller

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R
import kotlinx.android.synthetic.main.activity_ingredients.*
import kotlinx.android.synthetic.main.activity_main.*

class IngredientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        ingredientsBackground.setColorFilter(Color.argb(180, 0, 0, 0));

        val recipe = intent.getParcelableExtra<Recipe>("value3")

        val list = mutableListOf<String>()

        ingredientsNextButton.setOnClickListener {

            if(ingredient1.text.isNotEmpty()) {
                list.add(ingredient1.text.toString())
            }
            if(ingredient2.text.isNotEmpty()) {
                list.add(ingredient2.text.toString())
            }
            if(ingredient3.text.isNotEmpty()) {
                list.add(ingredient3.text.toString())
            }
            if(ingredient4.text.isNotEmpty()) {
                list.add(ingredient4.text.toString())
            }
            if(ingredient5.text.isNotEmpty()) {
                list.add(ingredient5.text.toString())
            }
            if(ingredient6.text.isNotEmpty()) {
                list.add(ingredient6.text.toString())
            }
            if(ingredient7.text.isNotEmpty()) {
                list.add(ingredient7.text.toString())
            }

            recipe?.ingredients = list

            val directionsIntent = Intent(this, DirectionsActivity::class.java)
            directionsIntent.putExtra("value4", recipe)
            startActivity(directionsIntent)

    }
}
}