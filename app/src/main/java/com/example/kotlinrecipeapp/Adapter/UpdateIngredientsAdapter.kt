package com.example.kotlinrecipeapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrecipeapp.R

class UpdateIngredientsAdapter(val context: Context, val ingredients: List<String>) : RecyclerView.Adapter<UpdateIngredientsAdapter.IngredientsHolder>(){


    inner class IngredientsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val updateIngredient = itemView.findViewById<EditText>(R.id.updateEditText)

        fun bindIngredients(ingredient: String) {
            updateIngredient.setText(ingredient)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.update_item, parent, false)
        return IngredientsHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredients.count()
    }

    override fun onBindViewHolder(holder: IngredientsHolder, position: Int) {
        holder.bindIngredients(ingredients[position])
    }
}