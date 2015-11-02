package io.github.bobhostern.bellre.bell.effect;

/**
 * Created by Bobhostern on 10/16/2015.
 * <p/>
 * An Effect is an occurance in the game.
 * <p/>
 * BTW, Effect as a class is geared toward easily making atomic (non-prepositional) effects.
 */
public abstract class Effect extends Negatable {
    private boolean chainable = true;

    // TODO: Remember to attach a GameCard as the causation of a ChainLink

    // Tells of all types in this effect (including "subeffects")
    // Used to resolve if effects
    public abstract EffectType getEffectType();

    // Determines chainability (can we chain to this effect?)
    public boolean isChainable() {
        return chainable;
    }

    public Effect chainability(boolean b) {
        chainable = b;
        return this;
    }

    // Allows you to do stuff before and after the Effect (such as passing a target onto the code that needs it)
    public void preEffect() {
    }

    public void postEffect() {
    }
}
