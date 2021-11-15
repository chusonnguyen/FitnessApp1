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
    private MutableLiveData<Exercises> currentExericise;
    private Integer position ;

    public ExerciseViewModel(Application application) {
        super(application);
        exerciseRepository = new ExerciseRepository(application);
        mAllType = exerciseRepository.getmAllType();
        position = 0;
    }

    public void setmAllExercises(String type) {
        this.mAllExercises = exerciseRepository.getmAllExercise(type);
        updateCurrentExercise(position);
        Log.e("test", ""+this.mAllExercises.size());
    }

    List<String> getmAllType(){return mAllType;}

    public MutableLiveData<Exercises> getCurrentExericise() {
        if (currentExericise == null){
            currentExericise = new MutableLiveData<Exercises>();
        }
        return currentExericise;
    }

    public void updateCurrentExercise(Integer position){
        this.getCurrentExericise().postValue(mAllExercises.get(position));
    }

    public void nextExercise (){
        position++;
        updateCurrentExercise(position);
    }

    public void previousExercise(){
        if (position != 0){
            position--;
            updateCurrentExercise(position);
        }
    }

    public void insert(Exercises exercise) {exerciseRepository.insert(exercise);}

}
