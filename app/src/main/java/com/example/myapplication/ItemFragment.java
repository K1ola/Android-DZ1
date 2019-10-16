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
    private final String key_number = "number";
    private final String key_color = "color";

    private int number = 0;
    private int color;

    public void setNumber(int _number, int _color) {
        Bundle bundle = new Bundle();
        bundle.putInt(key_number, _number);
        bundle.putInt(key_color, _color);
        setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        number = bundle.getInt(key_number);
        color = bundle.getInt(key_color);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView numView = view.findViewById(R.id.numberView);
        numView.setText(String.valueOf(number));
        numView.setTextColor(color);
    }
}
