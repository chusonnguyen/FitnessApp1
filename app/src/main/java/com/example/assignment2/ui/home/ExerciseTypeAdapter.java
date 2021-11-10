package com.example.assignment2.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.MainActivity;
import com.example.assignment2.R;

import java.util.List;

public class ExerciseTypeAdapter extends RecyclerView.Adapter<ExerciseTypeAdapter.MyViewHolder> {

    private List<String> typeList;
    private int[] imageArr = {R.drawable.img_badminton, R.drawable.img_baseball, R.drawable.img_basketball, R.drawable.img_bowling, R.drawable.img_cycling, R.drawable.img_golf};

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public ExerciseTypeAdapter(List<String> data) {
        this.typeList = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.type_menu, parent, false);

        view.setOnClickListener(HomeFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(typeList.get(listPosition));
        imageView.setImageResource(imageArr[listPosition]);
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }
}
