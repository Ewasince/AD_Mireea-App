package com.mirea.lavrenov.mireaproject.ui.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryCaseDao {
    @Query("SELECT * FROM EntryCase")
    List<EntryCase> getAll();
    @Query("SELECT * FROM EntryCase WHERE id = :id")
    EntryCase getById(long id);
    @Insert
    void insert(EntryCase entryCase);
    @Update
    void update(EntryCase entryCase);
    @Delete
    void delete(EntryCase entryCase);
    @Query("SELECT COUNT(*) FROM EntryCase")
    int getLength();
//    @Query("DROP TABLE EntryCase")
//    void drop();
}
