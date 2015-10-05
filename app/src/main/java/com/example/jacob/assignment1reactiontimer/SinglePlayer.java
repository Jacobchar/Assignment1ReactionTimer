/*
    Reaction Timer: Reaction timer and multi-player game show buzzer
    Copyright (C) 2015  Jacob Charlebois

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.example.jacob.assignment1reactiontimer;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jacob on 2015-10-01.
 */
public class SinglePlayer extends AppCompatActivity {

    protected long startTime;
    private int GREY = 0xff444444;
    private int GREEN = 0xff00ff00;
    private int BLACK = 0xff000000;
    private long reactionTime;
    private SinglePlayerResults reactionTimes = SinglePlayerResults.getGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player);
        welcomeDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        button.setBackgroundColor(GREY);
        button.setTextColor(BLACK);
        button.setText("Get Ready...");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setText("REACT");
                button.setTextColor(GREEN);
                startTime = System.currentTimeMillis();
            }
        }, delay);
    }

    public void singlePlayerClick(View view) {

        final Button button = (Button) findViewById(R.id.singleplayerReactionButton);

        if (button.getCurrentTextColor() != GREEN){
            resultDialog(-1);
        } else {
            resultDialog(System.currentTimeMillis());
        }
    }

    protected void resultDialog(long endTime){

        reactionTime = (endTime - startTime);

        String message;
        if(endTime == -1){
            message = "You jumped the gun!";
        } else {
            message = "Your reaction time was: " + reactionTime + "ms";
            this.reactionTimes.addReactionTime(reactionTime, this.getBaseContext());
        }

        final AlertDialog result = new AlertDialog.Builder(this).create();
        result.setMessage(message);
        result.show();
        reactionTimes.addReactionTime(reactionTime, this.getBaseContext());

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
