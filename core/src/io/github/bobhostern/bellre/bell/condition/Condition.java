package io.github.bobhostern.bellre.bell.condition;

import io.github.bobhostern.bellre.bell.*;

/**
 * Created by Bobhostern on 10/17/2015.
 * <p/>
 * This simply is an effect in play. Continuous effects and rules will use this to be able to add effects to the
 * effect resolver, WITHOUT going through the chaining mechanism.
 */
public abstract class Condition {
    private final Lifetime lifetime;
    private final PlayerTeam activator;
    private final GameCard assoc; // For Continuous-effect, so we know the source. Rules simply have null.

    // Nice bit of code:
    // PlayerField player = (red.getTeam() == getActivator() ? red : green);
    // PlayerField opponent = (red.getTeam() != getActivator() ? red : green);

    public abstract boolean isActivatable(PlayerField red, PlayerField green, FieldStats s);

    public abstract void activate(EffectResolver e, PlayerField red, PlayerField green, FieldStats stats); // TODO add effect resolver argument...

    public Condition(PlayerTeam t, Lifetime l) {
        this(t, null, l);
    }

    public Condition(PlayerTeam t, GameCard c, Lifetime l) {
        lifetime = l;
        activator = t;
        assoc = c;
    }

    public boolean isExpired(PlayerField p, PlayerField o, FieldStats s) {
        return lifetime.isExpired(p, o, s);
    }

    public PlayerTeam getActivator() {
        return activator;
    }

    public GameCard getAssociatedCard() {
        return assoc;
    }
}
