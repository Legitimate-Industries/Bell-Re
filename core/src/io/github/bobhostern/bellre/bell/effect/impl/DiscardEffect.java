package io.github.bobhostern.bellre.bell.effect.impl;

import io.github.bobhostern.bellre.bell.effect.Effect;
import io.github.bobhostern.bellre.bell.effect.EffectType;

/**
 * Created by Bobhostern on 10/16/2015.
 */
public class DiscardEffect extends Effect {
    private final int discardTotal;

    public DiscardEffect(int amount) {
        discardTotal = amount;
    }

    public int getAmount() {
        return discardTotal;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.DISCARD;
    }
}
