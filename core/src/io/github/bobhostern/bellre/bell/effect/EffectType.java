package io.github.bobhostern.bellre.bell.effect;

/**
 * Created by Bobhostern on 10/16/2015.
 * <p/>
 * All the types of effects that the engine can process.
 */
public enum EffectType {
    BUILT, // Used to represent if this class is a result of BuilderEffect

    DRAW,
    DISCARD,
    SUMMON, // All summon types
    NEGATE, // Impl. detail: Only check the chain before the target, as a negation must follow the effect it's negating
}
