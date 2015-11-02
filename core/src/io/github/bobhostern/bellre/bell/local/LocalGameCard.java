package io.github.bobhostern.bellre.bell.local;

import com.badlogic.gdx.graphics.Pixmap;
import io.github.bobhostern.bellre.bell.*;

import java.util.EnumSet;

public class LocalGameCard implements GameCard {
    private final String name;
    private final CustomValue<Integer> attack;
    private final CustomValue<Integer> defence;
    private final CustomValue<Integer> level;
    private final CustomValue<Integer> rank;
    private CardPosition position;
    private final CardType ctype;
    private final EnumSet<CardSpecifier> cspec;
    private final String description;
    private final CardScript script;
    private final Pixmap pixmap;
    private final PlayerTeam owner;

    public LocalGameCard(CardType t, String n, CustomValue<Integer> l, CustomValue<Integer> r,
                         CustomValue<Integer> a, CustomValue<Integer> d, CardPosition b, String e, Pixmap p, CardScript s) {
        this(t, EnumSet.of(CardSpecifier.NORMAL), n, l, r, a, d, b, e, p, null, s);
    }

    public LocalGameCard(CardType t, EnumSet<CardSpecifier> s, String n, CustomValue<Integer> l, CustomValue<Integer> r,
                         CustomValue<Integer> a, CustomValue<Integer> d, CardPosition b, String e, Pixmap p, CardScript c) {
        this(t, s, n, l, r, a, d, b, e, p, null, c);
    }

    public LocalGameCard(CardType t, EnumSet<CardSpecifier> s, String n, CustomValue<Integer> l, CustomValue<Integer> r,
                         CustomValue<Integer> a, CustomValue<Integer> d, CardPosition b, String e, Pixmap p, PlayerTeam o, CardScript c) {
        name = n;
        attack = a;
        defence = d;
        position = b;
        ctype = t;
        cspec = s;
        level = l;
        rank = r;
        description = e;
        script = c;
        pixmap = p;
        owner = o;
    }

    /**
     * I can lie. This does a deep copy of the GameCard.
     *
     * @return a **deep** copy of this card
     */
    @Override
    public LocalGameCard clone() {
        return new LocalGameCard(
                ctype,
                cspec.clone(),
                name,
                level.clone(),
                rank.clone(),
                attack.clone(),
                defence.clone(),
                position.clone(),
                description,
                pixmap,
                owner,
                script);
    }

    @Override
    public GameCard name(String n) {
        return new LocalGameCard(
                ctype,
                cspec.clone(),
                n,
                level.clone(),
                rank.clone(),
                attack.clone(),
                defence.clone(),
                position.clone(),
                description,
                pixmap,
                owner,
                script);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GameCard attack(CustomValue<Integer> i) {
        return new LocalGameCard(
                ctype,
                cspec.clone(),
                name,
                level.clone(),
                rank.clone(),
                i,
                defence.clone(),
                position.clone(),
                description,
                pixmap,
                owner,
                script);
    }

    @Override
    public CustomValue<Integer> getAttack() {
        return attack;
    }

    @Override
    public GameCard defense(CustomValue<Integer> i) {
        return new LocalGameCard(
                ctype,
                cspec.clone(),
                name,
                level.clone(),
                rank.clone(),
                attack.clone(),
                i,
                position.clone(),
                description,
                pixmap,
                owner,
                script);
    }

    @Override
    public CustomValue<Integer> getDefense() {
        return defence;
    }

    @Override
    public CardPosition position() {
        return position;
    }

    @Override
    public void setPosition(CardPosition cp) {
        position = cp;
    }

    @Override
    public CardScript getScript() {
        return script;
    }

    @Override
    public Pixmap getPixmap() {
        return pixmap;
    }

    @Override
    public GameCard type(CardType type) {
        return new LocalGameCard(
                type,
                cspec.clone(),
                name,
                level.clone(),
                rank.clone(),
                attack.clone(),
                defence.clone(),
                position.clone(),
                description,
                pixmap,
                owner,
                script);
    }

    @Override
    public CardType getType() {
        return ctype;
    }

    @Override
    public GameCard specifics(EnumSet<CardSpecifier> s) {
        return new LocalGameCard(
                ctype,
                s,
                name,
                level.clone(),
                rank.clone(),
                attack.clone(),
                defence.clone(),
                position.clone(),
                description,
                pixmap,
                owner,
                script);
    }

    @Override
    public EnumSet<CardSpecifier> getSpecifics() {
        return cspec;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(name);
        str.append("[").append(ctype).append("]");
        if (ctype == CardType.MONSTER) {
            if (cspec.contains(CardSpecifier.XYZ))
                str.append("[[R").append(rank.getValue()).append("]]");
            else
                str.append("[[L").append(level.getValue()).append("]]");
            str.append("<").append(attack.getValue()).append("/").append(defence.getValue()).append(">");
        }
        str.append("(").append(cspec).append(")");
        str.append('"').append(description).append('"');
        str.append(" <<").append(script).append(">>");
        return str.toString();
    }

    @Override
    public GameCard level(CustomValue<Integer> i) {
        return new LocalGameCard(
                ctype,
                cspec.clone(),
                name,
                i,
                rank.clone(),
                attack.clone(),
                defence.clone(),
                position.clone(),
                description,
                pixmap,
                owner,
                script);
    }

    @Override
    public CustomValue<Integer> getLevel() {
        return level;
    }

    @Override
    public GameCard rank(CustomValue<Integer> i) {
        return new LocalGameCard(
                ctype,
                cspec.clone(),
                name,
                level.clone(),
                i,
                attack.clone(),
                defence.clone(),
                position.clone(),
                description,
                pixmap,
                owner,
                script);
    }

    @Override
    public CustomValue<Integer> getRank() {
        return rank;
    }

    @Override
    public GameCard desc(String i) {
        return new LocalGameCard(
                ctype,
                cspec.clone(),
                name,
                level.clone(),
                rank.clone(),
                attack.clone(),
                defence.clone(),
                position.clone(),
                i,
                pixmap,
                owner,
                script);
    }

    @Override
    public String getDesc() {
        return description;
    }

    @Override
    public GameCard owner(PlayerTeam team) {
        return new LocalGameCard(
                ctype,
                cspec.clone(),
                name,
                level.clone(),
                rank.clone(),
                attack.clone(),
                defence.clone(),
                position.clone(),
                description,
                pixmap,
                team,
                script);
    }

    @Override
    public PlayerTeam getOwner() {
        return owner;
    }

    @Override
    public void dispose() {
        pixmap.dispose();
    }
}
