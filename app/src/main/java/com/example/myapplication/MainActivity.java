package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnNumberClickListener {
    private ItemFragment itemFragment;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(ListFragment.TAG);
        itemFragment = (ItemFragment) getSupportFragmentManager().findFragmentByTag(ItemFragment.TAG);
        if (listFragment == null) {
            listFragment = new ListFragment();
        }
        if (itemFragment == null) {
            itemFragment = new ItemFragment();
        }

        if (getSupportFragmentManager().findFragmentByTag(ListFragment.TAG) != null) {
            return;
        }

        getSupportFragmentManager().beginTransaction().add(R.id.container, listFragment, ListFragment.TAG).commit();
    }

    @Override
    public void onNumberClick(int number, int color) {
        itemFragment.setNumber(number, color);

        if (getSupportFragmentManager().findFragmentByTag(ItemFragment.TAG) != null) {
            return;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, itemFragment, ItemFragment.TAG).addToBackStack(ItemFragment.TAG).commit();
    }
}