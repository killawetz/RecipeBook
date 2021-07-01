package com.example.recipebook.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "id",
            entity = Ingredient.class,
            entityColumn = "id",
            associateBy = @Junction(value = RecipeIngredientCrossRef.class,
                    parentColumn = "recipe_id",
                    entityColumn = "ingredient_id")
    )
    public List<Ingredient> ingredients;


}
