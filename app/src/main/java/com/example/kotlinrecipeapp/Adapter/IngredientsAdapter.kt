package com.example.kotlinrecipeapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R

class IngredientsAdapter(val context: Context, val ingredients: List<String>) : RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder>(){

    inner class IngredientsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ingredientCheckBox = itemView.findViewById<CheckBox>(R.id.ingredientCheckbox)

        fun bindIngredients(ingredient: String) {
            ingredientCheckBox.text = ingredient
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.ingredient_item, parent, false)
        return IngredientsHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredients.count()
    }

    override fun onBindViewHolder(holder: IngredientsHolder, position: Int) {
        holder.bindIngredients(ingredients[position])
    }
}