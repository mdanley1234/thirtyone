package edu.guilford.gameObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * The DrawDeck class represents a deck of cards using a queue data structure.
 * It provides methods to add, remove, and inspect cards in the deck.
 */
public class DrawDeck {
    private Queue<Card> cardQueue = new LinkedList<>();

    /**
     * Adds a card to the deck.
     *
     * @param card the card to be added to the deck
     * @return true if the card was added successfully
     */
    public boolean add(Card card) {
        return cardQueue.add(card);
    }

    /**
     * Removes and returns the card at the front of the deck.
     *
     * @return the card at the front of the deck
     * @throws NoSuchElementException if the deck is empty
     */
    public Card remove() {
        return cardQueue.remove();
    }

    /**
     * Retrieves and removes the card at the front of the deck, or returns null if the deck is empty.
     *
     * @return the card at the front of the deck, or null if the deck is empty
     */
    public Card deal() {
        return cardQueue.poll();
    }

    /**
     * Retrieves, but does not remove, the card at the front of the deck.
     *
     * @return the card at the front of the deck
     * @throws NoSuchElementException if the deck is empty
     */
    public Card element() {
        return cardQueue.element();
    }

    /**
     * Retrieves, but does not remove, the card at the front of the deck, or returns null if the deck is empty.
     *
     * @return the card at the front of the deck, or null if the deck is empty
     */
    public Card peek() {
        return cardQueue.peek();
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return cardQueue.isEmpty();
    }

    /**
     * Returns the number of cards in the deck.
     *
     * @return the number of cards in the deck
     */
    public int size() {
        return cardQueue.size();
    }

    /**
     * Removes all cards from the deck.
     */
    public void clear() {
        cardQueue.clear();
    }

    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cardQueue.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        List<Card> cardList = new ArrayList<>(cardQueue);
        Collections.shuffle(cardList);
        cardQueue.clear();
        cardQueue.addAll(cardList);
    }
}