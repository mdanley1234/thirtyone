package edu.guilford;

import java.util.ArrayList;
import java.util.HashMap;

import edu.guilford.gameObjects.Deck;
import edu.guilford.playerModels.Player;
import edu.guilford.playerModels.Player.Discard;
import edu.guilford.playerModels.Player.Draw;

public class ThirtyOneGame {
    
    private Deck deck; // Game deck
    private Deck discardDeck; // Discard pile
    private ArrayList<Player> players; // Game players
    private HashMap<Player, Integer> playerScores = new HashMap<>(); // Player scores
    private HashMap<Player, Boolean> playerKnocks = new HashMap<>(); // Checks for knocked players
    private int turnNumber = 0;

    public ThirtyOneGame(ArrayList<Player> players) {
        deck = new Deck();
        discardDeck = new Deck();
        this.players = players;

        // Max player count of 16
        if (players.size() > 16) {
            throw new IllegalArgumentException("Number of players cannot exceed 16.");
        }

        // Generate score map
        for (Player player : players) {
            playerScores.put(player, 0);
        }
    }

    private void playRound() {
        resetGame();
        int playerIndex = 0;
        Player player = players.get(playerIndex);
        while (playTurn(player)) {
            playerIndex++;
            if (playerIndex == players.size()) {
                playerIndex = 0;
                turnNumber++;
            }
            player = players.get(playerIndex);
        }

        // TODO: End game (Double penalty for knock player)
    }

    // Single turn for one player (Return false to end round)
    private boolean playTurn(Player player) {

        // Check for empty discard and deck
        if (discardDeck.size() == 0) {
            discardDeck.addTop(deck.deal());
        }
        else if (deck.size() == 0) {
            while (discardDeck.size() > 1) {
                deck.addBottom(discardDeck.deal());
            }
            deck.shuffle();
        }

        // Check for round end or player knocks
        if (playerKnocks.get(player)) {
            return false;
        }
        else if (player.requestKnock(turnNumber)) {
            playerKnocks.put(player, true);
            return true;
        }

        Draw draw = player.requestDraw(discardDeck.peek(0));
        switch (draw) {
            case DECK:
                player.addCard(deck.deal());    
                break;
            case DISCARD:
                player.addCard(discardDeck.deal());
                break;
        }

        Discard discard = player.requestDiscardLocation(discardDeck.peek(0));
        switch (discard) {
            case DECK -> {
                deck.addBottom(player.requestDiscardCard());
                player.removeCard(player.requestDiscardCard());
            }
            case DISCARD -> {
                discardDeck.addTop(player.requestDiscardCard());
                player.removeCard(player.requestDiscardCard());
            }
        }

        // Check if knocking sequence is active
        if (isKnock()) {
            playerKnocks.put(player, true);
        }

        return true;
    }

    private void resetGame() {
        // Reset player hands
        for (Player player : players) {
            player.clearHand();
        }

        // Reset decks
        discardDeck.clear();
        dealCards();

        // Reset turn number
        turnNumber = 0;

        // Reset knocks to false
        for (Player player : players) {
            playerKnocks.put(player, false);
        }
    }

    // Resets and reshuffles deck, then deals the cards to each player in the list
    private void dealCards() {
        deck.clear();
        deck.build();
        deck.shuffle();

        // Deal cards
        for (Player player : players) {
            player.addCard(deck.deal());
        }

        // Add first card to discard
        discardDeck.addTop(deck.deal());
    }

    // Checks if any player has knocked
    private boolean isKnock() {
        for (Player player : players) {
            if (playerKnocks.get(player)) {
                return true;
            }
        }
        return false;
    }

    

}
