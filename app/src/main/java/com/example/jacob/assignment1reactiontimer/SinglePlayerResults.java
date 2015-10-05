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
 * Created by Jacob on 2015-10-04.
 */
public class SinglePlayerResults {

    private String filename = "singlePlayerResults";
    private ArrayList<Long> singlePlayerResults = new ArrayList<>();
    private static SinglePlayerResults thisGame = new SinglePlayerResults();

    public void saveSinglePlayerResults(Context context){
        //The following code reflects what we did with the lonely twitter lab
        try{
            // http://stackoverflow.com/questions/3625837/android-what-is-wrong-with-openfileoutput, naikus, 2015-09-26
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(this.singlePlayerResults, output);
            output.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadSinglePlayerResults(Context context){
        //The following code reflects what we did with the lonely twitter lab
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type hasMapType = new TypeToken<ArrayList<Long>>() {}.getType();
            this.singlePlayerResults = gson.fromJson(in, hasMapType);

        } catch (FileNotFoundException e) {
            this.singlePlayerResults = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearSinglePlayerResults(){
        this.singlePlayerResults.clear();
    }

    public static SinglePlayerResults getGame() {
        return thisGame;
    }

    public void addReactionTime(Long reactionTime, Context context){
        singlePlayerResults.add(reactionTime);
        saveSinglePlayerResults(context);
    }

    //Function for calculating reaction time average, median, etc, etc.
    public HashMap<String, Long> reactionCalculations() {

        HashMap<String, Long> reactionCalculations = new HashMap<>();


        return reactionCalculations;
    }

}
