package com.example.recipebook.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.recipebook.Entity.Area;
import com.example.recipebook.Entity.Category;
import com.example.recipebook.Entity.Ingredient;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM category")
    List<Category> getAllAreaNames();

    @Query("SELECT * FROM category WHERE id = (:categoryID)")
    Area getNameById(int categoryID);
}
