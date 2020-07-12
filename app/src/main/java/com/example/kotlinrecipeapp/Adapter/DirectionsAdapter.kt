package com.example.kotlinrecipeapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R

class DirectionsAdapter(val context: Context, val directions: List<String>) : RecyclerView.Adapter<DirectionsAdapter.DirectionsHolder>(){

    inner class DirectionsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val directionRadioButton = itemView.findViewById<RadioButton>(R.id.directionRadioBtn)
        val directionText = itemView.findViewById<TextView>(R.id.directionText)

        fun bindIngredients(direction: String, position: Int) {
            directionRadioButton.text = "Step ${position + 1}:"
            directionText.text = direction
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionsHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.direction_item, parent, false)
        return DirectionsHolder(view)
    }

    override fun getItemCount(): Int {
        return directions.count()
    }

    override fun onBindViewHolder(holder: DirectionsHolder, position: Int) {
        holder.bindIngredients(directions[position], position)
    }
}