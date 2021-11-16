package com.example.assignment2.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment2.R;
import com.example.assignment2.databinding.FragmentExerciseBinding;
import com.example.assignment2.databinding.FragmentFinishBinding;

public class FinishFragment extends Fragment {

    private FragmentFinishBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentFinishBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.workoutTime.setText(FinishFragmentArgs.fromBundle(getArguments()).getTime());
        binding.workoutType.setText(FinishFragmentArgs.fromBundle(getArguments()).getExerciseType());

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(FinishFragmentDirections.actionFinishFragmentToNavHome());
            }
        });

        binding.repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinishFragmentDirections.ActionFinishFragmentToTimePickerFragment action = FinishFragmentDirections.actionFinishFragmentToTimePickerFragment(FinishFragmentArgs.fromBundle(getArguments()).getExerciseType());
                Navigation.findNavController(getView()).navigate(action);
            }
        });
        return root;
    }
}