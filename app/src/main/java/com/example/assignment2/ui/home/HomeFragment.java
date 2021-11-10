package com.example.assignment2.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.MainActivity;
import com.example.assignment2.R;
import com.example.assignment2.databinding.FragmentHomeBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ExerciseViewModel exerciseViewModel;
    private FragmentHomeBinding binding;
    public static RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    public List<String> typeList;
    static View.OnClickListener myOnClickListener;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myOnClickListener = new MyOnClickListener(getContext());

        exerciseViewModel = new ExerciseViewModel(getActivity().getApplication());

        typeList = exerciseViewModel.getmAllType();
        mAdapter = new ExerciseTypeAdapter(typeList);
        mRecyclerView = binding.myRecyclerView;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new SpanningLinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private class MyOnClickListener implements View.OnClickListener{

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedPosition =mRecyclerView.getChildPosition(v);
            HomeFragmentDirections.ActionNavHomeToTimePickerFragment action = HomeFragmentDirections.actionNavHomeToTimePickerFragment(typeList.get(selectedPosition));
            Navigation.findNavController(v).navigate(action);
        }
    }
}