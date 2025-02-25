package edu.guilford;

import edu.guilford.gameObjects.DiscardDeck;
import edu.guilford.gameObjects.DrawDeck;
import edu.guilford.playerModels.Group;
import edu.guilford.playerModels.Player;

/**
 * The ThirtyOneGame class manages the flow of a game of Thirty-One, handling
 * player turns, deck management, and round progression.
 */
public class ThirtyOneGame {
    
    private final DrawDeck drawDeck; // The draw deck for the game
    private final DiscardDeck discardDeck; // The discard deck for the game
    private final Group playerGroup; // The group of players in the game
    private Player knockPlayer; // The player that initiates the knock

    /**
     * Constructs a new Thirty-One game with the specified group of players.
     * 
     * @param playerGroup the group of players participating in the game
     * @throws IllegalArgumentException if the number of players exceeds 16
     */
    public ThirtyOneGame(Group playerGroup) {
        drawDeck = new DrawDeck();
        discardDeck = new DiscardDeck();
        this.playerGroup = playerGroup;

        // Ensure the player count does not exceed 16
        if (playerGroup.size() > 16) {
            throw new IllegalArgumentException("Number of players cannot exceed 16.");
        }
    }

    /**
     * Plays the game until only one player remains.
     */
    public void playGame() {
        while (playerGroup.playersRemaining() > 1) {
            playRound();
        }
    }

    /**
     * Plays a single round of the game, resetting the game state, managing turns,
     * and determining the losing player.
     */
    public void playRound() {
        resetGame();
        Player player = playerGroup.getNextPlayer();
        
        // Continue playing turns until a condition ends the round
        while (playTurn(player)) {
            player = playerGroup.getNextPlayer();
        }

        // Determine the player with the lowest hand value
        int minScore = 31;
        Player minPlayer = playerGroup.getNextPlayer();
    
        for (int i = 0; i < playerGroup.size(); i++) {
            Player thePlayer = playerGroup.getNextPlayer();
            if (thePlayer.getHandValue() < minScore) {
                minScore = thePlayer.getHandValue();
                minPlayer = thePlayer;
            }
        }

        // Reduce lives based on the knock rule
        if (minPlayer == knockPlayer) {
            minPlayer.removeLives(2);
        } else {
            minPlayer.removeLives();
        }
    }

    /**
     * Plays a single turn for the given player.
     * 
     * @param player the player taking the turn
     * @return {@code false} if the round should end, otherwise {@code true}
     */
    private boolean playTurn(Player player) {

        // Ensure discard and draw decks are not empty
        if (discardDeck.size() == 0) {
            discardDeck.push(drawDeck.poll());
        } else if (drawDeck.isEmpty()) {
            while (discardDeck.size() > 1) {
                drawDeck.add(discardDeck.pop());
            }
            drawDeck.shuffle();
        }

        // End the round if all players have knocked
        if (playerGroup.knockComplete()) {
            return false;
        }

        // Start the knock sequence if no player has knocked yet
        if (!playerGroup.knockStarted()) {
            if (player.requestKnock()) {
                knockPlayer = player;
                return true; 
            }
        }

        // Player draws a card
        Player.Deck drawDeckLocation = player.requestDrawLocation(discardDeck.peek());
        switch (drawDeckLocation) {
            case DRAW -> player.addCard(drawDeck.poll());
            case DISCARD -> player.addCard(discardDeck.pop());
        }

        // Player discards a card
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

        // If a knocking sequence is active, mark the player as knocked
        if (playerGroup.knockStarted()) {
            player.setKnock(true);
        }

        return true;
    }

    /**
     * Resets the game state at the start of a new round.
     */
    private void resetGame() {
        // Reset player hands
        playerGroup.resetPlayers();

        // Deal new cards and reset decks
        dealCards();
    }

    /**
     * Resets and reshuffles the deck, then deals cards to each player.
     */
    private void dealCards() {
        discardDeck.clear();
        drawDeck.clear();
        drawDeck.build();
        drawDeck.shuffle();

        // Deal cards to players
        playerGroup.dealCards(drawDeck);
        discardDeck.push(drawDeck.poll());
    }
}