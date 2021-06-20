package com.example.recipebook.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

public class AreaAndRecipes {
    @Embedded public Area area;
    @Relation(
            parentColumn = "id",
            entityColumn = "area_name"
    )
    public List<Recipe> recipes;
}
