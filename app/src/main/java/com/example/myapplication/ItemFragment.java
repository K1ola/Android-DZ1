package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {
    private int number = 0;
    private int color;
    private TextView numView;

    public void setNumber(int _number, int _color) {
        Bundle bundle = new Bundle();
        bundle.putInt("number", _number);
        bundle.putInt("color", _color);
        setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        number = bundle.getInt("number");
        color = bundle.getInt("color");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numView = view.findViewById(R.id.numberView);
        numView.setText(String.valueOf(number));
        numView.setTextColor(color);
    }
}
