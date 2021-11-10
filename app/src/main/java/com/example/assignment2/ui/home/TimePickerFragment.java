package com.example.assignment2.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.assignment2.MainActivity;
import com.example.assignment2.R;
import com.example.assignment2.databinding.FragmentTimePickerBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import org.jetbrains.annotations.NotNull;

public class TimePickerFragment extends Fragment {

    private FragmentTimePickerBinding binding;
    private String exerciseType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTimePickerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        exerciseType = TimePickerFragmentArgs.fromBundle(getArguments()).getExerciseType();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(exerciseType);

        NumberPicker picker = binding.numberPicker;
        picker.setMaxValue(12);
        picker.setMinValue(0);
        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 5;
                return "" + temp;
            }
        };
        picker.setFormatter(formatter);
        binding.beginWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragmentDirections.ActionTimePickerFragmentToExerciseFragment action = TimePickerFragmentDirections.actionTimePickerFragmentToExerciseFragment(exerciseType, picker.getValue()*5);
                Navigation.findNavController(v).navigate(action);
            }
        });
        return root;
    }

}