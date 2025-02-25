package edu.guilford.gameObjects;

import java.util.Random;

/**
 * The Card class represents a standard playing card with a suit and rank.
 * It implements the Comparable interface for sorting based on suit or rank.
 */
public class Card implements Comparable<Card> {

    /**
     * Enum representing the four suits of a standard deck of playing cards.
     */
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    /**
     * Enum representing the thirteen ranks of a standard deck of playing cards.
     */
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    // Instance variables
    private final Suit suit;
    private final Rank rank;

    /**
     * Constant for sorting by suit.
     */
    public static final int SORT_BY_SUIT = 1;

    /**
     * Constant for sorting by rank.
     */
    public static final int SORT_BY_RANK = 2;

    /**
     * Static variable to determine the sorting method.
     */
    private static int sortMethod = SORT_BY_RANK;

    /**
     * Constructs a Card with a specified suit and rank.
     *
     * @param suit The suit of the card.
     * @param rank The rank of the card.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Constructs a Card with a random suit and rank.
     */
    public Card() {
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    /**
     * Gets the suit of the card.
     *
     * @return The suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the rank of the card.
     *
     * @return The rank of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets the sorting method for card comparison.
     *
     * @param sortMethod The sorting method (SORT_BY_SUIT or SORT_BY_RANK).
     */
    public static void setSortMethod(int sortMethod) {
        Card.sortMethod = sortMethod;
    }

    /**
     * Returns a string representation of the card.
     *
     * @return A string representing the card's rank and suit.
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Compares this card with another card for ordering.
     * Sorting is based on the currently set sort method.
     *
     * @param otherCard The card to be compared with.
     * @return A positive number if this card is greater, negative if smaller, and 0 if equal.
     */
    @Override
    public int compareTo(Card otherCard) {
        if (sortMethod == SORT_BY_SUIT) {
            if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                return 1;
            } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                return -1;
            } else {
                return Integer.compare(this.rank.ordinal(), otherCard.rank.ordinal());
            }
        } else {
            if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                return 1;
            } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                return -1;
            } else {
                return Integer.compare(this.suit.ordinal(), otherCard.suit.ordinal());
            }
        }
    }
}
