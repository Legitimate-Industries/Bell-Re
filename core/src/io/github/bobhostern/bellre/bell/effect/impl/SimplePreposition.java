package io.github.bobhostern.bellre.bell.effect.impl;

import io.github.bobhostern.bellre.bell.effect.Effect;
import io.github.bobhostern.bellre.bell.effect.Preposition;
import io.github.bobhostern.bellre.bell.effect.PrepositionType;

/**
 * Created by Bobhostern on 10/17/2015.
 */
public class SimplePreposition extends Preposition {
    private final Effect effect;

    public SimplePreposition(Effect e) {
        effect = e;
    }

    @Override
    public PrepositionType getType() {
        return PrepositionType.NAP;
    }

    public Effect getEffect() {
        return effect;
    }

    @Override
    public Preposition getA() {
        return null;
    }

    @Override
    public Preposition getB() {
        return null;
    }
}
