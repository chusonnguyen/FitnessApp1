package com.example.assignment2.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.R;
import com.example.assignment2.ui.home.HomeFragment;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<com.example.assignment2.ui.gallery.ExerciseAdapter.MyViewHolder> {

    private List<String> typeList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.exerciseName);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.exerciseImageView);
        }
    }

    public ExerciseAdapter(List<String> data) {
        this.typeList = data;
    }

    @Override
    public com.example.assignment2.ui.gallery.ExerciseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_list, parent, false);

        //view.setOnClickListener(HomeFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final com.example.assignment2.ui.gallery.ExerciseAdapter.MyViewHolder holder, final int listPosition) {
        TextView textView = holder.textViewName;

    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }
}
