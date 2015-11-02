package io.github.bobhostern.bellre.bell;

/**
 * Created by Bobho_000 on 10/5/2015.
 */
public class FieldLocation {
    public enum General {
        MONSTER,
        SPELL_TRAP,
        FIELD_CARD,
        GRAVEYARD,
        BANISHED,
        DECK,
        EXTRA_DECK,
        HAND,

        LIMBO,
    }

    General generic_location;
    int specific_index;

    public FieldLocation() {
        this(General.LIMBO, 0);
    }

    public FieldLocation(General gen, int spec) {
        generic_location = gen;
        specific_index = spec;
    }

    public General get_generic() {
        return generic_location;
    }

    public int get_specific() {
        return specific_index;
    }
}
