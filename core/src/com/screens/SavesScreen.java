package com.screens;

import com.Kroy;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.misc.SFX;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import static com.misc.Constants.DEBUG_ENABLED;


/* =================================================================
                    New class added for assessment 3
   ===============================================================*/

        import com.badlogic.gdx.ApplicationListener;
        import com.badlogic.gdx.Game;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.*;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
        import com.badlogic.gdx.utils.Align;
        import com.badlogic.gdx.utils.viewport.ScreenViewport;
        import com.badlogic.gdx.utils.viewport.Viewport;
        import com.Kroy;
        import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import java.util.ArrayList;

import static com.misc.Constants.DEBUG_ENABLED;

/**
 * Screen to tell the user the back story to the game
 * This text is taken from the product brief and sets
 * the scene before the user enters the game
 */
public class SavesScreen implements Screen {

    // A constant variable to store the game
    private final Kroy game;

    private GameScreen gameScreen;

    // visuals and rendering
    private final OrthographicCamera camera;
    private final Stage stage;
    private final Skin skin;
    private final Viewport viewport;

    // Added for assessment 4
    private final String action;

    /**
     * The constructor for the Save screen
     *
     */
    public SavesScreen(Kroy game, GameScreen gameScreen, String action) {
        this.game=game;
        this.gameScreen=gameScreen;
        this.action=action;
        // imports common skin from game
        skin = game.getSkin();

        // Create an orthographic camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // tell the SpriteBatch to render in the coordinate system specified by the camera.
        game.spriteBatch.setProjectionMatrix(camera.combined);

        // Create a viewport
        viewport = new ScreenViewport(camera);
        viewport.apply(true);

        // Set camera to centre of viewport
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        // Create a stage for buttons
        stage = new Stage(viewport, game.spriteBatch);
        stage.setDebugAll(DEBUG_ENABLED);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        ArrayList<String> savedGames = gameScreen.getSavedFiles();

        // Create table to arrange buttons.
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Table labels = new Table();
        labels.center();
        Label label = new Label(action+" Game", new Label.LabelStyle(game.coolFont, Color.WHITE));
        label.setFontScale(2);
        table.add(label).padBottom(20);
        table.row();

        TextButton saveButton = new TextButton(savedGames.get(0), skin);
        table.add(saveButton).width(200).height(40).padBottom(20);
        table.row();
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SFX.sfx_button_click.play();
                if (action.equals("load")){
                    if (!savedGames.get(0).equals("Empty"))
                        gameScreen.loadGame(savedGames.get(0));}
                else {gameScreen.saveGame("1");}

                gameScreen.resume();
                game.setScreen(gameScreen);
                dispose();
            }
        });

        saveButton = new TextButton(savedGames.get(1), skin);
        table.add(saveButton).width(200).height(40).padBottom(20);
        table.row();
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SFX.sfx_button_click.play();
                if (action.equals("load")){
                    if (!savedGames.get(1).equals("Empty"))
                        gameScreen.loadGame(savedGames.get(1));}
                else {gameScreen.saveGame("2");}

                gameScreen.resume();
                game.setScreen(gameScreen);
                dispose();
            }
        });

        saveButton = new TextButton(savedGames.get(2), skin);
        table.add(saveButton).width(200).height(40).padBottom(20);
        table.row();
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SFX.sfx_button_click.play();
                if (action.equals("load")){
                    if (!savedGames.get(2).equals("Empty"))
                        gameScreen.loadGame(savedGames.get(2));}
                else {gameScreen.saveGame("3");}

                gameScreen.resume();
                game.setScreen(gameScreen);
                dispose();
            }
        });

        saveButton = new TextButton(savedGames.get(3), skin);
        table.add(saveButton).width(200).height(40).padBottom(20);
        table.row();
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SFX.sfx_button_click.play();
                if (action.equals("load")){
                    if (!savedGames.get(3).equals("Empty"))
                        gameScreen.loadGame(savedGames.get(3));
                }
                else {
                    System.out.println("savegame");
                    gameScreen.saveGame("4");
                }

                gameScreen.resume();
                game.setScreen(gameScreen);
                dispose();
            }
        });

        saveButton = new TextButton(savedGames.get(4), skin);
        table.add(saveButton).width(200).height(40).padBottom(20);
        table.row();
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SFX.sfx_button_click.play();
                if (action.equals("load")){
                    if (!savedGames.get(4).equals("Empty"))
                        gameScreen.loadGame(savedGames.get(4));}
                else {gameScreen.saveGame("5");
}
                gameScreen.resume();
                game.setScreen(gameScreen);
                dispose();
            }
        });

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    SFX.sfx_button_click.play();
                    game.setScreen(gameScreen);
                    gameScreen.resume();
                    dispose();
                }
                return true;
            }
        });

        stage.addActor(table);

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the button stage
        stage.act(delta);
        stage.draw();
    }

    /**
     * @param width of window
     * @param height of window
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.update();
    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}

