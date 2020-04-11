package com.misc;

import com.entities.ETFortress;
import com.entities.Patrol;
import com.screens.GameScreen;

/*
 *  =======================================================================
 *                          Added for Assessment 4
 *  =======================================================================
 */
public class Achievement {



    private Integer type;
    /**
     1 - No. of Alien Kills
     2 - No. of Fortresses Destroyed
     */
    private String name;

    private float goalValue;
    private float currentValue;

    private boolean complete;

    private Integer timeCondition;
    private Integer timeAtFirstValue;

    private int scoreValue;

    public Achievement(String name, float goalValue, int scoreValue, Integer type, Integer timeCondition){
        this.timeCondition = timeCondition;
        this.name = name;
        this.type = type;
        this.goalValue = goalValue;
        this.scoreValue = scoreValue;
    }

    public Achievement(String name, float goalValue, int scoreValue, Integer type){
        this.name = name;
        this.type = type;
        this.goalValue = goalValue;
        this.scoreValue = scoreValue;
        this.timeCondition = null;
    }

    void checkCondition(int gameTime){
        if (timeCondition == null) { //if no time condition
            currentValue++; //then increase the value
        }
        else if (timeAtFirstValue == null) { //if there is a time condition but this is the first kill
            currentValue++; //then increase the value
            timeAtFirstValue = gameTime; //update time counter
        }
        else if (timeCondition >= (gameTime - timeAtFirstValue)) {//if there's a time condition and it is met
            currentValue++; //then increase the value
        }
        else{ //if the time condition isn't met
            currentValue = 1; //then reset the value
            timeAtFirstValue = gameTime; //update current time counter
        }
    }

    void checkPatrolDeath(GameScreen gameScreen){
        for (Patrol patrol : gameScreen.ETPatrols){
            if(patrol.isDead()) {
                checkCondition(gameScreen.getTime());
            }
        }
    }

    void checkFortressDeath(GameScreen gameScreen){
        for (ETFortress fortress : gameScreen.ETFortresses){
            if(fortress.isFlooded()) {
                checkCondition(gameScreen.getTime());
            }
        }
    }

    public void update(GameScreen gameScreen){
        if(!complete) {
            if (evaluateAchievement(gameScreen)) {
                gameScreen.setScore(gameScreen.getScore() + scoreValue);
                System.out.println(name + " Achievement complete! at times: " + timeAtFirstValue + ", " + gameScreen.getTime());
                complete = true;
            }
        }
    }

    boolean evaluateAchievement(GameScreen gameScreen){
        switch (type){
            case 1:
                checkPatrolDeath(gameScreen);
                break;
            case 2:
                checkFortressDeath(gameScreen);
                break;
            default:
                return false;
        }
        return (goalValue <= currentValue);
    }


    public String getStatusMessage(int currentTime){
        String s= "";
        s+="Kill " + this.goalValue;
        s+= this.type==0 ? " patrols" : " fortresses";
        String seconds = this.timeAtFirstValue==null ? String.valueOf(this.timeCondition) : String.valueOf((this.timeAtFirstValue - currentTime));
        s+=" in "+ seconds + " seconds";

        return s;
    }

}
