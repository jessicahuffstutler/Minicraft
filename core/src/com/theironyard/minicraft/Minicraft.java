package com.theironyard.minicraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Minicraft extends ApplicationAdapter {
    final int WIDTH = 100;
    final int HEIGHT = 100;

    SpriteBatch batch;
    TextureRegion down, up, right, left;
    FitViewport viewport;

    float x = 0;
    float y = 0;
    float xVelocity = 0;
    float yVelocity = 0;

    final float MAX_VELOCITY = 100;

    @Override
    public void create () {
        batch = new SpriteBatch();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][0];
        up = grid[6][1];
        right = grid[6][3];
        left = new TextureRegion(right);
        left.flip(true, false);
    }

    @Override
    public void render () {
        move();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) { //it takes an integer as the key which will represent the UP arrow
            yVelocity = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) { //if we do if, else if, else if...etc, we could not push the keys at the same time. so 4 if statements are needed
            yVelocity = MAX_VELOCITY * -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xVelocity = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xVelocity = MAX_VELOCITY * -1;
        }

        x += xVelocity * Gdx.graphics.getDeltaTime(); //makes it move but takes into account pixel refresh rate
        y += yVelocity * Gdx.graphics.getDeltaTime();

        //screen parameters
        if (y < 0 ) { //player doesn't go below y axis
            y = 0;
        }

        if (x < 0) { //player doesn't go to the left of x axis
            x = 0;
        }

        if (y > viewport.getScreenHeight() - HEIGHT) { //player doesn't go past right side of screen
            y = viewport.getScreenHeight() - HEIGHT;
        }

        if (x > viewport.getScreenWidth() - WIDTH) { //player doesn't go past top of screen
            x = viewport.getScreenWidth() - WIDTH;
        }

        xVelocity *= 0.9;
        yVelocity *= 0.9;
    }

    void draw() {
        TextureRegion img;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            img = left;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            img = up;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            img = right;
        } else {
            img = down;
        }

        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img, x, y, WIDTH, HEIGHT);
        batch.end();
    }
}