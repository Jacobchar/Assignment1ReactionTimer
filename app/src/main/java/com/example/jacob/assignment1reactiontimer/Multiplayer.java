package com.example.jacob.assignment1reactiontimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jacob on 2015-10-01.
 */
public class Multiplayer extends AppCompatActivity {

    /*The following two methods are necessary to create the screen and handle the options menu*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void playerSelection(View view) {

        Button button = (Button) view;
        Intent intent;

        switch(button.getId()) {
            case R.id.twoPlayerButton:
                intent = new Intent(this, TwoPlayer.class);
                startActivity(intent);
                break;
            case R.id.threePlayerButton:
                intent = new Intent(this, ThreePlayer.class);
                startActivity(intent);
                break;
            case R.id.fourPlayerButton:
                intent = new Intent(this, FourPlayer.class);
                startActivity(intent);
                break;
        }
    }
}
