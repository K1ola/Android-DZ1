package com.example.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFragment extends Fragment implements ItemAdapter.OnNumberClickListener {
    private Button button;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    private ArrayList<Integer> data = new ArrayList<Integer>();

    public ListFragment() {
        for (int i = 0; i < 100; i++) {
            data.add(i+1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            data = savedInstanceState.getIntegerArrayList("data_array");
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //TODO
        int orientation = view.getContext().getResources().getConfiguration().orientation;
        final int columnsNumber = orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;

        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), columnsNumber));

        itemAdapter = new ItemAdapter(data, this, view.getContext());
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        button = view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(itemAdapter.getItemCount() + 1);
                itemAdapter.SetItemCount(data);
            }
        });
    }

    @Override
    public void onNumberClick(int number, int color) {
        if (getActivity() == null || !(getActivity() instanceof ItemAdapter.OnNumberClickListener)) {
            return;
        }

        ((ItemAdapter.OnNumberClickListener) getActivity()).onNumberClick(number, color);
    }

    @Override
    public void onPause() { super.onPause(); }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putIntegerArrayList("data_array", data);
    }

}
