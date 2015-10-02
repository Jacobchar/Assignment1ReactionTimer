package com.example.jacob.assignment1reactiontimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jacob on 2015-10-01.
 */
public class FourPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_player);

        //Create the multiplayer buttons
        new MultiplayerButton((Button) findViewById(R.id.p1ModeFourButton), 1, 4, FourPlayer.this);
        new MultiplayerButton((Button) findViewById(R.id.p2ModeFourButton), 2, 4, FourPlayer.this);
        new MultiplayerButton((Button) findViewById(R.id.p3ModeFourButton), 3, 4, FourPlayer.this);
        new MultiplayerButton((Button) findViewById(R.id.p4ModeFourButton), 4, 4, FourPlayer.this);
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
