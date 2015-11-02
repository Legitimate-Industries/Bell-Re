package io.github.bobhostern.bellre.bell.local;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import io.github.bobhostern.bellre.bell.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class LocalCardLibrary implements CardLibrary {
    private Map<String, GameCard> cards = null;
    private final String baseLocation;
    private final PixmapLoader pixmapLoader;

    private Integer cardTotal = 0;
    private Integer current = 0;

    private static final int CARD_FORMAT_MAJOR = 0;
    private static final int CARD_FORMAT_MINOR = 0;
    private static final int CARD_FORMAT_PATCH = 1;

    public LocalCardLibrary(PixmapLoader loader) {
        this(".bell/", loader);
    }

    public LocalCardLibrary(String s, PixmapLoader loader) {
        baseLocation = s;
        pixmapLoader = loader;
    }

    private GameCard loadCard(Map cmap) {
        String card_format = (String) cmap.get("format");
        if (card_format != null) {
            String[] pformat = card_format.split("\\.", 3);
            System.out.println("Card format: " + card_format);
            // We use semver, so these descriptions were adapted from semver.org to inform users of format differences
            if (Integer.parseUnsignedInt(pformat[0]) != CARD_FORMAT_MAJOR)
                Gdx.app.log("LocalCardLibrary",
                        "There is a backwards-incompatible change between this card and the current SAPI. " +
                                "Most likely, the card will not load.");
            else if (Integer.parseUnsignedInt(pformat[1]) != CARD_FORMAT_MINOR)
                Gdx.app.log("LocalCardLibrary",
                        "There is an added backwards-compatible feature between this card and the current SAPI. " +
                                "This card will most likely load, but it may be subject to missing features.");
            else if (Integer.parseUnsignedInt(pformat[2]) != CARD_FORMAT_PATCH)
                Gdx.app.log("LocalCardLibrary",
                        "There is a backwards-compatible bugfix between this card and the current SAPI. " +
                                "This card will load, but it may be vulnerable to odd or incorrect behavior.");
        } else {
            Gdx.app.log("LocalCardLibrary",
                    "This card failed to declare a format. It may be vulnerable to odd or incorrect behavior, " +
                            "missing features, or it may not load at all.");
        }
        Map map = (Map) cmap.get("card_data");
        CardType type = (CardType) map.get("card_type");
        EnumSet<CardSpecifier> specs = EnumSet.copyOf((ArrayList) map.get("specifiers"));
        String name = (String) map.get("name");
        CustomValue<Integer> level = new StaticInteger((Integer) map.get("level"));
        CustomValue<Integer> rank = new StaticInteger((Integer) map.get("rank"));
        CustomValue<Integer> attack = new StaticInteger((Integer) map.get("attack"));
        CustomValue<Integer> defense = new StaticInteger((Integer) map.get("defense"));
        CardPosition cpos = new CardPosition(CardPosition.Facing.FACE_DOWN, CardPosition.BattlePosition.ATTACK_POS);
        String lore = (String) map.get("lore");

        CardScript script = null;
        String scriptf = (String) map.get("script");
        try {
            Binding binding = new Binding();
            GroovyScriptEngine engine = new GroovyScriptEngine(new URL[]{Gdx.files.external(baseLocation + "/cards/script/").file().toURI().toURL()});

            Object so = engine.run(scriptf, binding);
            if (so instanceof CardScript)
                script = (CardScript) so;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        String picLocation = baseLocation + "/cards/pics/" + (String) map.get("image");
        Pixmap pm = pixmapLoader.loadPixmap(Gdx.files.external(picLocation));

        return new LocalGameCard(type, specs, name, level, rank, attack, defense, cpos, lore, pm, script);
    }

    @Override
    public void loadCards() {
        Gdx.app.log("CardLoader", "Format Version " + String.join(".", "" + CARD_FORMAT_MAJOR, "" + CARD_FORMAT_MINOR, "" + CARD_FORMAT_PATCH));
        cards = new HashMap<String, GameCard>();
        YamlReader cardList = new YamlReader(Gdx.files.external(baseLocation + "/cardList.yml").reader());
        try {
            Map map = (Map) cardList.read();
            FileHandle cardPath = Gdx.files.external(baseLocation + "/" + map.get("card_directory") + "/");
            if (!cardPath.isDirectory()) {
                throw new RuntimeException("card_directory must point to a valid subdirectory of " + baseLocation);
            }

            final FileHandle[] clist = cardPath.list("yml");
            cardTotal = clist.length;
            Thread loading = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (FileHandle handel : clist) {
                        YamlConfig config = new YamlConfig();
                        config.setClassTag("CardType", CardType.class);
                        config.setClassTag("CardSpec", CardSpecifier.class);

                        Gdx.app.log("LocalCardLibrary", "Loading " + handel.nameWithoutExtension());
                        YamlReader card = new YamlReader(handel.reader(), config);
                        Map nmap = null;
                        try {
                            nmap = (Map) card.read();
                            GameCard gcard = loadCard(nmap);
                            synchronized (cards) {
                                cards.put((String) nmap.get("cid"), gcard);
                            }
                        } catch (YamlException e) {
                            e.printStackTrace();
                        }
                        synchronized (current) {
                            current += 1;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
//            loading.run();
            loading.start();
        } catch (YamlException e) {
            e.printStackTrace();
        }
    }

    public Map<String, GameCard> getCards() {
        synchronized (cards) {
            return cards;
        }
    }

    @Override
    public void dispose() {
        for (GameCard card : cards.values()) {
            card.dispose();
        }
    }

    public int getTotal() {
        return cardTotal;
    }

    public int getCurrent() {
        synchronized (current) {
            return current;
        }
    }
}
