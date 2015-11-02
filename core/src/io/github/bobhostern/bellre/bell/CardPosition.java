package io.github.bobhostern.bellre.bell;

public class CardPosition implements Cloneable {
    public enum Facing {
        FACE_UP,
        FACE_DOWN
    }

    public enum BattlePosition {
        ATTACK_POS,
        DEFENCE_POS
    }

    public CardPosition(Facing f, BattlePosition b) {
        face = f;
        battle = b;
    }

    private Facing face;
    private BattlePosition battle;

    public Facing getFace() {
        return face;
    }

    public BattlePosition getBattle() {
        return battle;
    }

    public void setFace(Facing face) {
        this.face = face;
    }

    public void setBattle(BattlePosition battle) {
        this.battle = battle;
    }

    @Override
    public CardPosition clone() {
        return new CardPosition(face, battle);
    }
}
