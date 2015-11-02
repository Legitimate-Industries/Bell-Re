package io.github.bobhostern.bellre.bell.effect;

import io.github.bobhostern.bellre.bell.effect.impl.BinaryPreposition;

/**
 * Created by Bobhostern on 10/17/2015.
 * <p/>
 * Represents a preposition.
 * Used in effect resolution and creation
 */
public abstract class Preposition extends Negatable {
    public abstract PrepositionType getType();

    public abstract Preposition getA();

    public abstract Preposition getB();

    // Helper preposition methods...
    public Preposition then(Preposition b) {
        return new BinaryPreposition(PrepositionType.THEN, this, b);
    }

    public Preposition and(Preposition b) {
        return new BinaryPreposition(PrepositionType.AND, this, b);
    }

    public Preposition also(Preposition b) {
        return new BinaryPreposition(PrepositionType.ALSO, this, b);
    }
}
