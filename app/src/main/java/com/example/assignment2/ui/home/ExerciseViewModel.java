package com.example.assignment2.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignment2.database.ExerciseRepository;
import com.example.assignment2.database.Exercises;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
    private ExerciseRepository exerciseRepository;
    private final List<String> mAllType;
    private List<Exercises> mAllExercises;
    private MutableLiveData<String> test;

    public ExerciseViewModel(Application application) {
        super(application);
        exerciseRepository = new ExerciseRepository(application);
        mAllType = exerciseRepository.getmAllType();
        Log.e("viewModelConstruc", "hello");
    }

    public void setmAllExercises(String type) {
        this.mAllExercises = exerciseRepository.getmAllExercise(type);
    }

    List<String> getmAllType(){return mAllType;}

    public MutableLiveData<String> getTest() {
        if (test == null){
            test = new MutableLiveData<String>();
        }
        return test;
    }

    public void updateTest(String newTest){
        this.getTest().postValue(newTest);
    }


    //public void insert(Exercises exercise) {exerciseRepository.insert(exercise);}

}
