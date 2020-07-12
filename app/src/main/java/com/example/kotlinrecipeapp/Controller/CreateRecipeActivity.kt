package com.example.kotlinrecipeapp.Controller

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R
import com.example.kotlinrecipeapp.Services.RoundedTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_recipe.*
import java.lang.Exception

class CreateRecipeActivity : AppCompatActivity() {

    var recipe = Recipe("","", "", 0, listOf(""), listOf(""), "" )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        createRecipeBackground.setColorFilter(Color.argb(150, 0, 0, 0));

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.keto01)
        val rounded = RoundedBitmapDrawableFactory.create(resources, bitmap)
        rounded.cornerRadius = 40f
        previewImage.setImageDrawable(rounded)

        val spinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.recipe_types, R.layout.spinner_item)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        createRecipeType.adapter = spinnerAdapter

        Picasso.get()
            .load("https://static01.nyt.com/images/2014/05/14/dining/14REDVELVET/14REDVELVET-superJumbo-v4.jpg")
            .transform(RoundedTransformation(40,0))
            .fit()
            .into(previewImage)

        previewImageBtn.setOnClickListener {
            try {
                Picasso.get()
                    .load(imageUrlText.text.toString().replace("http:", "https:"))
                    .transform(RoundedTransformation(40,0))
                    .fit()
                    .into(previewImage)
            } catch (e: Exception) {
                println(e)
                Toast.makeText(this,
                    "Please use a valid image url.",
                    Toast.LENGTH_SHORT).show()
            }
        }

        createNextBtn.setOnClickListener {
            try{
                recipe.type = createRecipeType.selectedItem.toString()
                recipe.title = createTitle.text.toString()
                recipe.prepTime = createPreptime.text.toString().toInt()
                recipe.image = imageUrlText.text.toString()

                val ingredientsIntent = Intent(this, IngredientsActivity::class.java)
                ingredientsIntent.putExtra("value3", recipe)
                startActivity(ingredientsIntent)
            }
            catch (e: Exception) {
                println(e)
                Toast.makeText(this,
                    "Please fill in all the details.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}


