package edu.guilford.playerModels;

import edu.guilford.gameObjects.Card;

// Test model
public class TestModel extends Player {

    @Override
    public Deck requestDrawLocation(Card discardCard) {
        return Deck.DRAW;
    }

    @Override
    public Deck requestDiscardLocation() {
        return Deck.DISCARD;
    }

    @Override
    public Card requestDiscardCard() {
        return hand.getCard(0);
    }

    @Override
    public boolean requestKnock() {
        turnCounter++;

        // Test model knocks after turn 3
        if (turnCounter > 3) {
            knock = true;
        }
        return knock;
    }
    
}
