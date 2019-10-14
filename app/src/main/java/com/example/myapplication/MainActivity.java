package com.example.myapplication;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager gridManager;
    Integer[] data = new Integer[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        gridManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridManager);



        // specify an adapter (see also next example)
        for(int i=0; i<100; i++) {
            data[i] = i+1;
        }
        mAdapter = new MyAdapter(data);
        recyclerView.setAdapter(mAdapter);

//        for (int i=0; i<100; i++) {
//            if (i%2==0) {
//                gridManager.findViewByPosition(i).setBackgroundColor(Color.RED);
//            } else {
//                gridManager.findViewByPosition(i).setBackgroundColor(Color.RED);
//            }
//        }
    }
    // ...
}