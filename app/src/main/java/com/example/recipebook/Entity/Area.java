package com.example.recipebook.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "area")
public class Area {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public Integer id;

    @ColumnInfo(name = "name")
    @androidx.annotation.NonNull
    public String name;
}
