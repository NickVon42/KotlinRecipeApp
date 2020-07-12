package com.example.kotlinrecipeapp.Controller

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrecipeapp.Adapter.UpdateDirectionsAdapter
import com.example.kotlinrecipeapp.Adapter.UpdateIngredientsAdapter
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R
import com.example.kotlinrecipeapp.Services.DataService
import com.example.kotlinrecipeapp.Services.RoundedTransformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_update_recipe.*


lateinit var updateIngredientsAdapter: UpdateIngredientsAdapter
lateinit var updateDirectionsAdapter: UpdateDirectionsAdapter

class UpdateRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_recipe)

        updateRecipeBackground.setColorFilter(Color.argb(180, 0, 0, 0));

        val recipe = intent.getParcelableExtra<Recipe>("value5")

        updateTitleEditText.setText(recipe?.title)
        updatePreptimeEditText.setText(recipe?.prepTime.toString())
        updateImageEditText.setText(recipe?.image)

        Picasso.get()
            .load(recipe?.image?.replace("http:", "https:"))
            .transform(RoundedTransformation(40,0))
            .fit()
            .into(updatePreviewImage)

        previewImageBtn2.setOnClickListener {
            try {
                Picasso.get()
                    .load(updateImageEditText.text.toString().replace("http:", "https:"))
                    .transform(RoundedTransformation(40,0))
                    .fit()
                    .into(updatePreviewImage)
            } catch (e: Exception) {
                println(e)
                Toast.makeText(this,
                    "Please use a valid image url.",
                    Toast.LENGTH_SHORT).show()
            }
        }

//        INGREDIENTS RECYCLE VIEW
        updateIngredientsAdapter = UpdateIngredientsAdapter(this, recipe?.ingredients!!)
        updateIngredientsRecyclerView.adapter = updateIngredientsAdapter
        val ingredientsLayoutManager = LinearLayoutManager(this)
        updateIngredientsRecyclerView.layoutManager = ingredientsLayoutManager

        //DIRECTIONS RECYCLE VIEW
        updateDirectionsAdapter = UpdateDirectionsAdapter(this, recipe.directions!!)
        updateDirectionsRecyclerView.adapter = updateDirectionsAdapter
        val directionsLayoutManager = LinearLayoutManager(this)
        updateDirectionsRecyclerView.layoutManager = directionsLayoutManager



        saveBtn.setOnClickListener {
            it.clearFocus();

            val ingredients = mutableListOf<String>()
            val directions = mutableListOf<String>()

            try {
                for (i in 0 until updateIngredientsAdapter.itemCount) {
                    val items = (updateIngredientsRecyclerView.findViewHolderForAdapterPosition(i)
                        ?.itemView?.findViewById(R.id.updateEditText) as EditText).text
                        .toString()

                    ingredients.add(items)
                }

                for (i in 0 until updateDirectionsAdapter.itemCount) {
                    val items = (updateDirectionsRecyclerView.findViewHolderForAdapterPosition(i)
                        ?.itemView?.findViewById(R.id.updateEditText) as EditText).text
                        .toString()

                    directions.add(items)
                }

                val map = mutableMapOf<String, Any?>()

                map["title"] = updateTitleEditText.text.toString()
                map["image"] = updateImageEditText.text.toString()
                map["prepTime"] = updatePreptimeEditText.text.toString()
                map["ingredients"] = ingredients
                map["directions"] = directions

                recipe.id?.let { it1 -> DataService.updateRecipe(it1, map) }

                Toast.makeText(this, "Yayy! Changes Saved!", Toast.LENGTH_SHORT).show()
                val returnIntent = Intent(this, MainActivity::class.java)
                startActivity(returnIntent)

            } catch (e: Exception) {
                println(e)
                Toast.makeText(this, "Make sure all that the fields are valid.", Toast.LENGTH_SHORT).show()

            }
        }



    }
}