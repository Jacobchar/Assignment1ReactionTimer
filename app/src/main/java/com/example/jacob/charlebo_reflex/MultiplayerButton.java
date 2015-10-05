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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jacob on 2015-10-02.
 */
public class MultiplayerButton {

    //Handy variables we need to properly store data
    private Button button;
    private Integer player, gameMode;
    private Context buttonContext;
    private MultiplayerResults gameWinner = MultiplayerResults.getGame();

    public MultiplayerButton(Button button, final Integer player, final Integer gameMode, final Context buttonContext){

        this.player = player;
        this.gameMode = gameMode;
        this.buttonContext = buttonContext;
        this.button = button;
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameWinner.addWinner(gameMode, player, buttonContext);
                declareWinner();

            }
        });
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
