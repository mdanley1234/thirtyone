package edu.guilford.playerModels;

import edu.guilford.gameObjects.Card;
import edu.guilford.gameObjects.Card.Suit;

/**
 * The BasicModel class extends the Player class and implements a simple decision-making strategy
 * for drawing, discarding, and knocking based on hand evaluation.
 */
public class BasicModel extends Player {

    /**
     * Determines whether to draw from the discard pile or the draw deck.
     *
     * @param discardCard the top card from the discard pile
     * @return {@code Deck.DISCARD} if the discard card improves the hand, otherwise {@code Deck.DRAW}
     */
    @Override
    public Deck requestDrawLocation(Card discardCard) {
        return cardImprovesHand(discardCard) ? Deck.DISCARD : Deck.DRAW;
    }

    /**
     * Determines where to place the discarded card.
     *
     * @return {@code Deck.DISCARD}, indicating the card should be placed in the discard pile
     */
    @Override
    public Deck requestDiscardLocation() {
        return Deck.DISCARD;
    }

    /**
     * Chooses the least valuable card in hand to discard.
     *
     * @return the card that contributes the least to the hand’s highest total value
     */
    @Override
    public Card requestDiscardCard() {
        return findWorstCard();
    }

    /**
     * Determines whether the player should knock (end the round).
     * The BasicModel knocks if the hand's value is at least 27 or if more than five turns have passed.
     *
     * @return {@code true} if the player knocks, {@code false} otherwise
     */
    @Override
    public boolean requestKnock() {
        int handValue = hand.getTotalValue();
        turnCounter++;

        if (handValue >= 27 || turnCounter > 5) {
            knock = true;
        }
        return knock;
    }

    /**
     * Determines if the given card from the discard pile can improve the current hand.
     *
     * @param candidate the card being considered from the discard pile
     * @return {@code true} if the card improves the hand, {@code false} otherwise
     */
    private boolean cardImprovesHand(Card candidate) {
        int currentValue = hand.getTotalValue();
        Card worstInSuit = findWorstCardInSuit(candidate.getSuit());
        int candidateValue = cardValue(candidate);

        int potentialNewValue = candidateValue;
        if (worstInSuit != null) {
            int currentSuitSum = calculateSuitSum(candidate.getSuit());
            potentialNewValue = currentSuitSum - cardValue(worstInSuit) + candidateValue;
        }

        return potentialNewValue > currentValue;
    }

    /**
     * Returns the numeric value of a card based on its rank.
     *
     * @param card the card whose value is being determined
     * @return the card's numerical value
     */
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

    /**
     * Finds the lowest-value card in the given suit.
     *
     * @param suit the suit to check
     * @return the worst card in the suit, or {@code null} if none are found
     */
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

    /**
     * Calculates the total value of all cards of the given suit in hand.
     *
     * @param suit the suit to evaluate
     * @return the sum of all card values of that suit
     */
    private int calculateSuitSum(Suit suit) {
        int sum = 0;
        for (Card card : hand.getHand()) {
            if (card.getSuit() == suit) {
                sum += cardValue(card);
            }
        }
        return sum;
    }

    /**
     * Identifies the worst card in the hand, which is the one contributing the least to the overall hand value.
     *
     * @return the worst card in the hand
     */
    private Card findWorstCard() {
        Card worst = null;
        int worstContribution = Integer.MAX_VALUE;

        for (Card card : hand.getHand()) {
            int suitSum = calculateSuitSum(card.getSuit());
            int contribution = suitSum;

            if (contribution < worstContribution) {
                worstContribution = contribution;
                worst = card;
            }
        }

        return worst != null ? worst : hand.getCard(0);
    }
}