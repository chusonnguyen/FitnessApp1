package com.example.assignment2.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExerciseRepository {

    private ExerciseDAO exerciseDAO;
    private List<Exercises> mAllExercise;
    private List<String> mAllType;
    private List<Exercises> mAll;

    public ExerciseRepository(Application application){
        ExerciseRoomDatabase db = ExerciseRoomDatabase.getDatabase(application);
        exerciseDAO = db.exerciseDAO();
        mAllType = exerciseDAO.getAllTypes();
        mAll = exerciseDAO.getAll();
    }

    public List<Exercises> getmAllExercise(String mType){
        mAllExercise = exerciseDAO.getAllExerciseFromType(mType);
        return mAllExercise;
    }

    public List<String> getmAllType(){return mAllType;}

    public void insert(Exercises exercises){
        ExerciseRoomDatabase.databaseWriteExecutor.execute(() -> {
            exerciseDAO.insert(exercises);
        });
    }

    public List<Exercises> getAll() {return mAll;}
}
