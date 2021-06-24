package com.example.recipebook.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class RecipeAndArea {
    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "area_name",
            entityColumn = "id"
    )
    public Area area;
}
