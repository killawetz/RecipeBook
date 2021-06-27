package com.example.recipebook.DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.recipebook.Entity.AreaAndRecipes;
import com.example.recipebook.Entity.FullRecipe;
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
    List<RecipeWithIngredients> getRecipeWithIngredients();

    @Transaction
    @Query("SELECT * FROM recipe;")
    RecipeAndArea getRecipeAndArea();

    @Transaction
    @Query("SELECT area.name FROM area WHERE name = (:areaID)")
    String getAreaNameForCurrentRecipe(int areaID);

    @Transaction
    @Query("select r.*, a.name as ar_name, c.name as cat_name\n" +
            "from recipe r\n" +
            "join area a on r.area_name = a.id\n" +
            "join category c on r.category_name = c.id;")
    List<FullRecipe> getFullRecipes();

}
