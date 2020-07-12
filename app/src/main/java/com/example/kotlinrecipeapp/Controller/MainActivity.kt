package com.example.kotlinrecipeapp.Controller

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.kotlinrecipeapp.Adapter.RecipeAdapter
import com.example.kotlinrecipeapp.R
import com.example.kotlinrecipeapp.Services.DataService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    lateinit var adapter: RecipeAdapter
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeBackground.setColorFilter(Color.argb(150, 0, 0, 0));

        exploreBtn.setOnClickListener {
            val recipeTypeIntent = Intent(this, RecipeTypeActivity::class.java)
            startActivity(recipeTypeIntent)
        }

        createBtn.setOnClickListener {
            val createRecipeIntent = Intent(this, CreateRecipeActivity::class.java)
            startActivity(createRecipeIntent)
        }

        DataService.list.clear()
        DataService.getRecipes()

    }

}
