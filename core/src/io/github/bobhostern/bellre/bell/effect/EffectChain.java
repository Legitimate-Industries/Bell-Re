package io.github.bobhostern.bellre.bell.effect;

import io.github.bobhostern.bellre.bell.GameCard;
import io.github.bobhostern.bellre.bell.PlayerTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bobhostern on 10/16/2015.
 * Represents an effect chain, and does chain resolution.
 */
public class EffectChain {
    public class EffectChainLink {
        private final GameCard cause;
        private final Preposition effect; // Represents all the atomic effects (Effect) of a card's effect
        private final PlayerTeam activator; // This is the player that activated the effect.

        EffectChainLink(PlayerTeam t, GameCard c, Preposition e) {
            cause = c;
            effect = e;
            activator = t;
        }
    }

    private List<EffectChainLink> links = new ArrayList<EffectChainLink>();

    // TODO add actual chain creation and chain resolution methods...
}
