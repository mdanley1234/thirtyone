package edu.guilford.playerModels;

import edu.guilford.gameObjects.Card;

/**
 * The TestModel class extends the Player class and provides predefined behaviors
 * for testing purposes. This model follows a simple logic to interact with the game,
 * including drawing, discarding, and knocking after a certain number of turns.
 */
public class TestModel extends Player {

    /**
     * Determines where to draw a card from when it's the player's turn.
     *
     * @param discardCard the top card from the discard pile
     * @return {@code Deck.DRAW}, indicating the card should be drawn from the draw deck
     */
    @Override
    public Deck requestDrawLocation(Card discardCard) {
        return Deck.DRAW;
    }

    /**
     * Determines where to place the discarded card.
     *
     * @return {@code Deck.DISCARD}, indicating the card should be placed in the discard pile
     */
    @Override
    public Deck requestDiscardLocation() {
        return Deck.DISCARD;
    }

    /**
     * Chooses a card from the player's hand to discard.
     *
     * @return the first card in the player's hand
     */
    @Override
    public Card requestDiscardCard() {
        return hand.getCard(0);
    }

    /**
     * Determines whether the player should knock (end the round).
     * The test model knocks after three turns.
     *
     * @return {@code true} if the player has taken more than three turns, {@code false} otherwise
     */
    @Override
    public boolean requestKnock() {
        turnCounter++;

        // The test model knocks after turn 3
        if (turnCounter > 3) {
            knock = true;
        }
        return knock;
    }
}
