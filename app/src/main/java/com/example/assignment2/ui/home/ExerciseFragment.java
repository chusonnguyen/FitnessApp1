package com.example.assignment2.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.assignment2.MainActivity;
import com.example.assignment2.R;
import com.example.assignment2.databinding.FragmentExerciseBinding;
import com.example.assignment2.databinding.FragmentTimePickerBinding;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class ExerciseFragment extends Fragment {

    private FragmentExerciseBinding binding;
    private String exerciseType;
    private Integer duration;
    private ProgressBar progressBar;
    private ProgressBar exerciseProgressBar;
    private TextView timeCountDown;
    private TextView progressText;
    private CountDownTimer Timer;
    private CountDownTimer exerciseCountdown;
    private CountDownTimer restTime;
    private Boolean exerciseStart  = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sh = getActivity().getPreferences(Context.MODE_PRIVATE);
        exerciseType = ExerciseFragmentArgs.fromBundle(getArguments()).getExerciseType();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(exerciseType);
        duration = ExerciseFragmentArgs.fromBundle(getArguments()).getDuration();

        progressBar = binding.progressBar;
        exerciseProgressBar = binding.exerciseProgressBar;
        progressBar.setProgress(100);

        timeCountDown = binding.duration;
        NumberFormat f = new DecimalFormat("00");
        timeCountDown.setText(f.format((long)duration) + ":00");
        progressText = binding.progressText;

        int secondBegining = duration * 60 ;

        Timer = new CountDownTimer(secondBegining * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                //NumberFormat f = new DecimalFormat("00");
                long minute = (millisUntilFinished / 60000) % 60;
                long second = (millisUntilFinished / 1000)  %  60;
                timeCountDown.setText(f.format(minute) + ":" + f.format(second));
                int secondRemaining = (int) (millisUntilFinished / 1000);
                int progressPercentage = (int) (((secondRemaining * 100 )/ secondBegining));
                progressBar.setProgress(progressPercentage);
            }
            public void onFinish() {
                timeCountDown.setText("done!");
            }
        };


        exerciseCountdown = new CountDownTimer(31000, 1000) {
            public void onTick(long millisUntilFinished) {
                long minute = (millisUntilFinished / 60000) % 60;
                long second = (millisUntilFinished / 1000)  %  60;
                progressText.setText(f.format(minute) + ":" + f.format(second));
                int secondRemaining = (int) (millisUntilFinished / 1000);
                int progressPercentage = (int) (((secondRemaining * 100 )/ 30));
                exerciseProgressBar.setProgress(progressPercentage);
            }
            public void onFinish() {
                Drawable draw = getResources().getDrawable(R.drawable.circular_progress_bar_orange);
                exerciseProgressBar.setProgressDrawable(draw);
                restTime.start();
            }
        };

        restTime = new CountDownTimer(sh.getInt("restTime",0)*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long minute = (millisUntilFinished / 60000) % 60;
                long second = (millisUntilFinished / 1000)  %  60;
                progressText.setText(f.format(minute) + ":" + f.format(second));
                int secondRemaining = (int) (millisUntilFinished / 1000);
                exerciseProgressBar.setProgress(secondRemaining * 20);
            }
            public void onFinish() {
                Drawable draw = getResources().getDrawable(R.drawable.circular_progress_bar);
                exerciseProgressBar.setProgressDrawable(draw);
                if (exerciseStart == false){
                    exerciseStart = true;
                    Timer.start();
                }
                exerciseCountdown.start();
            }
        }.start();

        binding.nextExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetProgressBar();
            }
        });

        binding.previousExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetProgressBar();
            }
        });
        return root;
    }

    void resetProgressBar(){
        Drawable draw = getResources().getDrawable(R.drawable.circular_progress_bar_orange);
        exerciseProgressBar.setProgressDrawable(draw);
        exerciseProgressBar.setProgress(100);
        restTime.cancel();
        exerciseCountdown.cancel();
        restTime.start();
    }

}