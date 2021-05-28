package com.example.breezy.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "dailyPointDb")
public class DailyPoints {

    @PrimaryKey
    @NonNull
    private String date;

    private int sleep, mood;
    private boolean isHydrated;

    public DailyPoints() {
    }

    @Ignore
    public DailyPoints(String date, int sleep, int mood, boolean isHydrated) {
        this.date = date;
        this.sleep = sleep;
        this.mood = mood;
        this.isHydrated = isHydrated;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public boolean isHydrated() {
        return isHydrated;
    }

    public void setHydrated(boolean hydrated) {
        isHydrated = hydrated;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
