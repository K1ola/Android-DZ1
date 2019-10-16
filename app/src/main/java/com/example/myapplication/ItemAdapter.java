package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private final Context context;
    private final OnNumberClickListener listener;
    private int numCount = 99;

    private int lastPosition = -1;

    public ItemAdapter(int _numCount, final OnNumberClickListener _listener, final Context _context) {
        numCount = _numCount;
        listener = _listener;
        context = _context;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final Button button;
        private Integer number;
        ItemViewHolder(@NonNull View itemView, final OnNumberClickListener _listener) {
            super(itemView);
            button = itemView.findViewById(R.id.numButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _listener.onNumberClick(number, getColor(number));
                }
            });
        }
    }


    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_number, parent, false);

        return new ItemViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.number = position + 1;
        holder.button.setText(String.valueOf(holder.number));
        holder.button.setTextColor(getColor(holder.number));
        setAnimation(holder.itemView, position);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(@NonNull View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return numCount;
    }

    public void SetItemCount(int _numCount) {
        numCount = _numCount;
        notifyDataSetChanged();
    }

    public interface OnNumberClickListener {
        void onNumberClick(int number, int color);
    }

    private static int getColor(int _number) {
        return _number % 2 == 0 ? Color.RED : Color.BLUE;
    }
}