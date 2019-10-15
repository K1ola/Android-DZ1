package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnNumberClickListener {
    private ListFragment listFragment;
    private ItemFragment itemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("ListFragment");
        itemFragment = (ItemFragment) getSupportFragmentManager().findFragmentByTag("ItemFragment");
        if (listFragment == null) {
            listFragment = new ListFragment();
        }
        if (itemFragment == null) {
            itemFragment = new ItemFragment();
        }

        if (getSupportFragmentManager().findFragmentByTag("ListFragment") != null) {
            return;
        }

        getSupportFragmentManager().beginTransaction().add(R.id.container, listFragment, "ListFragment").commit();
    }

    @Override
    public void onNumberClick(int number, @ColorInt int color) {
        itemFragment.setNumber(number, color);

        if (getSupportFragmentManager().findFragmentByTag("ItemFragment") != null) {
            return;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, itemFragment, "ItemFragment").addToBackStack("ItemFragment").commit();
    }
}