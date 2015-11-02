package io.github.bobhostern.bellre.bell.local;

import io.github.bobhostern.bellre.bell.*;

import static io.github.bobhostern.bellre.bell.FieldLocation.General;

public class LocalPlayerField implements PlayerField {
    final CardZone monsters;
    final CardZone spelltrap;
    final CardZone fieldcard;
    final CardQueue deck;
    final CardQueue extradeck;
    final CardQueue graveyard;
    final CardQueue banished;
    final CardQueue hand;
    final PlayerTeam team;

    public LocalPlayerField(PlayerTeam t) {
        monsters = new CardZone(5);
        spelltrap = new CardZone(5);
        fieldcard = new CardZone(1);

        deck = new CardQueue();
        extradeck = new CardQueue();
        graveyard = new CardQueue();
        banished = new CardQueue();
        hand = new CardQueue();

        team = t;
        // Populate deck...
        // Populate extradeck...
    }

    @Override
    public CardZone getMonsters() {
        return monsters;
    }

    @Override
    public CardZone getSpellTrap() {
        return spelltrap;
    }

    @Override
    public CardZone getField() {
        return fieldcard;
    }

    @Override
    public CardQueue getDeck() {
        return deck;
    }

    @Override
    public CardQueue getExtraDeck() {
        return extradeck;
    }

    @Override
    public CardQueue getGraveyard() {
        return graveyard;
    }

    @Override
    public CardQueue getBanished() {
        return banished;
    }

    @Override
    public CardQueue getHand() {
        return hand;
    }

    @Override
    public PlayerTeam getTeam() {
        return team;
    }

    @Override
    public boolean addCardToField(GameCard card, FieldLocation loc) {
        switch (loc.get_generic()) {
            case MONSTER:
                if (loc.get_specific() == -1) {
                    // Find next empty slot...
                    int open_slot = monsters.nextEmptySlot();
                    if (open_slot == -1)
                        return false;
                    monsters.placeCard(open_slot, card);
                } else {
                    monsters.placeCard(loc.get_specific(), card);
                }
                break;
            case SPELL_TRAP:
                if (loc.get_specific() == -1) {
                    // Find next empty slot...
                    int open_slot = spelltrap.nextEmptySlot();
                    if (open_slot == -1)
                        return false;
                    spelltrap.placeCard(open_slot, card);
                } else {
                    spelltrap.placeCard(loc.get_specific(), card);
                }
                break;
            case FIELD_CARD:
                if (loc.get_specific() == -1) {
                    // Find next empty slot...
                    int open_slot = fieldcard.nextEmptySlot();
                    if (open_slot == -1)
                        return false;
                    fieldcard.placeCard(open_slot, card);
                } else {
                    fieldcard.placeCard(loc.get_specific(), card);
                }
                break;
            case GRAVEYARD:
                if (loc.get_specific() == -1) {
                    graveyard.pushCard(card);
                } else {
                    graveyard.placeCard(loc.get_specific(), card);
                }
                break;
            case BANISHED:
                if (loc.get_specific() == -1) {
                    banished.pushCard(card);
                } else {
                    banished.placeCard(loc.get_specific(), card);
                }
                break;
            case DECK:
                if (loc.get_specific() == -1) {
                    deck.pushCard(card);
                } else {
                    deck.placeCard(loc.get_specific(), card);
                }
                break;
            case EXTRA_DECK:
                if (loc.get_specific() == -1) {
                    extradeck.pushCard(card);
                } else {
                    extradeck.placeCard(loc.get_specific(), card);
                }
                break;
            case HAND:
                if (loc.get_specific() == -1) {
                    hand.pushCard(card);
                } else {
                    hand.placeCard(loc.get_specific(), card);
                }
                break;
            case LIMBO:
                // Limbo cards are considered to be any card that is not in a real location.
                // So this is a no-op.
                break;
        }
        return true;
    }

    public void placeCardOnField(GameCard card, FieldLocation loc) {
        switch (loc.get_generic()) {
            case MONSTER:
                if (loc.get_specific() < 0) throw new AssertionError();
                monsters.placeCard(loc.get_specific(), card);
                break;
            case SPELL_TRAP:
                if (loc.get_specific() < 0) throw new AssertionError();
                spelltrap.placeCard(loc.get_specific(), card);
                break;
            case FIELD_CARD:
                if (loc.get_specific() < 0) throw new AssertionError();
                fieldcard.placeCard(loc.get_specific(), card);
                break;
            case GRAVEYARD:
                graveyard.pushCard(card);
                break;
            case BANISHED:
                banished.pushCard(card);
                break;
            case DECK:
                deck.pushCard(card);
                break;
            case EXTRA_DECK:
                extradeck.pushCard(card);
                break;
            case HAND:
                hand.pushCard(card);
                break;
            case LIMBO:
                // Limbo cards are considered to be any card that is not in a real location.
                // So this is a no-op.
                break;
        }
    }

    public FieldLocation findCard(GameCard card) {
        /*
            Search order:
                - Deck
                - Hand
                - Field
                    - Monster
                    - Spell/Trap
                    - Field Card
                - Graveyard
                - Extra Deck
                - Banished
            If nowhere, it must be in limbo.
         */
        for (General g : new General[]{
                General.DECK, General.HAND, General.MONSTER, General.SPELL_TRAP, General.FIELD_CARD, General.GRAVEYARD,
                General.EXTRA_DECK, General.BANISHED}) {
            FieldLocation l = findCard(card, g);
            if (l.get_generic() != General.LIMBO) return l;
        }

        return new FieldLocation(General.LIMBO, -1);
    }

    public FieldLocation findCard(GameCard card, General gen) {
        int fnd;
        switch (gen) {
            case MONSTER:
                fnd = monsters.find(card);
                if (fnd != -1)
                    return new FieldLocation(General.MONSTER, fnd);
                break;
            case SPELL_TRAP:
                fnd = spelltrap.find(card);
                if (fnd != -1)
                    return new FieldLocation(General.SPELL_TRAP, fnd);
                break;
            case FIELD_CARD:
                fnd = fieldcard.find(card);
                if (fnd != -1)
                    return new FieldLocation(General.FIELD_CARD, fnd);
                break;
            case GRAVEYARD:
                fnd = graveyard.find(card);
                if (fnd != -1)
                    return new FieldLocation(General.GRAVEYARD, fnd);
                break;
            case BANISHED:
                fnd = banished.find(card);
                if (fnd != -1)
                    return new FieldLocation(General.BANISHED, fnd);
                break;
            case DECK:
                fnd = deck.find(card);
                if (fnd != -1)
                    return new FieldLocation(General.DECK, fnd);
                break;
            case EXTRA_DECK:
                fnd = extradeck.find(card);
                if (fnd != -1)
                    return new FieldLocation(General.EXTRA_DECK, fnd);
                break;
            case HAND:
                fnd = hand.find(card);
                if (fnd != -1)
                    return new FieldLocation(General.HAND, fnd);
                break;
            case LIMBO:
                // Cannot search for anything in limbo, because limbo means it's not on the field
                break;
        }
        return new FieldLocation(General.LIMBO, -1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Deck: ").append(deck);

        return builder.toString();
    }
}
