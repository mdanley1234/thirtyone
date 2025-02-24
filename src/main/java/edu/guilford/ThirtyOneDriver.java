package edu.guilford;

import edu.guilford.playerModels.Group;
import edu.guilford.playerModels.TestModel;

public class ThirtyOneDriver {
    public static void main(String[] args) {
        TestModel player1 = new TestModel();
        TestModel player2 = new TestModel();
        Group group = new Group();
        group.addPlayer(player1);
        group.addPlayer(player2);

        System.out.println(group.toString());

    }
}