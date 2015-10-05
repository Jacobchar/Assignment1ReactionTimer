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

import android.content.Context;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jacob on 2015-10-02.
 */

// This class will handle the Hashmap I used to store and save the data from the single and multiplayer modes (shout out to the labs for the help with saving and loading with gson)
public class MultiplayerResults {

    private String filename = "multiplayerResults";
    private HashMap<Integer, ArrayList<Integer>> multiplayerResults = new HashMap<>();
    private static MultiplayerResults thisGame = new MultiplayerResults();

    public void saveMultiplayerResults(Context context){
        //The following code reflects what we did with the lonely twitter lab
        try{
            // http://stackoverflow.com/questions/3625837/android-what-is-wrong-with-openfileoutput, naikus, 2015-09-26
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(this.multiplayerResults, output);
            output.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMultiplayerResults(Context context){
        //The following code reflects what we did with the lonely twitter lab
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type hasMapType = new TypeToken<HashMap<Integer, ArrayList<Integer>>>() {}.getType();
            this.multiplayerResults = gson.fromJson(in, hasMapType);

        } catch (FileNotFoundException e) {
            this.multiplayerResults = new HashMap<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearMultiplayerResults(){
        this.multiplayerResults.clear();
    }

    public static MultiplayerResults getGame() {
        return thisGame;
    }

   public void addWinner(Integer gameMode, Integer player, Context buttonContext){
        ArrayList<Integer> winner = this.multiplayerResults.get(gameMode);
        if (winner == null){
            winner = new ArrayList<>();
        } else {
            winner.add(player);
        }
        this.multiplayerResults.put(gameMode, winner);
        saveMultiplayerResults(buttonContext);
    }

    public HashMap<String, Integer> multiplayerStats() {
        //win count for all the players in all the modes
        Integer p1m2WinCount,p1m3WinCount, p1m4WinCount, p2m2WinCount, p2m3WinCount,
                p2m4WinCount, p3m3WinCount, p3m4WinCount,p4m4WinCount;
        p1m2WinCount=p1m3WinCount=p1m4WinCount=p2m2WinCount=p2m3WinCount=p2m4WinCount=p3m3WinCount=p3m4WinCount=p4m4WinCount=0;

        ArrayList<Integer> twoPlayer = this.multiplayerResults.get(2);
        ArrayList<Integer> threePlayer = this.multiplayerResults.get(3);
        ArrayList<Integer> fourPlayer = this.multiplayerResults.get(4);

        //if null create array list
        if(twoPlayer == null){
            twoPlayer = new ArrayList<>();
        }
        if(threePlayer == null){
            threePlayer = new ArrayList<>();
        }
        if(fourPlayer == null){
            fourPlayer = new ArrayList<>();
        }

        //Incrementing the multiplayer counters on victory
        for(Integer player:twoPlayer){
            if(player.equals(1)){
                p1m2WinCount ++;
            } else if(player.equals(2)){
                p2m2WinCount ++;
            }
        }
        for(Integer player:threePlayer){
            if(player.equals(1)){
                p1m3WinCount ++;
            } else if(player.equals(2)){
                p2m3WinCount ++;
            } else if(player.equals(3)){
                p3m3WinCount ++;
            }
        }

        for(Integer player:fourPlayer){
            if(player.equals(1)){
                p1m4WinCount ++;
            } else if(player.equals(2)){
                p2m4WinCount ++;
            } else if(player.equals(3)){
                p3m4WinCount ++;
            } else if(player.equals(4)){
                p4m4WinCount ++;
            }
        }

        //Place wins in hash map
        HashMap<String, Integer> multiplayerStats = new HashMap<>();

        multiplayerStats.put("p1m2", p1m2WinCount);
        multiplayerStats.put("p1m3", p1m3WinCount);
        multiplayerStats.put("p1m4", p1m4WinCount);
        multiplayerStats.put("p2m2", p2m2WinCount);
        multiplayerStats.put("p2m3", p2m3WinCount);
        multiplayerStats.put("p2m4", p2m4WinCount);
        multiplayerStats.put("p3m3", p3m3WinCount);
        multiplayerStats.put("p3m4", p3m4WinCount);
        multiplayerStats.put("p4m4", p4m4WinCount);

        return multiplayerStats;
    }
}
