package edu.guilford;

import edu.guilford.playerModels.Group;
import edu.guilford.playerModels.TestModel;

public class ThirtyOneDriver {
    public static void main(String[] args) {
        TestModel player1 = new TestModel();
        TestModel player2 = new TestModel();
        TestModel player3 = new TestModel();
        TestModel player4 = new TestModel();
        TestModel player5 = new TestModel();
        TestModel player6 = new TestModel();

        Group group = new Group();
        group.addPlayer(player1);
        group.addPlayer(player2);
        group.addPlayer(player3);
        group.addPlayer(player4);
        group.addPlayer(player5);
        group.addPlayer(player6);

        ThirtyOneGame game = new ThirtyOneGame(group);

        System.out.println(group.toString());

        game.playRound();

        System.out.println(group.toString());


    }
}