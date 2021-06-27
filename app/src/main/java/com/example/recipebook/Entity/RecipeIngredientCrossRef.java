package com.example.recipebook.Entity;

import androidx.annotation.NonNull;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecipeIngredientCrossRef {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public Integer id;

    @ColumnInfo(name = "recipe_id")
    @androidx.annotation.NonNull
    public Integer recipeId;

    @ColumnInfo(name = "ingredient_id")
    @androidx.annotation.NonNull
    public Integer ingredientId;

    @ColumnInfo(name = "measure")
    @NonNull
    public String measure;
}
