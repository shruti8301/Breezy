package com.example.breezy.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.breezy.models.DailyPoints;

import java.util.List;

@Dao
public interface DailyDao {

    @Insert
    void insertDaily(DailyPoints points);

    @Query("SELECT * FROM dailyPointDb")
    List<DailyPoints> loadAllPoints();

    @Query("DELETE FROM dailyPointDb")
    void resetDailyDb();

    @Query("SELECT COUNT(*) FROM dailyPointDb WHERE date = :inputDate")
    int isDatePresent(String inputDate);
}
