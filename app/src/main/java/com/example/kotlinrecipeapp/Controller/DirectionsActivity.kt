package com.example.kotlinrecipeapp.Controller

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R
import com.example.kotlinrecipeapp.Services.DataService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_recipe.*
import kotlinx.android.synthetic.main.activity_directions.*
import kotlinx.android.synthetic.main.activity_ingredients.*

class DirectionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directions)

        directionsBackground.setColorFilter(Color.argb(180, 0, 0, 0));

        val recipe = intent.getParcelableExtra<Recipe>("value4")

        val list = mutableListOf<String>()

        createFinishBtn.setOnClickListener {

            if(directions1.text.isNotEmpty()) {
                list.add(directions1.text.toString())
            }
            if(directions2.text.isNotEmpty()) {
                list.add(directions2.text.toString())
            }
            if(directions3.text.isNotEmpty()) {
                list.add(directions3.text.toString())
            }
            if(directions4.text.isNotEmpty()) {
                list.add(directions4.text.toString())
            }
            if(directions5.text.isNotEmpty()) {
                list.add(directions5.text.toString())
            }

            recipe?.directions = list

            DataService.addRecipe(recipe)
            Toast.makeText(this, "Yay! New recipe!", Toast.LENGTH_SHORT).show()

            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)

        }

    }
}