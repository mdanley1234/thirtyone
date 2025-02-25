package edu.guilford.playerModels;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import edu.guilford.gameObjects.DrawDeck;

/**
 * The Group class manages a list of Player objects, facilitating player turns,
 * dealing cards, and determining game state conditions.
 */
public class Group {
    
    private ArrayList<Player> players = new ArrayList<>();
    private int playerCounter = 0; // Tracks the current player index

    /**
     * Constructs a Group with a predefined list of players.
     *
     * @param players the list of players to be included in the group
     */
    public Group(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Constructs a Group with a specified number of players of a given type.
     *
     * @param playerClass the class type of the players
     * @param numberOfPlayers the number of players to create
     */
    public Group(Class<? extends Player> playerClass, int numberOfPlayers) {
        try {
            for (int i = 0; i < numberOfPlayers; i++) {
                players.add(playerClass.getDeclaredConstructor().newInstance());
            }
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {}
    }

    /**
     * Constructs an empty Group with no players.
     */
    public Group() {}

    /**
     * Retrieves the next player who is still in the game (has remaining lives).
     *
     * @return the next active player
     */
    public Player getNextPlayer() {
        do {
            playerCounter++;
            if (playerCounter == players.size()) {
                playerCounter = 0;
            }
        } while (players.get(playerCounter).getLives() == 0);

        return players.get(playerCounter);
    }

    /**
     * Resets all players by clearing their hands and resetting the player counter.
     */
    public void resetPlayers() {
        for (Player player : players) {
            player.clearHand();
        }
        playerCounter = 0;
    }

    /**
     * Adds a player to the group.
     *
     * @param player the player to add
     */
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

    /**
     * Checks if all remaining players have knocked, indicating the end of the round.
     *
     * @return {@code true} if all active players have knocked, otherwise {@code false}
     */
    public boolean knockComplete() {
        for (Player player : players) {
            if (!player.getKnock() && player.getLives() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if any player has knocked, indicating the start of the final turn.
     *
     * @return {@code true} if at least one player has knocked, otherwise {@code false}
     */
    public boolean knockStarted() {
        for (Player player : players) {
            if (player.getKnock() && player.getLives() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of players in the group.
     *
     * @return the number of players
     */
    public int size() {
        return players.size();
    }

    /**
     * Deals three cards to each player from the draw deck.
     *
     * @param drawDeck the deck from which cards are drawn
     */
    public void dealCards(DrawDeck drawDeck) {
        for (int i = 0; i < 3; i++) {
            for (Player player : players) {
                player.addCard(drawDeck.poll());
            }
        }
    }

    /**
     * Retrieves the list of players in the group.
     *
     * @return the list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the number of players still in the game (players with remaining lives).
     *
     * @return the count of players still active
     */
    public int playersRemaining() {
        int playersRemain = 0;
        for (Player player : players) {
            if (player.getLives() > 0) {
                playersRemain++;
            }
        }
        return playersRemain;
    }

    /**
     * Determines the winner of the game. The winner is the last player remaining.
     *
     * @return the winning player's index (1-based), or -1 if there is no single winner
     */
    public int getWinner() {
        if (playersRemaining() == 1) {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getLives() > 0) {
                    return i + 1;
                }
            }
        }
        return -1;
    }
}