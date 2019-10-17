package com.example.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements ItemAdapter.OnNumberClickListener {
    public static final String TAG = "ListFragment";
    private final String key_data = "data_array";
    private final int portrait_orientation = 3;
    private final int landscape_orientation = 4;

    private ItemAdapter itemAdapter;

    private List<Integer> data;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            data = savedInstanceState.getIntegerArrayList(key_data);
        } else {
            data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                data.add(i+1);
            }
        }
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        int orientation = view.getContext().getResources().getConfiguration().orientation;
        final int columnsNumber = orientation == Configuration.ORIENTATION_LANDSCAPE ? landscape_orientation : portrait_orientation;

        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), columnsNumber));

        itemAdapter = new ItemAdapter(data, this, view.getContext());
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Button button = view.findViewById(R.id.addButton);
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
    public void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
        state.putIntegerArrayList(key_data, (ArrayList<Integer>)data);
    }

}
