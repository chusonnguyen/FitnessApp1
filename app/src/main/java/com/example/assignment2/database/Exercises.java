package com.example.assignment2.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercises {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;


    @NonNull
    @ColumnInfo(name = "type")
    private String mType;

    public Exercises(@NonNull String mName, byte[] image, @NonNull String mType) {
        this.mName = mName;
        this.image = image;
        this.mType = mType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    @NonNull
    public String getType() {
        return mType;
    }

    public void setType(@NonNull String mType) {
        this.mType = mType;
    }
}
