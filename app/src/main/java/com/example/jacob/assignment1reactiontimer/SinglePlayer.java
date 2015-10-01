package com.example.jacob.assignment1reactiontimer;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;

/**
 * Created by Jacob on 2015-10-01.
 */
public class SinglePlayer extends AppCompatActivity {

    protected long startTime;
    private int GREY = 0xff444444;
    private int GREEN = 0xff00ff00;


    /*The following two methods are necessary to create the screen and handle the options menu*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player);
        welcomeDialog();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void welcomeDialog() {

        AlertDialog welcome = new AlertDialog.Builder(this).create();

        welcome.setTitle("Welcome to the reaction timer!");
        welcome.setMessage("Hit the button when it changes color, be careful not to hit it " +
                "too early though");
        welcome.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startGame();
            }
        });
        welcome.show();
    }

    protected void startGame() {

        //Customer specification for reaction time variables
        int max = 2000;
        int min = 10;

        Random i = new Random();
        int delay = i.nextInt(max-min) + min;

        final Button button = (Button) findViewById(R.id.singleplayerReactionButton);
        button.setText("Get Ready...");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setBackgroundColor(GREY);
                button.setText("REACT");
                button.setTextColor(GREEN);
                startTime = System.currentTimeMillis();
            }
        }, delay);
    }

    public void singlePlayerClick(View view) {

        final Button button = (Button) findViewById(R.id.singleplayerReactionButton);

        if (button.getCurrentTextColor() != GREY){
            resultDialog(-1);
        } else {
            resultDialog(System.currentTimeMillis());
        }
    }

    protected void resultDialog(long endTime){

        String message;
        if(endTime == -1){
            message = "You jumped the gun!";
        } else {
            message = "Your reaction time was: " + (endTime - startTime) + "ms";
        }

        final AlertDialog result = new AlertDialog.Builder(this).create();
        result.setMessage(message);
        result.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                result.dismiss();
                startGame();
            }
        }, 2000);
    }
}
