package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context context;
    private OnNumberClickListener listener;
    private ArrayList<Integer> numCount;

    private int lastPosition = -1;

    public ItemAdapter(ArrayList<Integer> _numCount, OnNumberClickListener _listener, Context _context) {
        numCount = _numCount;
        listener = _listener;
        context = _context;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private Button button;
        private Integer number;
        public ItemViewHolder(View itemView, final OnNumberClickListener _listener) {
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


    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_number, parent, false);

        ItemViewHolder vh = new ItemViewHolder(v, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.number = numCount.get(position);
        holder.button.setText(String.valueOf(holder.number));
        holder.button.setTextColor(getColor(holder.number));
        setAnimation(holder.itemView, position);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
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
        return numCount.size();
    }

    public void SetItemCount(ArrayList<Integer> num) {
        numCount = num;
        notifyDataSetChanged();
    }

    public interface OnNumberClickListener {
        void onNumberClick(int number, int color);
    }

    private static int getColor(int _number) {
        int result = _number % 2 == 0 ? Color.RED : Color.BLUE;
        return result;
    }
}