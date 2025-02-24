package edu.guilford.gameObjects;

import java.util.Stack;

public class DiscardDeck {
    private Stack<Card> cardStack = new Stack<>();

    // Push a card onto the discard deck
    public void push(Card card) {
        cardStack.push(card);
    }

    // Pop a card from the discard deck
    public Card pop() {
        return cardStack.pop();
    }

    // Peek at the top card of the discard deck without removing it
    public Card peek() {
        return cardStack.peek();
    }

    // Check if the discard deck is empty
    public boolean isEmpty() {
        return cardStack.isEmpty();
    }

    // Get the size of the discard deck
    public int size() {
        return cardStack.size();
    }

    // Clear the discard deck
    public void clear() {
        cardStack.clear();
    }
}