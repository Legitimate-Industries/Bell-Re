package io.github.bobhostern.bellre.bell;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Disposable;

import java.util.EnumSet;

public interface GameCard extends Cloneable, Disposable {
    // All of this is "original" information.
    // ERGO, the most impossible type of card right now is a card that changes original attack externally...
    // 	I.E. A spell that says "Target 1 card; the targets original ATK is now 0."
    GameCard name(String n);

    String getName();

    GameCard desc(String i);

    String getDesc();

    GameCard attack(CustomValue<Integer> i);

    CustomValue<Integer> getAttack();

    GameCard defense(CustomValue<Integer> i);

    CustomValue<Integer> getDefense();

    GameCard level(CustomValue<Integer> i);

    CustomValue<Integer> getLevel();

    GameCard rank(CustomValue<Integer> i);

    CustomValue<Integer> getRank();

    GameCard type(CardType type);

    CardType getType();

    GameCard specifics(EnumSet<CardSpecifier> s);

    EnumSet<CardSpecifier> getSpecifics();

    CardPosition position();

    void setPosition(CardPosition cp);

    CardScript getScript(); // Used for various things, but mainly for effects!

    Pixmap getPixmap();

    GameCard clone();

    GameCard owner(PlayerTeam team);

    PlayerTeam getOwner();
}
