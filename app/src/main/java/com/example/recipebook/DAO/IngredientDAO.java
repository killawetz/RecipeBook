package com.example.recipebook.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.recipebook.Entity.Area;
import com.example.recipebook.Entity.Ingredient;

import java.util.List;

@Dao
public interface IngredientDAO {
    @Query("SELECT * FROM ingredient")
    List<Ingredient> getAllIngredients();

    @Query("SELECT name FROM ingredient")
    List<String> getAllNames();

    @Query("SELECT * FROM ingredient WHERE id = (:ingredientID)")
    Area getNameById(int ingredientID);
}
