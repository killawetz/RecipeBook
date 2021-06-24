package com.example.recipebook.DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.recipebook.Entity.AreaAndRecipes;
import com.example.recipebook.Entity.Recipe;
import com.example.recipebook.Entity.RecipeAndArea;
import com.example.recipebook.Entity.RecipeWithIngredients;

import java.util.List;

@Dao
public interface RecipeDAO {
    @Query("SELECT * FROM recipe;")
    List<Recipe> getAllRecipes();

    @Transaction
    @Query("SELECT * FROM recipe;")
    public List<RecipeWithIngredients> getRecipeWithIngredients();

    @Query("SELECT * FROM recipe;")
    public RecipeAndArea getRecipeAndArea();

    @Query("SELECT area.name FROM area WHERE name = (:areaID)")
    public String getAreaNameForCurrentRecipe(int areaID);

}
