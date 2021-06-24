package com.example.recipebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.room.Room;

import com.example.recipebook.DAO.AreaDAO;
import com.example.recipebook.Entity.Recipe;
import com.example.recipebook.Entity.RecipeWithIngredients;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);

        RecipeDatabase recipeDatabase =
                Room.databaseBuilder(this, RecipeDatabase.class, "recipebook.db")
                        .allowMainThreadQueries()
                        .createFromAsset("databases/recipebook.db")
                        .build();

        StringBuilder recipeString = new StringBuilder();
        /*List<RecipeWithIngredients> recipeWithIngredients = recipeDatabase.recipeDAO()
                .getRecipeWithIngredients();
        recipeString.append(recipeWithIngredients
                .get(0)
                .recipe
                .name);
        recipeString.append(recipeWithIngredients
                .get(0)
                .ingredients);*/


        ///************************* ПОЛУЧАЕМ ВСЮ ИНФОРМАЦИЮ ПО РЕЦЕПТУ ***********//////
        /*Recipe recipe = recipeDatabase.recipeDAO().getAllRecipes().get(0);
        recipeString.append(recipe.name);
        recipeString.append("\n");
        recipeString.append(recipe.description);
        recipeString.append("\n");
        recipeString.append(recipeDatabase.areaDAO().getNameByID(recipe.areaName).name);
        recipeString.append("\n");
        recipeString.append(recipeDatabase.categoryDAO().getNameById(recipe.categoryName).name);
        recipeString.append("\n");
        recipeString.append(recipe.youtubeLink);
        recipeString.append("\n");
        recipeString.append(recipe.image);*/


        

        mainText.setText(recipeString.toString());
    }
}