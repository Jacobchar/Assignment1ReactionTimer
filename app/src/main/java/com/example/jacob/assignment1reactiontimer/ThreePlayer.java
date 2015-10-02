package com.example.jacob.assignment1reactiontimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jacob on 2015-10-01.
 */
public class ThreePlayer extends AppCompatActivity {

    /*The following two methods are necessary to create the screen and handle the options menu*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_player);
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

    public void declareWinner(View view) {

        Button button = (Button) view;
        String player = (String) button.getText();
        String message = player + " was victorious!";

        final AlertDialog result = new AlertDialog.Builder(this).create();
        result.setTitle("Winner:");
        result.setMessage(message);
        result.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        result.show();
    }
}
