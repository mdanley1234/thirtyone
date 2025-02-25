package edu.guilford.playerModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.guilford.gameObjects.Card;
import edu.guilford.gameObjects.Card.Suit;

/**
 * The AdvancedModel class extends Player and implements an improved decision-making strategy
 * for drawing, discarding, and knocking by considering optimal moves and tracking game state.
 */
public class AdvancedModel extends Player {

    @Override
    public Deck requestDrawLocation(Card discardCard) {
        int currentValue = hand.getTotalValue();
        List<Card> tempHand = new ArrayList<>(hand.getHand());
        tempHand.add(discardCard);

        int bestNewMax = 0;
        for (int i = 0; i < tempHand.size(); i++) {
            List<Card> newHand = new ArrayList<>(tempHand);
            newHand.remove(i);
            int maxSuitSum = calculateMaxSuitSum(newHand);
            if (maxSuitSum > bestNewMax) {
                bestNewMax = maxSuitSum;
            }
        }

        // Dynamic threshold: higher threshold when current hand is weaker
        int requiredGain = Math.max(1, (31 - currentValue) / 10);
        if (bestNewMax - currentValue >= requiredGain) {
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
        List<Card> handCards = hand.getHand();
        Card bestDiscard = null;
        int bestMaxSum = -1;
        int minValue = Integer.MAX_VALUE;

        for (Card candidate : handCards) {
            List<Card> tempHand = new ArrayList<>(handCards);
            tempHand.remove(candidate);
            int currentMax = calculateMaxSuitSum(tempHand);

            if (currentMax > bestMaxSum || (currentMax == bestMaxSum && cardValue(candidate) < minValue)) {
                bestMaxSum = currentMax;
                bestDiscard = candidate;
                minValue = cardValue(candidate);
            }
        }

        return bestDiscard != null ? bestDiscard : hand.getCard(0);
    }

    @Override
    public boolean requestKnock() {
        int handValue = hand.getTotalValue();
        turnCounter++;

        // Adjust thresholds dynamically based on turn and hand value
        if (handValue == 31) {
            knock = true; // Immediate knock if max value
        } else if (handValue >= 29) {
            knock = true;
        } else if (handValue >= 27) {
            // Knock earlier if in mid-late game
            if (turnCounter >= 3) knock = true;
        } else if (turnCounter > 5) {
            // Late game, more aggressive
            if (handValue >= 25) knock = true;
        } else if (turnCounter > 10) {
            knock = true; // Knock model time-out
        }

        return knock;
    }

    /**
     * Calculates the maximum suit sum for a given list of cards.
     * @param cards the list of cards to evaluate
     * @return the maximum sum of any single suit in the list
     */
    private int calculateMaxSuitSum(List<Card> cards) {
        Map<Suit, Integer> suitSums = new HashMap<>();
        for (Card card : cards) {
            Suit suit = card.getSuit();
            suitSums.put(suit, suitSums.getOrDefault(suit, 0) + cardValue(card));
        }
        return suitSums.values().stream().max(Integer::compare).orElse(0);
    }

    private int cardValue(Card card) {
        return switch (card.getRank()) {
            case ACE -> 11;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN, JACK, QUEEN, KING -> 10;
            default -> 0;
        };
    }
}