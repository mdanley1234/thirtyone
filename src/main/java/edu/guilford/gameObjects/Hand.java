package edu.guilford.gameObjects;

import java.util.ArrayList;

/**
 * The Hand class represents a player's hand of cards in a card game.
 * It provides methods to add, remove, reset, and calculate the total value of the hand.
 */
public class Hand {
    private final ArrayList<Card> hand;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        hand = new ArrayList<>();
    }

    /**
     * Returns the list of cards in the hand.
     *
     * @return the hand as an ArrayList of Card objects
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Adds a card to the hand.
     *
     * @param card the card to be added
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Removes a card from the hand.
     *
     * @param card the card to be removed
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Clears the hand of all cards.
     */
    public void reset() {
        hand.clear();
    }

    /**
     * Returns the number of cards in the hand.
     *
     * @return the size of the hand
     */
    public int size() {
        return hand.size();
    }

    /**
     * Retrieves a card at a specific index in the hand.
     *
     * @param index the index of the card to retrieve
     * @return the card at the specified index
     */
    public Card getCard(int index) {
        return hand.get(index);
    }

    /**
     * Calculates and returns the total value of the hand.
     * The value is calculated based on the card ranks, with aces valued at 11 by default.
     *
     * @return the highest possible total value of the hand
     */
    public int getTotalValue() {
        int[] values = new int[Card.Suit.values().length];
        for (Card.Suit suit : Card.Suit.values()) {
            values[suit.ordinal()] = 0;
            for (Card card : hand) {
                if (card.getSuit() == suit) {
                    // add the value of the card to the value of the suit
                    switch (card.getRank()) {
                        case ACE -> values[suit.ordinal()] += 11;
                        case TWO -> values[suit.ordinal()] += 2;
                        case THREE -> values[suit.ordinal()] += 3;
                        case FOUR -> values[suit.ordinal()] += 4;
                        case FIVE -> values[suit.ordinal()] += 5;
                        case SIX -> values[suit.ordinal()] += 6;
                        case SEVEN -> values[suit.ordinal()] += 7;
                        case EIGHT -> values[suit.ordinal()] += 8;
                        case NINE -> values[suit.ordinal()] += 9;
                        case TEN, JACK, QUEEN, KING -> values[suit.ordinal()] += 10;
                    }
                }
            }
        }
        int maxValue = values[0];
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    /**
     * Returns a string representation of the hand.
     *
     * @return a string containing the details of all cards in the hand
     */
    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();
        for (Card card : hand) {
            handString.append(card.toString()).append("\n");
        }
        return handString.toString();
    }
}
