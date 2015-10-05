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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;

/**
 * Created by Jacob on 2015-10-01.
 */
public class Statistics extends AppCompatActivity {

    private SinglePlayerResults reactionTimes = SinglePlayerResults.getGame();
    private MultiplayerResults multiplayerResults = MultiplayerResults.getGame();

    //Load files
    private void loadResults() {
        reactionTimes.loadSinglePlayerResults(this.getBaseContext());
        multiplayerResults.loadMultiplayerResults(this.getBaseContext());

        HashMap<String, Long> singlePlayerStats = reactionTimes.reactionCalculations();
        HashMap<String, Integer> multiplayerStats = multiplayerResults.multiplayerStats();

        displaySinglePlayerStats(singlePlayerStats);
        displayMultiplayerStats(multiplayerStats);

    }

    private void displaySinglePlayerStats(HashMap<String, Long> stats) {

    }

    private void displayMultiplayerStats(HashMap<String, Integer> stats){

    }

    //Clear Files
    private void clearResults(){
        this.reactionTimes.clearSinglePlayerResults();
        this.multiplayerResults.clearMultiplayerResults();
        loadResults();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        loadResults();

        Button clear = (Button) findViewById(R.id.clearResults);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearResults();
            }
        });

        Button email = (Button) findViewById(R.id.emailButton);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailResults();
            }
        });
    }

    public void emailResults() {
        //How to email
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
}
