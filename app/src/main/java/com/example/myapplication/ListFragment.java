package com.example.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment implements ItemAdapter.OnNumberClickListener {
    private Button button;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    private int countNum = 100;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            countNum = savedInstanceState.getInt("count_number");
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //TODO
        int orientation = view.getContext().getResources().getConfiguration().orientation;
        int columnsNumber = orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;

        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), columnsNumber));

        itemAdapter = new ItemAdapter(countNum, this, view.getContext());
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        button = view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countNum = itemAdapter.getItemCount() + 1;
                itemAdapter.SetItemCount(countNum);
            }
        });
    }

    @Override
    public void onNumberClick(int number, @ColorInt int color) {
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
        state.putInt("count_number", countNum);
    }

}
