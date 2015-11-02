package io.github.bobhostern.bellre.bell.local;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import io.github.bobhostern.bellre.bell.CardLibrary;
import io.github.bobhostern.bellre.bell.GameCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bobho_000 on 10/5/2015.
 * Opens files on the local computer to setup, and read deck files.
 */
public class DeckLoader {
    final String base;
    Map<String, List<GameCard>> decks = new HashMap<String, List<GameCard>>();

    public DeckLoader() {
        this(".bell/");
    }

    public DeckLoader(String s) {
        base = s;
    }

    private List<GameCard> createDeck(FileHandle fl, CardLibrary cl) {
        List<GameCard> cards = new ArrayList<GameCard>();
        YamlReader reader = new YamlReader(fl.reader());

        try {
            Map deck = (Map) reader.read();
            for (String cardn : (List<String>) deck.get("cards")) {
                GameCard card = cl.getCards().get(cardn);
                if (card != null)
                    cards.add(card.clone());
                else
                    Gdx.app.log("DeckLoader", "Couldn't find card with cid " + cardn);
            }
        } catch (YamlException e) {
            e.printStackTrace();
        }

        // TODO Do banlist checking here...
        return cards;
    }

    public void loadRecipes(CardLibrary library) {
        FileHandle deckloc = Gdx.files.external(base + "/decks/");
        if (!deckloc.isDirectory())
            throw new RuntimeException("decks subdirectory must exist in " + base);
        for (FileHandle fdeckrecipe : deckloc.list("yml")) {
            String name = fdeckrecipe.nameWithoutExtension();
            decks.put(name, createDeck(fdeckrecipe, library));
        }
    }

    private <T> List<T> createCopy(List<T> o) {
        List<T> temp = new ArrayList<T>();
        temp.addAll(o);
        return temp;
    }

    public List<GameCard> getDeck(String name) {
        return decks.get(name);
    }
}
