package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnNumberClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(ListFragment.TAG);
        if (listFragment != null) {
            return;
        }
        listFragment = new ListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, listFragment, ListFragment.TAG)
                .commit();
    }

    @Override
    public void onNumberClick(int number, int color) {
        ItemFragment itemFragment = new ItemFragment();

        final Bundle bundle = new Bundle();
        bundle.putInt(ItemFragment.KEY_NUMBER, number);
        bundle.putInt(ItemFragment.KEY_COLOR, color);
        itemFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, itemFragment, ItemFragment.TAG)
                .addToBackStack(ItemFragment.TAG)
                .commit();
    }
}