package com.mirea.lavrenov.mireaproject.ui.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EntryCase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EntryCaseDao employeeDao();
}
