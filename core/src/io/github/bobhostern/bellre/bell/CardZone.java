package io.github.bobhostern.bellre.bell;

/**
 * Stores a certain, limited number of cards
 * Useful for places like the monster card zone, the field card zone, etc.
 *
 * @author Bobho_000
 */
public class CardZone {
    public enum SlotState {
        OCCUPIED,
        EMPTY
    }

    final GameCard[] cards;

    public CardZone(int size) {
        cards = new GameCard[size];
    }

    public int size() {
        return cards.length;
    }

    public SlotState getState(int slot) {
        if (cards[slot] != null) {
            return SlotState.OCCUPIED;
        } else {
            return SlotState.EMPTY;
        }
    }

    public void placeCard(int slot, GameCard card) {
        if (slot < cards.length)
            cards[slot] = card;
    }

    public GameCard getCard(int slot) {
        if (slot < cards.length)
            return cards[slot];
        else
            return null;
    }

    /**
     * Returns the next open slot, or -1 if it fails
     *
     * @return index of the next of slot or -1 if it failed to find any
     */
    public int nextEmptySlot() {
        for (int i = 0; i < cards.length; i++) {
            if (getState(i) == SlotState.EMPTY)
                return i;
        }
        return -1;
    }

    /**
     * Finds a card in this zone
     *
     * @param card Card to look for
     * @return Slot index if found, -1 if not.
     */
    public int find(GameCard card) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == card)
                return i;
        }
        return -1;
    }
}
