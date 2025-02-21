package edu.guilford.playerModels;

import edu.guilford.gameObjects.Card;
import edu.guilford.gameObjects.Hand;

public abstract class Player {

    protected Hand hand = new Hand();
    
    public enum Draw {
        DECK,
        DISCARD
    }

    public enum Discard {
        DECK,
        DISCARD
    }

    public abstract Draw requestDraw(Card discardCard);

    public abstract Discard requestDiscardLocation(Card discardCard); // Discard location
    public abstract Card requestDiscardCard(); // Actual card returned
    
    public abstract boolean requestKnock(int turnNumber);

    public void addCard(Card card) {
        hand.addCard(card);
    } 

    public void removeCard(Card card) {
        hand.removeCard(card);
    }

    public void clearHand() {
        hand.reset();
    }
}
