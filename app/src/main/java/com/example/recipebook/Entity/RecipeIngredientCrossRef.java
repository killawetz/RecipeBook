package com.example.recipebook.Entity;

import androidx.annotation.NonNull;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "recipe_ingredient",foreignKeys ={
        @ForeignKey(
                entity = Ingredient.class,
                parentColumns = "id",
                childColumns = "ingredient_id",
                onDelete = CASCADE
        ),
        @ForeignKey(
                entity = Recipe.class,
                parentColumns = "id",
                childColumns = "recipe_id",
                onDelete = CASCADE
        )
}
)
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
