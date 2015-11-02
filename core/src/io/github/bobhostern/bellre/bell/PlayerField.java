package io.github.bobhostern.bellre.bell;

/**
 * Represents one side of the field (literally the player field)
 * TODO Find out a good way to support two players on the same player field. (for tag team)
 *
 * @author Bobho_000
 */
public interface PlayerField {
    CardZone getMonsters();

    CardZone getSpellTrap();

    CardZone getField();

    CardQueue getDeck();

    CardQueue getExtraDeck();

    CardQueue getGraveyard();

    CardQueue getBanished();

    CardQueue getHand();

    PlayerTeam getTeam();

    /**
     * Adds a card to the field, placing it at the specified location.
     * To forcefully place it, use placeCardOnField
     *
     * @param card Card to add to field (deck)
     * @param loc  Location to add card at (Set specific to 0 for the field to choose a place in that location.
     * @return true - was successful at adding card to specified location
     * false - failed to add card to specified location
     */
    boolean addCardToField(GameCard card, FieldLocation loc);

    /**
     * Add a card forcefully to the field.
     * Location:
     * MONSTER, SPELL_TRAP, FIELD_CARD - Must set the specific (should not be <0)
     * DECK, EXTRA_DECK, HAND, GRAVEYARD, BANISHED, LIMBO - Specific is ignored
     *
     * @param card
     * @param loc
     */
    void placeCardOnField(GameCard card, FieldLocation loc);

    /**
     * Find where a specific GameCard instance is located.
     * !WARNING! May be *incredibly* slow. Use findCardGeneral(GameCard, FieldLocation.General) for a bit faster search.
     *
     * @param card Card instance to search for
     * @return the location of that card instance
     */
    FieldLocation findCard(GameCard card);

    FieldLocation findCard(GameCard card, FieldLocation.General gen);
}
