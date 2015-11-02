package io.github.bobhostern.bellre.bell.condition;

import io.github.bobhostern.bellre.bell.FieldStats;
import io.github.bobhostern.bellre.bell.PlayerField;

/**
 * Created by Bobhostern on 10/17/2015.
 */
public interface Lifetime {
    boolean isExpired(PlayerField controller, PlayerField opponent, FieldStats stats); // TODO add field params...
}
