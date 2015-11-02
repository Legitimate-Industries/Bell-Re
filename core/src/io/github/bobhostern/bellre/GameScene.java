package io.github.bobhostern.bellre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.bobhostern.bellre.bell.*;
import io.github.bobhostern.bellre.bell.effect.Effect;
import io.github.bobhostern.bellre.bell.effect.EffectType;
import io.github.bobhostern.bellre.bell.effect.impl.BuilderEffect;
import io.github.bobhostern.bellre.bell.effect.impl.DrawEffect;
import io.github.bobhostern.bellre.bell.local.DeckLoader;
import io.github.bobhostern.bellre.bell.local.LocalCardLibrary;
import io.github.bobhostern.bellre.bell.local.LocalPlayerField;
import io.github.bobhostern.bellre.bell.local.PixmapLoader;
import io.github.bobhostern.bellre.gui.PlayingField;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * Holds all inputs and the game itself
 *
 * @author Bobho_000
 */
public class GameScene implements Screen {
    final BellGame game;
    private final PlayerField p1_field;
    private final PlayerField p2_field;
    private final CardLibrary cardLibrary;
    private final PixmapLoader pixmapLoader;

    private final Stage stage;
    private final Table table;

    private final PlayingField playfield; // Used to display the field and it's cards
    private final Skin skin;

    public GameScene(BellGame gam) {
        game = gam;
        p1_field = new LocalPlayerField(PlayerTeam.RED);
        p2_field = new LocalPlayerField(PlayerTeam.GREEN);
        pixmapLoader = new PixmapLoader();

        cardLibrary = new LocalCardLibrary(pixmapLoader); // This would be loaded by an intermediate screen.
        cardLibrary.loadCards();

        int lastCount = 0;
        while (cardLibrary.getTotal() != cardLibrary.getCurrent()) {
            if (cardLibrary.getCurrent() != lastCount) {
                System.out.println(cardLibrary.getCurrent() + "/" + cardLibrary.getTotal());
                lastCount = cardLibrary.getCurrent();
            }
        }

        // Temporary setup shtuff here...
        DeckLoader deckLoader = new DeckLoader();
        deckLoader.loadRecipes(cardLibrary);

        for (GameCard card : deckLoader.getDeck("aleph_series")) {
            p1_field.addCardToField(card.clone(), new FieldLocationBuilder().setGen(FieldLocation.General.DECK).createFieldLocation());
            p2_field.addCardToField(card.clone(), new FieldLocationBuilder().setGen(FieldLocation.General.DECK).createFieldLocation());
        }

        System.out.println(p1_field);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true);
        table.setClip(true);

        skin = new Skin();

        // Load all field images to the Skin.
        if (Gdx.files.external(".bell/cards/pics/field/").isDirectory()) {
            FileHandle fd = Gdx.files.external(".bell/cards/pics/field");
            FileHandle[] files = fd.list(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    String basename = FilenameUtils.getBaseName(file.getName());
                    String extension = FilenameUtils.getExtension(file.getName());
                    String[] accext = {
                            "png", "jpg", "bmp"
                    };
                    Arrays.sort(accext);
                    if (basename != "default" && !basename.startsWith("~") && (Arrays.binarySearch(accext, extension) != -1)) {
                        return true;
                    }
                    return false;
                }
            });
            for (FileHandle hand : files) {
                if (!skin.has(hand.nameWithoutExtension(), Texture.class))
                    skin.add(hand.nameWithoutExtension(), new Texture(hand), Texture.class);
            }
        }
        skin.add("default", new Texture("dbg.png"), Texture.class);

        playfield = new PlayingField(skin);
//        table.add(playfield).width(400).height(200);
        stage.addActor(playfield);

        BuilderEffect<DrawEffect> b = new BuilderEffect<DrawEffect>(DrawEffect.class);
        System.out.println(b.getEffectType());
        b.build(new Class<?>[]{int.class}, 1);
        System.out.println(b.getEffectType());

        Effect e = b;
        if (e.getEffectType() == EffectType.BUILT) {
            e = ((BuilderEffect) e).getInstance();
        }
        System.out.println(e.getClass().getName());
    }

    @Override
    public void show() {
        // TODO Setup stuff...
    }

    @Override
    public void render(float delta) {
        // TODO Update stuff
        // TODO Drawing stuff...
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*game.batch.begin();
        game.batch.draw(game.img, 0, 0);
		game.batch.end();
        ShapeRenderer renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int row = 0; row < 4; row++) {
            for (int count = 0; count < 5; count++) {
                renderer.box(count * 42 + count * 10, row*61+row*20, 0, 42, 61, 0);
            }
        }
        renderer.end();*/
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
//		Gdx.graphics.requestRendering();
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Tear down stuff...

    }

    @Override
    public void dispose() {
        pixmapLoader.dispose();
        cardLibrary.dispose();
        stage.dispose();
        skin.dispose();
    }
}