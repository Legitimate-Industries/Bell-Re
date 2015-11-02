package io.github.bobhostern.bellre;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class BellGame extends Game {
    SpriteBatch batch;
    Texture img;

    private static String randomSaying() throws IOException {
        Random rand = new Random();
        FileHandle file = Gdx.files.internal("sayings.txt");
        List<String> sayings = FileUtils.readLines(file.file());
        return (sayings.size() > 0 ? sayings.get(rand.nextInt(sayings.size())) : "No wit to-day");
    }

    @Override
    public void create() {
//		Gdx.graphics.setContinuousRendering(false);
//		Gdx.graphics.requestRendering();

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        try {
            String s = randomSaying();
            Gdx.graphics.setTitle("Bell:Re - " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setScreen(new GameScene(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
