package edu.guilford.gameObjects;

import java.util.Stack;

/**
 * The DiscardDeck class represents a stack-based discard pile for a card game.
 * It provides methods to push, pop, peek, check emptiness, get size, and clear the deck.
 */
public class DiscardDeck {
    
    /**
     * Stack to store discarded cards.
     */
    private final Stack<Card> cardStack = new Stack<>();

    /**
     * Pushes a card onto the discard deck.
     * 
     * @param card The card to be added to the discard deck.
     */
    public void push(Card card) {
        cardStack.push(card);
    }

    /**
     * Pops a card from the discard deck.
     * 
     * @return The top card from the discard deck.
     */
    public Card pop() {
        return cardStack.pop();
    }

    /**
     * Peeks at the top card of the discard deck without removing it.
     * 
     * @return The top card from the discard deck.
     */
    public Card peek() {
        return cardStack.peek();
    }

    /**
     * Checks if the discard deck is empty.
     * 
     * @return true if the discard deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return cardStack.isEmpty();
    }

    /**
     * Gets the size of the discard deck.
     * 
     * @return The number of cards in the discard deck.
     */
    public int size() {
        return cardStack.size();
    }

    /**
     * Clears the discard deck.
     */
    public void clear() {
        cardStack.clear();
    }
}
