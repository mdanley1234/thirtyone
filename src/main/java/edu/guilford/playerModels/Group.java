package edu.guilford.playerModels;

import java.util.ArrayList;

// Holds a group of Player objects in a list
public class Group {
    
    private ArrayList<Player> players = new ArrayList<>();
    private int playerCounter = 0; // Returns nth player in players list

    // Constructor assigning players
    public Group(ArrayList<Player> players) {
        this.players = players;
    }

    // Constructor that takes a Player class and a number
    public Group(Class<? extends Player> playerClass, int numberOfPlayers) {
        try {
            for (int i = 0; i < numberOfPlayers; i++) {
                players.add(playerClass.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Constructor (Empty)
    public Group() {}

    // Get next player with lives > 0
    public Player getNextPlayer() {
        // Increment to next alive player
        do {
            playerCounter++;
            if (playerCounter == players.size()) {
            playerCounter = 0;
            }
        } while (players.get(playerCounter).getLives() == 0);

        return players.get(playerCounter);
    }

    // Reset players (and counter)
    public void resetPlayers() {
        for (Player player : players) {
            player.clearHand();
        }
        playerCounter = 0;
    }

    // Add a player
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Returns a formatted string representation of all players, including their player number.
     *
     * @return a formatted string containing details of each player
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int playerNumber = 1;
        for (Player player : players) {
            sb.append(String.format("Player %d) ", playerNumber))
            .append(player.toString())
            .append(System.lineSeparator());
            playerNumber++;
        }
        return sb.toString();
    }

    // All knock check
    public boolean knockComplete() {
        for (Player player : players) {
            if (!player.getKnock() && player.getLives() > 0) {
                return false;
            }
        }
        return true;
    }

    // Any knock check
    public boolean knockStarted() {
        for (Player player : players) {
            if (player.getKnock() && player.getLives() > 0) {
                return true;
            }
        }
        return false;
    }

}
