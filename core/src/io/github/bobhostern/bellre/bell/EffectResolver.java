package io.github.bobhostern.bellre.bell;

import io.github.bobhostern.bellre.bell.effect.Effect;
import io.github.bobhostern.bellre.bell.effect.EffectChain;

/**
 * Created by Bobhostern on 10/19/2015.
 * <p/>
 * This can resolve chains or individual effects.
 */
public interface EffectResolver {
    void resolveEffect(Effect e, PlayerField red, PlayerField green, FieldStats stats);

    void resolveChain(EffectChain e, PlayerField red, PlayerField green, FieldStats stats);
}
