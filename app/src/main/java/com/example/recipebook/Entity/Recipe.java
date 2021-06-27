package com.example.recipebook.Entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "recipe",foreignKeys ={
        @ForeignKey(
                entity = Area.class,
                parentColumns = "id",
                childColumns = "area_name",
                onDelete = CASCADE
        ),
        @ForeignKey(
                entity = Category.class,
                parentColumns = "id",
                childColumns = "category_name",
                onDelete = CASCADE
        )
}
)
public class Recipe {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    @androidx.annotation.NonNull
    public String recipeName;

    @ColumnInfo(name = "description")
    @androidx.annotation.NonNull
    public String description;

    @ColumnInfo(name = "area_name")
    @androidx.annotation.NonNull
    public Integer areaName;

    @ColumnInfo(name = "category_name")
    @androidx.annotation.NonNull
    public Integer categoryName;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "youtube_link")
    public String youtubeLink;


}