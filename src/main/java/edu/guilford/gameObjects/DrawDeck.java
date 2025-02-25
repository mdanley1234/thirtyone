package edu.guilford.gameObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * The DrawDeck class represents a deck of cards using a queue data structure.
 * It provides methods to add, remove, shuffle, and inspect cards in the deck.
 */
public class DrawDeck implements Queue<Card> {
    private final Queue<Card> cardQueue = new LinkedList<>();

    /**
     * Adds a card to the deck.
     *
     * @param card the card to be added to the deck
     * @return true if the card was added successfully
     */
    @Override
    public boolean add(Card card) {
        return cardQueue.add(card);
    }

    /**
     * Removes and returns the card at the front of the deck.
     *
     * @return the card at the front of the deck
     * @throws NoSuchElementException if the deck is empty
     */
    @Override
    public Card remove() {
        return cardQueue.remove();
    }

    /**
     * Retrieves and removes the card at the front of the deck, or returns null if the deck is empty.
     *
     * @return the card at the front of the deck, or null if the deck is empty
     */
    @Override
    public Card poll() {
        return cardQueue.poll();
    }

    /**
     * Retrieves, but does not remove, the card at the front of the deck.
     *
     * @return the card at the front of the deck
     * @throws NoSuchElementException if the deck is empty
     */
    @Override
    public Card element() {
        return cardQueue.element();
    }

    /**
     * Retrieves, but does not remove, the card at the front of the deck, or returns null if the deck is empty.
     *
     * @return the card at the front of the deck, or null if the deck is empty
     */
    @Override
    public Card peek() {
        return cardQueue.peek();
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if the deck is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return cardQueue.isEmpty();
    }

    /**
     * Returns the number of cards in the deck.
     *
     * @return the number of cards in the deck
     */
    @Override
    public int size() {
        return cardQueue.size();
    }

    /**
     * Removes all cards from the deck.
     */
    @Override
    public void clear() {
        cardQueue.clear();
    }

    /**
     * Populates the deck with a full set of playing cards.
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cardQueue.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck randomly.
     */
    public void shuffle() {
        List<Card> cardList = new ArrayList<>(cardQueue);
        Collections.shuffle(cardList);
        cardQueue.clear();
        cardQueue.addAll(cardList);
    }

    @Override
    public boolean offer(Card card) {
        return cardQueue.offer(card);
    }

    @Override
    public boolean contains(Object o) {
        return cardQueue.contains(o);
    }

    @Override
    public Object[] toArray() {
        return cardQueue.toArray();
    }

    @Override
    public boolean remove(Object o) {
        return cardQueue.remove(o);
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        return cardQueue.containsAll(c);
    }

    @Override
    public boolean addAll(java.util.Collection<? extends Card> c) {
        return cardQueue.addAll(c);
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        return cardQueue.removeAll(c);
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        return cardQueue.retainAll(c);
    }

    @Override
    public Iterator<Card> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
