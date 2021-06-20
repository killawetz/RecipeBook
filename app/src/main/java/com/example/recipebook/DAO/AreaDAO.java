package com.example.recipebook.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.recipebook.Entity.Area;

import java.util.List;

@Dao
public interface AreaDAO {
    @Query("SELECT * FROM area;")
    List<Area> getAllNames();

    @Query("SELECT * FROM area WHERE id = (:areaID)")
    Area getNameById(int areaID);

}
