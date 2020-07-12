package com.example.kotlinrecipeapp.Controller

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrecipeapp.Adapter.DirectionsAdapter
import com.example.kotlinrecipeapp.Adapter.IngredientsAdapter
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R
import com.example.kotlinrecipeapp.Services.DataService
import com.example.kotlinrecipeapp.Services.RoundedTransformation
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.activity_recipe_detail.*


class RecipeDetailActivity : AppCompatActivity() {

    lateinit var ingredientsAdapter: IngredientsAdapter
    lateinit var directionsAdapter: DirectionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val recipe = intent.getParcelableExtra<Recipe>("value2")
        recipeDetailTitle.text = recipe?.title
        prepTimeText.text = "Prep time: ${recipe?.prepTime.toString()}mins"

        Picasso.get()
            .load(recipe?.image?.replace("http:", "https:"))
            .transform(RoundedTransformation(40,0))
            .fit()
            .into(recipeDetailImage)

        //INGREDIENTS RECYCLE VIEW
        ingredientsAdapter = IngredientsAdapter(this, recipe?.ingredients!!)
        ingredientsRecyclerView.adapter = ingredientsAdapter
        val ingredientsLayoutManager = LinearLayoutManager(this)
        ingredientsRecyclerView.layoutManager = ingredientsLayoutManager

        //DIRECTIONS RECYCLE VIEW
        directionsAdapter = DirectionsAdapter(this, recipe.directions!!)
        directionsRecyclerView.adapter = directionsAdapter
        val directionsLayoutManager = LinearLayoutManager(this)
        directionsRecyclerView.layoutManager = directionsLayoutManager

        updateBtn.setOnClickListener {
            val updateRecipeIntent = Intent(this, UpdateRecipeActivity::class.java)
            updateRecipeIntent.putExtra("value5", recipe)
            startActivity(updateRecipeIntent)
        }

        deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Confirmation")
            builder.setMessage("Are you sure you want to delete the recipe?")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                DataService.deleteRecipe(recipe.id!!)
                Toast.makeText(this,
                    "This recipe is gone forever..", Toast.LENGTH_SHORT).show()
                val returnIntent = Intent(this, MainActivity::class.java)
                startActivity(returnIntent)
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }
    }

    private fun getResourceId(context: Context, recipe: Recipe?): Int {
        return context.resources.getIdentifier(recipe?.image,
            "drawable", context.packageName)
    }
}
