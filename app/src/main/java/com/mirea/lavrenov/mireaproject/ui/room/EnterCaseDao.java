package com.mirea.lavrenov.mireaproject.ui.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EnterCaseDao {
    @Query("SELECT * FROM EnterCase")
    List<EnterCase> getAll();
    @Query("SELECT * FROM enterCase WHERE id = :id")
    EnterCase getById(long id);
    @Insert
    void insert(EnterCase enterCase);
    @Update
    void update(EnterCase enterCase);
    @Delete
    void delete(EnterCase enterCase);
    @Query("SELECT COUNT(*) FROM EnterCase")
    int getLength();
}
