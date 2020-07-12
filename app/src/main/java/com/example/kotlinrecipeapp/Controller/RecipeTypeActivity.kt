package com.example.kotlinrecipeapp.Controller

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.kotlinrecipeapp.R
import kotlinx.android.synthetic.main.activity_recipe_type.*

class RecipeTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_type)

        recipeTypeBackground.setColorFilter(Color.argb(150, 0, 0, 0));

        val spinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.recipe_types, R.layout.spinner_item)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        createRecipeType.adapter = spinnerAdapter

        recipeTypeNextBtn.setOnClickListener {
            val recipeListIntent = Intent(this, RecipeListActivity::class.java)
            recipeListIntent.putExtra("value", createRecipeType.selectedItem.toString())
            startActivity(recipeListIntent)
        }

    }

    fun getValues(view: View?) {
        Toast.makeText(this, "Spinner 1 " + createRecipeType.selectedItem.toString() +
                "\nSpinner 2 " + createRecipeType.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }
}
