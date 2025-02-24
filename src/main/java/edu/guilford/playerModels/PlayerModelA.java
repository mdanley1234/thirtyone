package edu.guilford.playerModels;

import edu.guilford.gameObjects.Card;

public class PlayerModelA extends Player {

    @Override
    public Deck requestDrawLocation(Card discardCard) {
        return Deck.DRAW;
    }

    @Override
    public Deck requestDiscardLocation(Card discardCard) {
        return Deck.DISCARD;
    }

    @Override
    public Card requestDiscardCard() {
        return hand.getCard(0);
    }
    
}
