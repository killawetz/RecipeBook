package com.example.recipebook.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class FullRecipe {
    @Embedded
    public Recipe recipeMain;

    @Relation(parentColumn = "area_name", entityColumn = "id")
    public Area area;

    @Relation(parentColumn = "category_name", entityColumn = "id")
    public Category category;
}
