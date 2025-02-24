package edu.guilford;

import edu.guilford.gameObjects.DiscardDeck;
import edu.guilford.gameObjects.DrawDeck;
import edu.guilford.playerModels.Group;
import edu.guilford.playerModels.Player;

public class ThirtyOneGame {
    
    private DrawDeck drawDeck; // Draw deck
    private DiscardDeck discardDeck; // Discard deck
    private Group playerGroup; // Game players
    private int turnNumber = 0; // Turn number in game
    private Player knockPlayer; // The player that knocks

    public ThirtyOneGame(Group playerGroup) {
        drawDeck = new DrawDeck();
        discardDeck = new DiscardDeck();
        this.playerGroup = playerGroup;

        // Max player count of 16
        if (playerGroup.size() > 16) {
            throw new IllegalArgumentException("Number of players cannot exceed 16.");
        }
    }

    public void playGame() {
        while (playerGroup.playersRemaining() > 1) {
            playRound();
        }
    }

    public void playRound() {
        resetGame();
        Player player = playerGroup.getNextPlayer();
        while (playTurn(player)) {
            player = playerGroup.getNextPlayer();
        }

        // Compile points and remove losing player
        int minScore = 31;
        Player minPlayer = playerGroup.getNextPlayer();
    
        for (int i = 0; i < playerGroup.size(); i++) {
            Player thePlayer = playerGroup.getNextPlayer();
            if (thePlayer.getHandValue() < minScore) {
                minScore = thePlayer.getHandValue();
                minPlayer = thePlayer;
            }
        }

        // Remove lives from losing player
        if (minPlayer == knockPlayer) {
            minPlayer.removeLives(2);
        }
        else {
            minPlayer.removeLives();
        }
    }

    // Single turn for one player (Return false to end round)
    private boolean playTurn(Player player) {

        // Check for empty discardDeck or drawDeck
        if (discardDeck.size() == 0) {
            discardDeck.push(drawDeck.deal());
        }
        else if (drawDeck.size() == 0) {
            while (discardDeck.size() > 1) {
                drawDeck.add(discardDeck.pop());
            }
            drawDeck.shuffle();
        }

        // If all players are knocked end round or if a player starts a knock begin knocking sequence
        if (playerGroup.knockComplete()) {
            return false;
        }
        else if (!playerGroup.knockStarted()) {
            if (player.requestKnock()) {
                knockPlayer = player;
                return true; 
            }
        }

        // Draw card
        Player.Deck drawDeckLocation = player.requestDrawLocation(discardDeck.peek());
        switch (drawDeckLocation) {
            case DRAW -> player.addCard(drawDeck.deal());
            case DISCARD -> player.addCard(discardDeck.pop());
        }

        // Discard card
        Player.Deck discardDeckLocation = player.requestDiscardLocation();
        switch (discardDeckLocation) {
            case DRAW -> {
                drawDeck.add(player.requestDiscardCard());
                player.removeCard(player.requestDiscardCard());
            }
            case DISCARD -> {
                discardDeck.push(player.requestDiscardCard());
                player.removeCard(player.requestDiscardCard());
            }
        }

        // Check if knocking sequence is active
        if (playerGroup.knockStarted()) {
            player.setKnock(true);
        }

        return true;
    }

    // Resets round and deals cards
    private void resetGame() {
        // Reset player hands
        playerGroup.resetPlayers();

        // Deal cards (Also resets decks)
        dealCards();

        // Reset turn number
        turnNumber = 0;
    }

    // Resets and reshuffles deck, then deals the cards to each player in the list
    private void dealCards() {
        discardDeck.clear();
        drawDeck.clear();
        drawDeck.build();
        drawDeck.shuffle();

        // Deal cards
        playerGroup.dealCards(drawDeck);
        discardDeck.push(drawDeck.deal());
    }

}