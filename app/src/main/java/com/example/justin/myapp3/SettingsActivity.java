package com.example.justin.myapp3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    public int startingBalance;
    public int defaultBet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);

        Intent intent = getIntent();
        startingBalance = intent.getIntExtra("balance", 1000);
        EditText balanceText = (EditText) findViewById(R.id.BalanceInput);
        balanceText.setText(Integer.toString(startingBalance));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        defaultBet = intent.getIntExtra("defaultBet", 10);
        EditText defaultText = (EditText) findViewById(R.id.defaultInput);
        defaultText.setText(Integer.toString(defaultBet));

        startingBalance = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_play:
                //set balance to what has been entered
                EditText balanceText = (EditText) findViewById(R.id.BalanceInput);
                String balanceString = balanceText.getText().toString();
                startingBalance = Integer.parseInt(balanceString);
                EditText defaultText = (EditText) findViewById(R.id.defaultInput);
                String defaultString = defaultText.getText().toString();
                defaultBet = Integer.parseInt(defaultString);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("balanceSetting", startingBalance);
                returnIntent.putExtra("resultDefaultBet", defaultBet);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void setStartingBalance(View view) {
        EditText balanceText= (EditText) findViewById(R.id.StartingBalanceText);
        String balanceString = balanceText.getText().toString();
        startingBalance = Integer.parseInt(balanceString);
    }

}
