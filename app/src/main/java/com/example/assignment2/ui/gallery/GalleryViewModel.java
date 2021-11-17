package com.example.assignment2.ui.gallery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment2.database.ExerciseRepository;
import com.example.assignment2.database.Exercises;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GalleryViewModel extends AndroidViewModel {

    private ExerciseRepository exerciseRepository;
    private List<Exercises> mAllExercises;

    public GalleryViewModel(@NonNull @NotNull Application application) {
        super(application);
        exerciseRepository = new ExerciseRepository(application);
        mAllExercises = exerciseRepository.getAll();
    }

    public List<Exercises> getmAllExercises() {return mAllExercises;}
}