package io.github.bobhostern.bellre.bell.effect.impl;

import io.github.bobhostern.bellre.bell.effect.Preposition;
import io.github.bobhostern.bellre.bell.effect.PrepositionType;

/**
 * Created by Bobhostern on 10/17/2015.
 * <p/>
 * Helps implement binary prepositions: then, and, and also, also, etc.
 */
public class BinaryPreposition extends Preposition {
    private final Preposition a;
    private final Preposition b;
    private final PrepositionType type;

    public BinaryPreposition(PrepositionType t, Preposition _a, Preposition _b) {
        type = t;
        a = _a;
        b = _b;
    }

    @Override
    public PrepositionType getType() {
        return type;
    }

    @Override
    public Preposition getA() {
        return a;
    }

    @Override
    public Preposition getB() {
        return b;
    }
}
