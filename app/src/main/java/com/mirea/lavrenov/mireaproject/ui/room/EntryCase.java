package com.mirea.lavrenov.mireaproject.ui.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntryCase {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String date;
}
