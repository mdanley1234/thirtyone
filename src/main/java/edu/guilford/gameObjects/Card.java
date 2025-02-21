package edu.guilford.gameObjects;

import java.util.Random;

public class Card implements Comparable<Card> {

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
        KING

    }

    // instance variables
    private Suit suit;
    private Rank rank;

    public static final int SORT_BY_SUIT = 1;
    public static final int SORT_BY_RANK = 2;
    private static int sortMethod = SORT_BY_RANK;

    // constructor
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card() {
        // random Card
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    // getters
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public static void setSortMethod(int sortMethod) {
        Card.sortMethod = sortMethod;
    }

    // toString

    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public int compareTo(Card otherCard) {
        if (sortMethod == SORT_BY_SUIT) {
            if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                return 1;
            } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                return -1;
            } else {
                if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                    return 1;
                } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                    return -1;
                }
            }
        } else {
            if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                return 1;
            } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                return -1;
            } else {
                if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                    return 1;
                } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                    return -1;
                }
            }
        }

        return 0;
    }

}