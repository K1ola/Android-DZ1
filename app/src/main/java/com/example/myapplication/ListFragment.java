package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ListFragment extends Fragment implements ItemAdapter.OnNumberClickListener {
    public static final String TAG = "ListFragment";
    private final String key_data = "data_array";

    private ItemAdapter itemAdapter;

    private ArrayList<Integer> data = new ArrayList<>();

    //////////////////////////////////
    class DBHelper extends SQLiteOpenHelper {
        final String CREATE_TABLE = "CREATE TABLE data (count int)";
        final String DB_NAME = "mySuperDB.db";
        Context mContext;

        public DBHelper(Context context, int dbVer){
            super(context, "mySuperDB.db", null, dbVer);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //проверяете какая версия сейчас и делаете апдейт
            db.execSQL("DROP TABLE IF EXISTS tableName");
            onCreate(db);
        }
	}

    DBHelper dbHelper;

    public ListFragment() {
        for (int i = 0; i < 100; i++) {
            data.add(i+1);
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

        if (savedInstanceState != null) {
            data = savedInstanceState.getIntegerArrayList(key_data);
        }

        dbHelper = new DBHelper(view.getContext(), 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query("data", null, null, null, null, null, null);
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int Col = c.getColumnIndex("count");
            int lastNum;
            do {
                // получаем значения по номерам столбцов и пишем все в лог
                lastNum = c.getInt(Col);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());


            data.add(lastNum);
        }

            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

            int orientation = view.getContext().getResources().getConfiguration().orientation;
            final int columnsNumber = orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;

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

//            dbHelper.close();

    }

        @Override
        public void onNumberClick ( int number, int color){
            if (getActivity() == null || !(getActivity() instanceof ItemAdapter.OnNumberClickListener)) {
                return;
            }

            ((ItemAdapter.OnNumberClickListener) getActivity()).onNumberClick(number, color);
        }

        @Override
        public void onSaveInstanceState (@NonNull Bundle state){
            super.onSaveInstanceState(state);
            state.putIntegerArrayList(key_data, data);


            ContentValues cv = new ContentValues();
            cv.put("count", data.size());

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            // вставляем запись и получаем ее ID
            db.insert("data", null, cv);
        }
    }

