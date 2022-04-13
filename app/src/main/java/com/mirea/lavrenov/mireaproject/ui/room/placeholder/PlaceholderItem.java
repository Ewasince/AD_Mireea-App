package com.mirea.lavrenov.mireaproject.ui.room.placeholder;

public class PlaceholderItem {
    public final String id;
    public final String content;
    public final String details;

    public PlaceholderItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}