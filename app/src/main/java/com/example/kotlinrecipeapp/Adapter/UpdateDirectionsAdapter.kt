package com.example.kotlinrecipeapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrecipeapp.R

class UpdateDirectionsAdapter(val context: Context, val directions: List<String>) : RecyclerView.Adapter<UpdateDirectionsAdapter.DirectionsHolder>(){

    inner class DirectionsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val updateDirection = itemView.findViewById<EditText>(R.id.updateEditText)

        fun bindIngredients(direction: String, position: Int) {
            updateDirection.setText(direction)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionsHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.update_item, parent, false)
        return DirectionsHolder(view)
    }

    override fun getItemCount(): Int {
        return directions.count()
    }

    override fun onBindViewHolder(holder: DirectionsHolder, position: Int) {
        holder.bindIngredients(directions[position], position)
    }
}