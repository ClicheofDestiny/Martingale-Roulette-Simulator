package com.example.justin.myapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private int startingBalance;
    private int defaultBet;

    /**called when user taps the Play Game button*/
    public void onStartPressed(View view){
        EditText menuBalanceText = (EditText) findViewById(R.id.MenuBalanceInput);
        startingBalance = Integer.parseInt(menuBalanceText.getText().toString());

        EditText menuDefaultText = (EditText) findViewById(R.id.MenuDefaultInput);
        defaultBet = Integer.parseInt(menuDefaultText.getText().toString());

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("MainBalance", startingBalance);
        intent.putExtra("MainDefaultBet", defaultBet);
        this.startActivity(intent);

    }

}
