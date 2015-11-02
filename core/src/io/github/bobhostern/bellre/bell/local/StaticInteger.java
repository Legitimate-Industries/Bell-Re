package io.github.bobhostern.bellre.bell.local;

import io.github.bobhostern.bellre.bell.CustomValue;

/**
 * Stores an integer, and returns it
 *
 * @author Bobho_000
 */
public class StaticInteger implements CustomValue<Integer> {
    final int value;

    public StaticInteger(int i) {
        value = i;
    }

    @Override
    public Integer getValue() {
        // TODO Auto-generated method stub
        return value;
    }

    @Override
    public StaticInteger clone() {
        return new StaticInteger(value);
    }
}
