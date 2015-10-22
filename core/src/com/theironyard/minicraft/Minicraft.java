package com.theironyard.minicraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Minicraft extends ApplicationAdapter {
    final int WIDTH = 100;
    final int HEIGHT = 100;

    SpriteBatch batch;
    TextureRegion down, up, right, left;

    float x = 0;
    float y = 0;

    @Override
    public void create () {
        batch = new SpriteBatch();

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

        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(down, x, y, WIDTH, HEIGHT);
        batch.end();
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

        xVelocity *= 0.9;
        yVelocity *= 0.9;
    }
}
