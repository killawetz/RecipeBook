package com.example.recipebook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.recipebook.Entity.FullRecipe;
import com.example.recipebook.R;
import com.example.recipebook.RecipeDatabase;
import com.example.recipebook.RecyclerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mainText;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        RecipeDatabase recipeDatabase =
                Room.databaseBuilder(this, RecipeDatabase.class, "recipebook.db")
                        .allowMainThreadQueries()
                        .createFromAsset("databases/recipebook.db")
                        .build();


        List<FullRecipe> recipeList = recipeDatabase.recipeDAO().getFullRecipes();
        RecyclerAdapter.OnRecipeClickListener onRecipeClickListener = new RecyclerAdapter.OnRecipeClickListener(){
            @Override
            public void onRecipeClick(FullRecipe recipe, int position) {
                Toast.makeText(MainActivity.this, "current recipe " + recipe.recipeMain.recipeName, Toast.LENGTH_SHORT).show();
            }
        };

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(recipeList, onRecipeClickListener);
        recyclerView.setAdapter(recyclerAdapter);
    }
}