package com.example.assignment2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Exercises exercise);

    @Query("DELETE FROM exercise_table")
    void deleteAll();

    @Query("SELECT * FROM exercise_table WHERE type LIKE :mType ORDER BY name ASC")
    List<Exercises> getAllExerciseFromType(String mType);

    @Query("SELECT DISTINCT type FROM exercise_table")
    List<String> getAllTypes();

    @Query("SELECT * FROM exercise_table")
    List<Exercises> getAll();
}
