package io.github.bobhostern.bellre.bell.effect;

/**
 * Created by Bobhostern on 10/17/2015.
 */
public class Negatable {
    private boolean negated = false;

    public boolean isNegated() {
        return negated;
    }

    public void negate() {
        negated = true;
    }
}
