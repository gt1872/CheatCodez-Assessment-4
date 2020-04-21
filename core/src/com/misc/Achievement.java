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

    /**
     * Constructor for achievements
     * @param name name of the task
     * @param goalValue how many you want to destroy
     * @param scoreValue score once complete
     * @param type type 1 or 2 depending on what you want destroyed
     * @param timeCondition time to complete
     */
    public Achievement(String name, float goalValue, int scoreValue, Integer type, Integer timeCondition){
        this.timeCondition = timeCondition;
        this.name = name;
        this.type = type;
        this.goalValue = goalValue;
        this.scoreValue = scoreValue;

        this.complete = false;
        this.timeAtFirstValue = null;
        System.out.println(timeCondition);
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

    /* Check the current achievements status */
    public void update(GameScreen gameScreen){
        // If not complete yet
        if(!complete) {
            // Check the achievement
            if (evaluateAchievement(gameScreen)) {
                gameScreen.setScore(gameScreen.getScore() + scoreValue);
                System.out.println(name + " Achievement complete! at times: " + timeAtFirstValue + ", " + gameScreen.getTime());
                complete = true;
            }
        }
    }

    /* Check the achievement against the appropriate condition */
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
        System.out.println(goalValue);
        System.out.println(currentValue);
        return (goalValue <= currentValue);
    }


    public String getStatusMessage(int currentTime){
        if (!complete){
            String s= "";
            s+="Kill " + (this.goalValue - this.currentValue);
            s+= this.type==1 ? " patrols " : " fortresses ";
            String seconds = this.timeAtFirstValue==null ? String.valueOf(this.timeCondition) : String.valueOf(timeCondition-(this.timeAtFirstValue - currentTime));
            s+=" in "+ seconds + " seconds";
            return s;
        } else if (timeCondition-(this.timeAtFirstValue - currentTime)<1){

            return "FAILED MISSION!";
        } else {
            return null;
        }
    }

    public boolean isComplete() {return this.complete;}

}
