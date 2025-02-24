package edu.guilford.playerModels;

import edu.guilford.gameObjects.Card;
import edu.guilford.gameObjects.Hand;

/**
 * Represents a generic player in the ThirtyOne game. 
 * This abstract class defines core player attributes and methods, 
 * including hand management, draw and discard actions, and life tracking.
 */
public abstract class Player {

    /** The player's hand containing cards. */
    protected Hand hand = new Hand();

    /** The number of lives the player has. */
    protected int lives = 3;

    /** Indicates whether the player has knocked. */
    protected boolean knock = false;

    /**
     * Enumeration for deck types in the game: draw deck or discard deck.
     */
    public enum Deck {
        DRAW,
        DISCARD
    }

    /**
     * Determines from which deck the player wants to draw a card.
     * 
     * @param discardCard the top card of the discard pile
     * @return the deck from which the player chooses to draw (DRAW or DISCARD)
     */
    public abstract Deck requestDrawLocation(Card discardCard);

    /**
     * Determines where the player wants to discard a card.
     * 
     * @param discardCard the top card of the discard pile
     * @return the deck where the player chooses to discard (DRAW or DISCARD)
     */
    public abstract Deck requestDiscardLocation(Card discardCard);

    /**
     * Determines which card the player wants to discard.
     * 
     * @return the card that the player chooses to discard
     */
    public abstract Card requestDiscardCard();

    /**
     * Checks if the player chooses to knock during their turn.
     * 
     * @param turnNumber the current turn number
     * @return {@code true} if the player knocks, otherwise {@code false}
     */
    public boolean requestKnock(int turnNumber) {
        return knock;
    }

    /**
     * Returns knock value (Different from request lock, only a getter method)
     * 
     * @return {@code true} if the player knocks, otherwise {@code false}
    */
    public boolean getKnock() {
        return knock;
    }

    /**
     * Adds a card to the player's hand.
     * 
     * @param card the card to be added
     */
    public void addCard(Card card) {
        hand.addCard(card);
    }

    /**
     * Removes a specific card from the player's hand.
     * 
     * @param card the card to be removed
     */
    public void removeCard(Card card) {
        hand.removeCard(card);
    }

    /**
     * Clears the player's hand, removing all cards.
     * Also clears player knock boolean
     */
    public void clearHand() {
        hand.reset();
        knock = false;
    }

    /**
     * Retrieves the total value of the player's hand.
     * 
     * @return the total hand value
     */
    public int getHandValue() {
        return hand.getTotalValue();
    }

    /**
     * Reduces the player's lives by one.
     */
    public void removeLives() {
        lives--;
    }

    /**
     * Reduces the player's lives by a specified amount.
     * Ensures lives do not drop below zero.
     * 
     * @param lives the number of lives to remove
     */
    public void removeLives(int lives) {
        this.lives -= lives;
        if (this.lives < 0) {
            this.lives = 0;
        }
    }

    /**
     * Get player lives
     * @return the player's lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Returns a formatted string representation of the player, displaying the player's lives and hand value.
     *
     * @return a formatted string containing player details
     */
    @Override
    public String toString() {
        return String.format("Lives: %d | Hand Value: %d", lives, getHandValue());
    }

}
