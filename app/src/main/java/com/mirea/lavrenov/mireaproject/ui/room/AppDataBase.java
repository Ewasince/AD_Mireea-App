package com.mirea.lavrenov.mireaproject.ui.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EnterCase.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract EnterCaseDao employeeDao();
}
