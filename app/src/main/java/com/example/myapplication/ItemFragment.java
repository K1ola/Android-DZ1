package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {
    public static final String TAG = "ItemFragment";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_COLOR = "color";

    private int number = 0;
    private int color;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        number = bundle.getInt(KEY_NUMBER);
        color = bundle.getInt(KEY_COLOR);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView numView = view.findViewById(R.id.numberView);
        numView.setText(String.valueOf(number));
        numView.setTextColor(color);
    }
}
