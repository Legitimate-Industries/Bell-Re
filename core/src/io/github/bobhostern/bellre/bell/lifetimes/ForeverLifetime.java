package io.github.bobhostern.bellre.bell.lifetimes;

import io.github.bobhostern.bellre.bell.FieldStats;
import io.github.bobhostern.bellre.bell.PlayerField;
import io.github.bobhostern.bellre.bell.condition.Lifetime;

/**
 * Created by Bobhostern on 10/17/2015.
 */
public class ForeverLifetime implements Lifetime {
    @Override
    public boolean isExpired(PlayerField controller, PlayerField opponent, FieldStats stats) {
        return false;
    }
}
