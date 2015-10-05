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
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Jacob on 2015-10-04.
 */
public class SinglePlayerResults {

    private String singlePlayerFile = "singlePlayerResults";
    private ArrayList<Long> singlePlayerResults = new ArrayList<>();
    private static SinglePlayerResults thisGame = new SinglePlayerResults();

    public void saveSinglePlayerResults(Context context){
        //The following code reflects what we did with the lonely twitter lab
        try{
            // http://stackoverflow.com/questions/3625837/android-what-is-wrong-with-openfileoutput, naikus, 2015-09-26
            FileOutputStream fos = context.openFileOutput(singlePlayerFile, Context.MODE_PRIVATE);
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
            FileInputStream fis = context.openFileInput(singlePlayerFile);
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

    public void clearSinglePlayerResults(Context context){
        this.singlePlayerResults.clear();
        saveSinglePlayerResults(context);
    }

    public static SinglePlayerResults getGame() {
        return thisGame;
    }

    public void addReactionTime(Long reactionTime, Context context){
        singlePlayerResults.add(reactionTime);
        saveSinglePlayerResults(context);
    }

    //Functions for calculating reaction time average, median, etc, etc.
    public Long average(int numReactionTimes){
        int size = singlePlayerResults.size();
        if(numReactionTimes > size){
            numReactionTimes = size;
        }
        ArrayList<Long> subList = new ArrayList<>(singlePlayerResults.subList(size-numReactionTimes, size));
        int sum =0;
        for(Long i: subList){
            sum += i;
        }
        int i = sum/numReactionTimes;
        Long l = (long) i;
        return l;
    }

    public Long max(int numReactionTimes){
        int size = singlePlayerResults.size();
        if(numReactionTimes > size){
            numReactionTimes = size;
        }
        ArrayList<Long> subList = new ArrayList<>(singlePlayerResults.subList(size-numReactionTimes, size));
        return Collections.min(subList);
    }
    public Long min(int numReactionTimes){
        int size = singlePlayerResults.size();
        if(numReactionTimes > size){
            numReactionTimes = size;
        }
        ArrayList<Long> subList = new ArrayList<>(singlePlayerResults.subList(size-numReactionTimes, size));
        return Collections.min(subList);
    }
    public Long median(int numReactionTimes){
        int size = singlePlayerResults.size();
        if(numReactionTimes > size){
            numReactionTimes = size;
        }
        ArrayList<Long> subList = new ArrayList<>(singlePlayerResults.subList(size-numReactionTimes, size));
        Collections.sort(subList);

        if(subList.size() % 2 == 1){
            return subList.get((subList.size() - 1) / 2 + 1);
        } else {
            Long upper = subList.get(subList.size() / 2);
            Long lower = subList.get(subList.size() / 2 - 1);
            return (upper + lower) / 2;
        }
    }

    //Put all the data into our hash map for stats screen
    public HashMap<String, Long> reactionCalculations() {

        HashMap<String, Long> singlePlayerStats = new HashMap<>();

        if(singlePlayerResults.isEmpty()){
            singlePlayerStats.put("avgAll", Long.valueOf(0));
            singlePlayerStats.put("maxAll", Long.valueOf(0));
            singlePlayerStats.put("minAll", Long.valueOf(0));
            singlePlayerStats.put("medianAll", Long.valueOf(0));
            singlePlayerStats.put("avg10", Long.valueOf(0));
            singlePlayerStats.put("max10",Long.valueOf(0));
            singlePlayerStats.put("min10", Long.valueOf(0));
            singlePlayerStats.put("median10", Long.valueOf(0));
            singlePlayerStats.put("avg100", Long.valueOf(0));
            singlePlayerStats.put("max100", Long.valueOf(0));
            singlePlayerStats.put("min100", Long.valueOf(0));
            singlePlayerStats.put("median100", Long.valueOf(0));
        } else {
            singlePlayerStats.put("avgAll", average(singlePlayerResults.size()));
            singlePlayerStats.put("maxAll", max(singlePlayerResults.size()));
            singlePlayerStats.put("minAll", min(singlePlayerResults.size()));
            singlePlayerStats.put("medianAll", median(singlePlayerResults.size()));
            singlePlayerStats.put("avg10", average(10));
            singlePlayerStats.put("max10", max(10));
            singlePlayerStats.put("min10", min(10));
            singlePlayerStats.put("median10", median(10));
            singlePlayerStats.put("avg100", average(100));
            singlePlayerStats.put("max100", max(100));
            singlePlayerStats.put("min100", min(100));
            singlePlayerStats.put("median100", median(100));
        }
        return singlePlayerStats;
    }
}
