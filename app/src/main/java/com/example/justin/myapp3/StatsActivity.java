package com.example.justin.myapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    public ArrayList<Character> history;
    public ArrayList<Character> winHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


    }
}
