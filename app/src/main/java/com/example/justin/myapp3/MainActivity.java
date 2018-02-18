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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE= "com.example.justin.myapp3.MESSAGE";
    private int balance;
    public int startingBalance;
    public boolean currBet; //blackj is true, red is false
    public int betAmount;
    public int profit = 0;
    public int defaultBet;
    public int currLoss = 0;
    public boolean autoDouble = true;
    public boolean fastSpin = false;
    public ArrayList<Character> history;
    public ArrayList<Character> winHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        history = new ArrayList<Character>();
        winHistory = new ArrayList<Character>();
        balance = startingBalance;
        betAmount = 0;

        Intent intent = getIntent();
        startingBalance = intent.getIntExtra("MainBalance", 1000);
        TextView balanceText = (TextView) findViewById(R.id.Balance);
        balance = startingBalance;
        balanceText.setText("Balance: $" + Integer.toString(balance));

        defaultBet = intent.getIntExtra("MainDefaultBet", 10);
        EditText defaultText = (EditText) findViewById(R.id.Bet);
        defaultText.setText(Integer.toString(defaultBet));

        TextView resultText = (TextView) findViewById(R.id.ResultText);
        resultText.setText("?????");
    }

    /** Called when the user taps the Bet buttons */
    public void PlayRound(View view) {
        String outcomeString = "";

        EditText betText = (EditText) findViewById(R.id.Bet);
        String betString = betText.getText().toString();
        betAmount = Integer.parseInt(betString);
        ImageView resultImage = (ImageView) findViewById(R.id.ResultImage);

        switch(view.getId()) {
            case R.id.BlackButton:
                currBet = true;
                break;
            case R.id.RedButton:
                currBet = false;
                break;
        }

        Random r = new Random();
        int outcome = r.nextInt(37);
        boolean outBool = false;

        if (outcome == 0){
            outcomeString = "0!";
            outBool = !currBet;
            resultImage.setImageResource(R.drawable.greenzeroroulette);
            listAdd(history, 'G');
        } else if (outcome % 2 == 0){
            outcomeString = "Red!";
            outBool = false;
            resultImage.setImageResource(R.drawable.redoutcomeimg);
            listAdd(history, 'R');
        } else {
            outcomeString = "Black!";
            outBool = true;
            resultImage.setImageResource(R.drawable.black);
            listAdd(history, 'B');
        }

        if(currBet == outBool){
            outcomeString = outcomeString + " You win!";
            balance += betAmount;
            profit += betAmount;
            currLoss = 0;
            listAdd(winHistory, 'W');
            if(autoDouble) {
                betText.setText(Integer.toString(defaultBet));
            }
        } else {
            outcomeString = outcomeString + " You lose!";
            balance -= betAmount;
            currLoss += betAmount;
            profit -= betAmount;
            listAdd(winHistory, 'L');
            if(autoDouble) {
                betText.setText(Integer.toString(betAmount * 2));
            }
        }

        TextView balanceText = (TextView) findViewById(R.id.Balance);
        balanceText.setText("Balance: $" + Integer.toString(balance));

        TextView resultText = (TextView) findViewById(R.id.ResultText);
        resultText.setText(outcomeString);

        TextView profitText = (TextView) findViewById(R.id.ProfitBox);
        profitText.setText("Profit: $" + Integer.toString(profit));

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
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.putExtra("balance", balance);
                intent.putExtra("defaultBet", defaultBet);
                intent.putExtra("fastSpin", fastSpin);
                intent.putExtra("autoDouble", autoDouble);
                this.startActivityForResult(intent, 1);
                return true;
            case R.id.action_statistics:
                // User chose the "Statistics" item, show stats
                Intent intent = new Intent(this, StatsActivity.class);
                intent.putExtra("history", history);
                intent.putExtra("winHistory", winHistory);
                this.startActivity(intent);
                return true;
            case R.id.action_play:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void onReset(View view) {
        balance = startingBalance;
        profit = 0;
        currLoss = 0;
        TextView balanceText = (TextView) findViewById(R.id.Balance);
        balanceText.setText("Balance: " + Integer.toString(balance));
        TextView profitText = (TextView) findViewById(R.id.ProfitBox);
        profitText.setText("Profit: " + Integer.toString(profit));
        EditText betText = (EditText) findViewById(R.id.Bet);
        betText.setText(Integer.toString(defaultBet));

    }

    public void quickDouble(View view){
        EditText betText = (EditText) findViewById(R.id.Bet);
        betText.setText(Integer.toString(currLoss * 2), TextView.BufferType.EDITABLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                autoDouble = data.getBooleanExtra("autoDouble", true);
                fastSpin = data.getBooleanExtra("fastSpin", false);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public static void listAdd(ArrayList<Character> list, Character c){
        list.add(c);
        if(list.size() > 50) {
            list.remove(list.size() - 1);
        }
    }


}

