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
package com.example.jacob.charlebo_reflex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Jacob on 2015-10-01.
 */
public class Statistics extends AppCompatActivity {
    /*
    The purpose of this class is to load and store the data from the rest of the game and store them
    in hash maps. This class handles the loading, saving, clearing, emailing, and displays all the
    data to the statistics screen. Though the method for emailing should work flawlessly I had
    trouble with my intent.setData(Uri.parse("mailto:")) command (caused stats to crash).
    */

    private SinglePlayerResults reactionTimes = SinglePlayerResults.getGame();
    private MultiplayerResults multiplayerResults = MultiplayerResults.getGame();

    //Load files
    private void loadResults() {
        reactionTimes.loadSinglePlayerResults(this.getBaseContext());
        multiplayerResults.loadMultiplayerResults(this.getBaseContext());

        HashMap<String, Long> singlePlayerResults = reactionTimes.reactionCalculations();
        HashMap<String, Integer> multiplayerStats = multiplayerResults.multiplayerStats();

        displaySinglePlayerStats(singlePlayerResults);
        displayMultiplayerStats(multiplayerStats);

    }

    private void displaySinglePlayerStats(HashMap<String, Long> stats) {
        TextView statText = (TextView) findViewById(R.id.avgAll);
        statText.setText(stats.get("avgAll").toString());
        statText = (TextView) findViewById(R.id.maxAll);
        statText.setText(stats.get("maxAll").toString());
        statText = (TextView) findViewById(R.id.minAll);
        statText.setText(stats.get("minAll").toString());
        statText = (TextView) findViewById(R.id.medianAll);
        statText.setText(stats.get("medianAll").toString());
        statText = (TextView) findViewById(R.id.avg10);
        statText.setText(stats.get("avg10").toString());
        statText = (TextView) findViewById(R.id.max10);
        statText.setText(stats.get("max10").toString());
        statText = (TextView) findViewById(R.id.min10);
        statText.setText(stats.get("min10").toString());
        statText = (TextView) findViewById(R.id.median10);
        statText.setText(stats.get("median10").toString());
        statText = (TextView) findViewById(R.id.avg100);
        statText.setText(stats.get("avg100").toString());
        statText = (TextView) findViewById(R.id.max100);
        statText.setText(stats.get("max100").toString());
        statText = (TextView) findViewById(R.id.min100);
        statText.setText(stats.get("min100").toString());
        statText = (TextView) findViewById(R.id.median100);
        statText.setText(stats.get("median100").toString());
    }

    private void displayMultiplayerStats(HashMap<String, Integer> stats){
        TextView statText = (TextView) findViewById(R.id.p1m2TextView);
        statText.setText(stats.get("p1m2").toString());
        statText = (TextView) findViewById(R.id.p1m3TextView);
        statText.setText(stats.get("p1m3").toString());
        statText = (TextView) findViewById(R.id.p1m4TextView);
        statText.setText(stats.get("p1m4").toString());
        statText = (TextView) findViewById(R.id.p2m2TextView);
        statText.setText(stats.get("p2m2").toString());
        statText = (TextView) findViewById(R.id.p2m3TextView);
        statText.setText(stats.get("p2m3").toString());
        statText = (TextView) findViewById(R.id.p2m4TextView);
        statText.setText(stats.get("p2m4").toString());
        statText = (TextView) findViewById(R.id.p3m3TextView);
        statText.setText(stats.get("p3m3").toString());
        statText = (TextView) findViewById(R.id.p3m4TextView);
        statText.setText(stats.get("p3m4").toString());
        statText = (TextView) findViewById(R.id.p4m4TextView);
        statText.setText(stats.get("p4m4").toString());
    }

    //Clear Files
    private void clearResults(){
        this.reactionTimes.clearSinglePlayerResults(this.getBaseContext());
        this.multiplayerResults.clearMultiplayerResults(this.getBaseContext());
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

    private void emailResults(){
        //From:http://www.tutorialspoint.com/android/android_sending_email.htm
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        //intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "charlebo-reflex stats");
        intent.putExtra(Intent.EXTRA_TEXT, emailString());
        try {
            startActivity(Intent.createChooser(intent, "Send mail with:"));
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(Statistics.this, "There are no email clients installed", Toast.LENGTH_SHORT).show();
        }
    }

    private String emailString() {
        HashMap<String, Long> singlePlayerResults = this.reactionTimes.reactionCalculations();
        HashMap<String, Integer> multiplayerStats = this.multiplayerResults.multiplayerStats();

        String email = "Reaction Timer:\n" +
                "All Values: AVG - " + singlePlayerResults.get("avgAll").toString() +
                " Max - " + singlePlayerResults.get("maxAll").toString() +
                " Min - " + singlePlayerResults.get("minAll").toString() +
                " Median - " + singlePlayerResults.get("medianAll").toString() + "\n" +
                "Last 10 Values: AVG - " + singlePlayerResults.get("avg10").toString() +
                " Max - " + singlePlayerResults.get("max10").toString() +
                " Min - " + singlePlayerResults.get("min10").toString() +
                " Median - " + singlePlayerResults.get("median10").toString() + "\n" +
                "Last 100 Values: AVG - " + singlePlayerResults.get("avg100").toString() +
                " Max - " + singlePlayerResults.get("max100").toString() +
                " Min - " + singlePlayerResults.get("min100").toString() +
                " Median - " + singlePlayerResults.get("median100").toString() + "\n" +
                "Two Player Mode : \nPlayer 1 wins: " + multiplayerStats.get("p1m2").toString() +
                " Player 2 wins: " + multiplayerStats.get("p2m2").toString() + "\n" +
                "Three Player Mode : \nPlayer 1 wins: " + multiplayerStats.get("p1m3").toString() +
                " Player 2 wins: " + multiplayerStats.get("p2m3").toString() + "\n" +
                " Player 3 Wins:" + multiplayerStats.get("p3m3").toString() + "\n" +
                "Four Player Mode : \nPlayer 1 wins: " + multiplayerStats.get("p1m4").toString() +
                " Player 2 wins: " + multiplayerStats.get("p2m4").toString() + "\n" +
                " Player 3 Wins:" + multiplayerStats.get("p3m4").toString() + "\n" +
                " Player 4 wins:" + multiplayerStats.get("p4m4").toString() + "\n";
        return email;
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
