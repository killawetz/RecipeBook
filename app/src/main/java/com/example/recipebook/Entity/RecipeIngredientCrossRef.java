package com.example.recipebook.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecipeIngredientCrossRef {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "recipe_id")
    public int recipeId;

    @ColumnInfo(name = "ingredient_id")
    public int ingredientId;

    @ColumnInfo(name = "measure")
    public String measure;
}
