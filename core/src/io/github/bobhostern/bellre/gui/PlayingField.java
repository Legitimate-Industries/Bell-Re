package io.github.bobhostern.bellre.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Bobho_000 on 10/6/2015.
 * Displays the cards on the field.
 */
public class PlayingField extends Group {
    private static final Vector2 card_size = new Vector2(42, 61);

    private final String fieldname;
    private final Skin skin;
    private String bgname = "default";

    public PlayingField(Skin _skin) {
        skin = _skin;
        Preferences fieldpref = Gdx.app.getPreferences("bellre-field");

        fieldname = fieldpref.getString("fieldfile", "default");
        System.out.println("Field file: " + fieldname + ".yml");

        setX(110);
        setY(6);
        setWidth(482);
        setHeight(467);
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        // Draw field background
        if (skin.has(bgname, Texture.class)) {
            TextureRegion d = skin.getRegion(bgname);
            batch.draw(d, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }
}
