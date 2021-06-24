package com.example.recipebook.Entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(RecipeIngredientCrossRef.class)
    )
    public List<Ingredient> ingredients;
}
