package com.entities;

// LibGDX imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;

// Custom class import
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.misc.Constants.*;
import com.screens.GameScreen;
import com.sprites.SimpleSprite;

// Constants import
import java.io.Serializable;
import java.util.Iterator;

import static com.misc.Constants.ETFORTRESS_HEIGHT;
import static com.misc.Constants.ETFORTRESS_WIDTH;

/**
 * The ET Fortress implementation, a static sprite in the game.
 * 
 * @author Archie
 * @since 16/12/2019
 */
public class ETFortress extends SimpleSprite implements Json.Serializable {

    // Private values for this class to use
    private Texture destroyed;
    private boolean flooded;
    private FortressType type =null;
    private GameScreen gameScreen = null;

    /**
     * Overloaded constructor containing all possible parameters.
     * Drawn with the given texture at the given position.
     * 
     * @param texture           The texture used to draw the ETFortress with.
     * @param destroyedTexture  The texture used to draw the ETFortress with. when it has been destroyed.
     * @param scaleX            The scaling in the x-axis.
     * @param scaleY            The scaling in the y-axis.
     * @param xPos              The x-coordinate for the ETFortress.
     * @param yPos              The y-coordinate for the ETFortress.
     * @param type              {@link FortressType} given to fortress
     * @param gameScreen        GameScreen to send popup messages to
     */
    public ETFortress(Texture texture, Texture destroyedTexture, float scaleX, float scaleY, float xPos, float yPos, FortressType type, GameScreen gameScreen) {
        super(texture);

        this.gameScreen = gameScreen;
        this.destroyed = destroyedTexture;
        this.flooded = false;
        this.type = type;
        this.setScale(scaleX, scaleY);
        this.setPosition(xPos, yPos);
        this.setSize(ETFORTRESS_WIDTH * this.getScaleX(), ETFORTRESS_HEIGHT * this.getScaleY());
        this.getHealthBar().setMaxResource(type.getHealth());
        super.resetRotation(90);
    }

    public ETFortress(Texture texture, Texture destroyedTexture, float scaleX, float scaleY, float xPos, float yPos, FortressType type, GameScreen gameScreen, int health) {
        this(texture, destroyedTexture, scaleX, scaleY, xPos, yPos, type, gameScreen);
        this.getHealthBar().subtractResourceAmount(health);
    }

        /**
         * Update the fortress so that it is drawn every frame.
         * @param batch  The batch to draw onto.
         */
    public void update(Batch batch) {
        super.update(batch);
        // If ETFortress is destroyed, change to flooded texture
        // If ETFortress is damaged, heal over time
        if (!flooded && this.getHealthBar().getCurrentAmount() <= 0) {
            this.removeSprite(this.destroyed);
            this.flooded = true;
            this.gameScreen.showPopupText("You have destroyed " + gameScreen.getETFortressesDestroyed()[0] + " / " + gameScreen.getETFortressesDestroyed()[1] +
                    " fortresses", 1, 7);
        } else if (!flooded && this.getInternalTime() % 150 == 0 && this.getHealthBar().getCurrentAmount() != this.getHealthBar().getMaxAmount()) {
            // Heal ETFortresses every second if not taking damage
			this.getHealthBar().addResourceAmount(type.getHealing());
        }
    }

    /**
     * Check to see if the ETFortress should fire another projectile. The pattern the ETFortress
     * uses to fire can be modified here. 
     * 
     * @return boolean  Whether the ETFortress is ready to fire again (true) or not (false)
     */
    public boolean canShootProjectile() {
        return this.getHealthBar().getCurrentAmount() > 0 && this.getInternalTime() < 120 && this.getInternalTime() % 30 == 0;
    }

    // ==============================================================
    //			          Edited for Assessment 3
    // ==============================================================
    /**
     * Simple method for detecting whether a vector, fire truck,
     * is within attacking distance of that fortress' range
     *
     * @param position  vector to check
     * @return          <code>true</code> position is within range
     *                  <code>false</code> otherwise
     */
    public boolean isInRadius(Vector2 position) {
        return this.getCentre().dst(position) <= type.getRange();
    }

    /**
     * Overloaded method for drawing debug information. Draws the hitbox as well
     * as the range circle.
     * 
     * @param renderer  The renderer used to draw the hitbox and range indicator with.
     */
    @Override
    public void drawDebug(ShapeRenderer renderer) {
        super.drawDebug(renderer);
        renderer.circle(this.getCentreX(), this.getCentreY(), this.type.getRange());
    }

    public boolean isFlooded() {
        return this.flooded;
    }

    public FortressType getType() {
        return this.type;
    }


    /*

    =========================================================
                    Added for assessment 4
    =========================================================

     */

    public ETFortress(){
        super(new Texture("MapAssets/UniqueBuildings/Yorkminster_wet.png"));

    }



    @Override
    public void write(Json json) {
        json.writeValue("flooded", flooded);
        json.writeValue("fortressType", type.getStatus());
        json.writeValue("health", type.getHealth()-this.getHealthBar().getCurrentAmount());
        json.writeValue("xPos", this.getX());
        json.writeValue("yPos", this.getY());
        json.writeValue("texture", this.getTexture().toString());
        json.writeValue("destroyedTexture", this.destroyed.toString());
        json.writeValue("xScale", this.getScaleX());
        json.writeValue("yScale", this.getScaleY());
        json.writeValue("class", ETFortress.class.toString());
    }


    @Override
    public void read(Json json, JsonValue jsonData) { }

    public String toString(){
        return String.format("FLOODED = %b | TYPE = %s | HEALTH = %f | TEXTURE = %s",flooded, type, getHealthBar().getCurrentAmount(), this.getTexture().toString());
    }

    public void setFlooded(boolean flooded) {
        this.flooded = flooded;
    }

}