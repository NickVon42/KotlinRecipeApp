package com.example.kotlinrecipeapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R
import com.example.kotlinrecipeapp.Services.RoundedTransformation
import com.squareup.picasso.Picasso

class RecipeAdapter(val context: Context, val recipies: List<Recipe>, val itemClick: (Recipe) -> Unit)
    : RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {

    inner class RecipeHolder(itemView: View, val itemClick: (Recipe) -> Unit): RecyclerView.ViewHolder(itemView) {
        val recipeTitle = itemView.findViewById<TextView>(R.id.recipeTitle)
        val recipeImage = itemView.findViewById<ImageView>(R.id.recipeImage)

        fun bindRecipe(recipe: Recipe, context: Context) {
//            val resourceId = context.resources.getIdentifier(recipe.image,
//                "drawable", context.packageName)
//
//            recipeImage.setImageResource(resourceId)
            Picasso.get()
                .load(recipe.image?.replace("http:", "https:"))
                .transform(RoundedTransformation(40,0))
                .fit()
                .into(recipeImage)

            recipeTitle.text = recipe.title
            itemView.setOnClickListener { itemClick(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return RecipeHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return recipies.count()
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bindRecipe(recipies[position], context)
    }


}