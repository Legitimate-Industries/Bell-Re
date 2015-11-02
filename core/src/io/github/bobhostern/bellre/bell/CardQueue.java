package io.github.bobhostern.bellre.bell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Stores cards in FIFO order, and doesn't limit the amount
 * Useful for the hand, all decks, the graveyard, etc.
 *
 * @author Bobho_000l
 */
public class CardQueue {
    final List<GameCard> stack;

    public CardQueue() {
        stack = new ArrayList<GameCard>();
    }

    public void pushCard(GameCard c) {
        stack.add(c);
    }

    public void shuffle() {
        List<GameCard> temp = new ArrayList<GameCard>();
        Random rand = new Random();
        while (stack.size() > 0) {
            temp.add(stack.remove(rand.nextInt(stack.size())));
        }
        stack.addAll(temp);
    }

    /**
     * Gets the card at a certain location
     *
     * @param idx The index to search
     * @return the card at that index, or null if index is too large
     */
    public GameCard getCard(int idx) {
        if (idx < stack.size())
            return stack.get(idx);
        return null;
    }

    public GameCard takeCard(int idx) {
        if (idx < stack.size())
            return stack.remove(idx);
        return null;
    }

    public void placeCard(int idx, GameCard card) {
        if (idx >= stack.size())
            stack.add(card);
        else
            stack.add(idx, card);
    }

    /**
     * Takes the top card, and removes it from the queue
     *
     * @return the top card in the queue
     */
    public GameCard popCard() {
        return takeCard(0);
    }

    public int size() {
        return stack.size();
    }

    public GameCard[] getStack() {
        return (GameCard[]) stack.toArray();
    }

    /**
     * Finds a card in this zone
     *
     * @param card Card to look for
     * @return Slot index if found, -1 if not.
     */
    public int find(GameCard card) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) == card)
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
