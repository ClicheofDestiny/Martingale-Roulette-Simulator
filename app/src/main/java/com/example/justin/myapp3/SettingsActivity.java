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
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {

    public int startingBalance;
    public int defaultBet;
    public boolean fastSpin;
    public boolean autoDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);

        Intent intent = getIntent();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        autoDouble = intent.getBooleanExtra("autoDouble", true);
        ToggleButton autoDoubleButton = (ToggleButton) findViewById(R.id.autoDoubleButton);
        autoDoubleButton.setChecked(autoDouble);

        fastSpin= intent.getBooleanExtra("fastSpin", false);
        ToggleButton fastSpinButton = (ToggleButton) findViewById(R.id.FastSpinButton);
        fastSpinButton.setChecked(fastSpin);

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
                ToggleButton autoDoubleButton = (ToggleButton) findViewById(R.id.autoDoubleButton);
                autoDouble = autoDoubleButton.isChecked();
                ToggleButton fastSpinButton = (ToggleButton) findViewById(R.id.FastSpinButton);
                fastSpin = fastSpinButton.isChecked();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("autoDouble", autoDouble);
                returnIntent.putExtra("fastSpin", fastSpin);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
