package com.pathFinding;

/* =================================================================
 *                  New class added for assessment 3
 *  ===============================================================*/

import com.badlogic.gdx.graphics.Texture;
import com.entities.PowerUp;
import com.misc.Constants;

public class Junction {
    final float x;
    final float y;
    final String name;

    int index;

    /** Constructor for Junction
     *
     * @param x     The x position of the junction in pixels
     * @param y     The y position of the junction in pixels
     * @param name  Descriptor of junction location on the map
     *              - used only for help debugging
     */
    public Junction (float x, float y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public PowerUp generatePowerUp(){
        PowerUp powerup;
        switch ((int)(Math.random() * 5)){
            case 0:
                powerup =  new PowerUp(Constants.PowerUpType.healthBoost,x,y);
                break;
            case 1:
                powerup =  new  PowerUp(Constants.PowerUpType.omniBoost,x,y);
                break;
            case 2:
                powerup =  new  PowerUp(Constants.PowerUpType.speedBoost,x,y);
                break;
            case 3:
                powerup =  new  PowerUp(Constants.PowerUpType.waterBoost,x,y);
                break;
            case 4:
                powerup =  new  PowerUp(Constants.PowerUpType.attackBoost,x,y);
                break;
            default:
                powerup =  new PowerUp(Constants.PowerUpType.healthBoost,x,y);
        }
        return powerup;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public String getName(){
        return this.name;
    }

    public int getIndex(){
        return this.index;
    }


}
