package edu.guilford.playerModels;

import edu.guilford.gameObjects.Card;
import edu.guilford.gameObjects.Card.Suit;

public class BasicModel extends Player {

    @Override
    public Deck requestDrawLocation(Card discardCard) {
        // If the discard card improves the hand, take from the discard pile.
        if (cardImprovesHand(discardCard)) {
            return Deck.DISCARD;
        }
        return Deck.DRAW;
    }

    @Override
    public Deck requestDiscardLocation() {
        return Deck.DISCARD;
    }

    @Override
    public Card requestDiscardCard() {
        // Choose the card that contributes the least to the hand’s highest total value.
        return findWorstCard();
    }

    @Override
    public boolean requestKnock() {
        int handValue = hand.getTotalValue();
        turnCounter++;

        // Knock if the hand is strong (e.g., at least 27) or if several turns have passed.
        if (handValue >= 27 || turnCounter > 5) {
            knock = true;
        }
        return knock;
    }

    // Determines if the candidate card from the discard pile can improve the hand
    private boolean cardImprovesHand(Card candidate) {
        // Get current hand value.
        int currentValue = hand.getTotalValue();
        
        // Determine what the hand value would be if we replaced the worst card in the candidate's suit.
        Card worstInSuit = findWorstCardInSuit(candidate.getSuit());
        int candidateValue = cardValue(candidate);
        
        // If there is a card of that suit already, compute potential new sum.
        int potentialNewValue = candidateValue;
        if (worstInSuit != null) {
            // Calculate current suit sum.
            int currentSuitSum = calculateSuitSum(candidate.getSuit());
            // New suit sum would be: current sum - worst card value + candidate card value.
            potentialNewValue = currentSuitSum - cardValue(worstInSuit) + candidateValue;
        }
        
        // If the new suit sum is higher than the current hand value, the card is helpful.
        return potentialNewValue > currentValue;
    }

    // Returns the numeric value of a card based on its rank.
    private int cardValue(Card card) {
        switch (card.getRank()) {
            case ACE:
                return 11;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                return 0;
        }
    }

    // Finds the card in hand of the given suit that contributes least to that suit’s total.
    private Card findWorstCardInSuit(Suit suit) {
        Card worst = null;
        for (Card card : hand.getHand()) {
            if (card.getSuit() == suit) {
                if (worst == null || cardValue(card) < cardValue(worst)) {
                    worst = card;
                }
            }
        }
        return worst;
    }

    // Calculates the total value of cards in hand for the given suit.
    private int calculateSuitSum(Suit suit) {
        int sum = 0;
        for (Card card : hand.getHand()) {
            if (card.getSuit() == suit) {
                sum += cardValue(card);
            }
        }
        return sum;
    }

    // Finds the worst card in the hand that, if discarded, is most likely to improve the overall hand value.
    private Card findWorstCard() {
        Card worst = null;
        int worstContribution = Integer.MAX_VALUE;
        // For each card in hand, calculate the potential decrease in hand value if removed.
        for (Card card : hand.getHand()) {
            // For the card’s suit, what is the total before removing it?
            int suitSum = calculateSuitSum(card.getSuit());
            int contribution = suitSum;
            // We consider lower contribution as "worse" because discarding it is less costly.
            if (contribution < worstContribution) {
                worstContribution = contribution;
                worst = card;
            }
        }
        // If somehow all cards are equally valuable, return the first card.
        if (worst == null) {
            worst = hand.getCard(0);
        }
        return worst;
    }
}
