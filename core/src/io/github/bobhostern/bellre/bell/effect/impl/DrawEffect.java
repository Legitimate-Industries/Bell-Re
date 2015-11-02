package io.github.bobhostern.bellre.bell.effect.impl;

import io.github.bobhostern.bellre.bell.effect.Effect;
import io.github.bobhostern.bellre.bell.effect.EffectType;

/**
 * Created by Bobhostern on 10/16/2015.
 */
public class DrawEffect extends Effect {
    private final int drawCount;

    public DrawEffect(int amt) {
        drawCount = amt;
    }

    public int amount() {
        return drawCount;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.DRAW;
    }
}
