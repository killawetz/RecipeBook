package com.example.recipebook.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryAndRecipes {
    @Embedded
    public Category category;
    @Relation(
            parentColumn = "id",
            entityColumn = "category_name"
    )
    public List<Recipe> recipes;
}
