package com.example.recipebook;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.recipebook.DAO.AreaDAO;
import com.example.recipebook.DAO.CategoryDAO;
import com.example.recipebook.DAO.IngredientDAO;
import com.example.recipebook.DAO.RecipeDAO;
import com.example.recipebook.Entity.Area;
import com.example.recipebook.Entity.Category;
import com.example.recipebook.Entity.Ingredient;
import com.example.recipebook.Entity.Recipe;
import com.example.recipebook.Entity.RecipeIngredientCrossRef;

@Database(entities = {Recipe.class,
        Ingredient.class,
        Area.class,
        Category.class,
        RecipeIngredientCrossRef.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeDAO recipeDAO();
    public abstract AreaDAO areaDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract IngredientDAO ingredientDAO();
}
