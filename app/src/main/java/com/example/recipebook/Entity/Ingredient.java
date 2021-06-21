package com.example.recipebook.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Integer id;

    @ColumnInfo(name = "name")
    @androidx.annotation.NonNull
    public String name;
}
