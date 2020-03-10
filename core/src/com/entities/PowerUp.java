package com.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.misc.Constants;
import com.sprites.SimpleSprite;

import static com.misc.Constants.FIRESTATION_HEIGHT;
import static com.misc.Constants.FIRESTATION_WIDTH;

public class PowerUp extends SimpleSprite {

    private Constants.PowerUpType type;

    /**
     * Constructor that creates a sprite at a given position using a given texture..
     * Creates a sprite at (0,0) using a given texture.
     *
     * @param type  contains the texture the sprite should use and powerup attributes.
     */
    public PowerUp(Constants.PowerUpType type ,float xPos, float yPos) {
        super(type.getTexture());
        this.setPosition(xPos, yPos);
        this.type = type;
        this.setSize(FIRESTATION_WIDTH/2, FIRESTATION_WIDTH/2);
        System.out.println("Created PowerUp at " + xPos + ", " +  yPos);
    }

    /**
     * Updates the powerup so that it is drawn every frame.
     * Also reduces the time before next repair can occur.
     *
     * @param batch  The batch to draw onto.
     */
    public void update(Batch batch) {
        super.update(batch);
    }

    @Override
    public void drawDebug(ShapeRenderer renderer) {
        super.drawDebug(renderer);
    }

    public Constants.PowerUpType getType() {
        return type;
    }
}
