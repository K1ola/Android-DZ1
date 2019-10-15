package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context context;
    private OnNumberClickListener listener;
    private int numCount;

    private int lastPosition = -1;

    public ItemAdapter(int _numCount, OnNumberClickListener _listener, Context _context) {
        numCount = _numCount;
        listener = _listener;
        context = _context;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private Button button;
        private Integer number;
        public ItemViewHolder(@NonNull View itemView, final OnNumberClickListener _listener) {
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
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_number, parent, false);

        ItemViewHolder vh = new ItemViewHolder(v, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.number = position;
        holder.button.setText(String.valueOf(position));
        holder.button.setTextColor(getColor(position));
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
        return numCount;
    }

    public void SetItemCount(int count) {
        numCount = count;
        notifyDataSetChanged();
    }

    public interface OnNumberClickListener {
        void onNumberClick(int number, @ColorInt int color);
    }

    @ColorInt
    private static int getColor(int _number) {
        int result = _number % 2 == 0 ? Color.RED : Color.BLUE;
        return result;
    }
}