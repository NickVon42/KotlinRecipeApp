package com.example.kotlinrecipeapp.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinrecipeapp.Adapter.RecipeAdapter
import com.example.kotlinrecipeapp.Model.Recipe
import com.example.kotlinrecipeapp.R
import com.example.kotlinrecipeapp.Services.DataService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recipe_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

class RecipeListActivity : AppCompatActivity() {

    lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        val recipeType = intent.getStringExtra("value")

        adapter = RecipeAdapter(this, DataService.getRecipesByType(recipeType!!)) {
            val recipeDetailIntent = Intent(this, RecipeDetailActivity::class.java)
            recipeDetailIntent.putExtra("value2", it)
            startActivity(recipeDetailIntent)
        }
        recipeRecyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this, 2)
        recipeRecyclerView.layoutManager = layoutManager
    }
}
