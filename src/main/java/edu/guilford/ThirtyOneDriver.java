package edu.guilford;

import edu.guilford.playerModels.Group;
import edu.guilford.playerModels.PlayerModelA;

public class ThirtyOneDriver {
    public static void main(String[] args) {
        PlayerModelA player1 = new PlayerModelA();
        PlayerModelA player2 = new PlayerModelA();
        Group group = new Group();
        group.addPlayer(player1);
        group.addPlayer(player2);

        System.out.println(group.toString());

    }
}