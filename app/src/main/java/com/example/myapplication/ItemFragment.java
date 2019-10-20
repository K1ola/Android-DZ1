package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {
    static final String TAG = "ItemFragment";
    static final String KEY_NUMBER = "number";
    static final String KEY_COLOR = "color";

    private int number = 0;
    private int color;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView numView = view.findViewById(R.id.numberView);
        numView.setText(String.valueOf(number));
        numView.setTextColor(color);
    }
}
