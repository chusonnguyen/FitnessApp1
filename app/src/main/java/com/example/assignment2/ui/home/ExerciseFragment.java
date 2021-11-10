package com.example.assignment2.ui.home;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private TextView timeCountDown;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        exerciseType = ExerciseFragmentArgs.fromBundle(getArguments()).getExerciseType();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(exerciseType);
        duration = ExerciseFragmentArgs.fromBundle(getArguments()).getDuration();

        progressBar = binding.progressBar;
        timeCountDown = binding.duration;

        int secondBegining = duration * 60 ;

        CountDownTimer Timer = new CountDownTimer(secondBegining * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
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
        }.start();
        
        return root;
    }

}