package com.example.jacob.assignment1reactiontimer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jacob on 2015-10-02.
 */
public class MultiplayerButton {

    private Button button;
    private Integer player, gameMode;
    private Context buttonContext;
    //private GameWinner gameWinner = GameWinner.getInstance();


    public MultiplayerButton(Button button, final Integer player, final Integer gameMode, final Context buttonContext){

        this.button = button;
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declareWinner();
                //gameWinner.addWinner(gameMode, player, buttonContext);
            }
        });
        this.player = player;
        this.gameMode = gameMode;
        this.buttonContext = buttonContext;
    }

    public void declareWinner() {

        String player = (String) button.getText();
        String message = player + " was victorious!";

        final AlertDialog result = new AlertDialog.Builder(buttonContext).create();
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
