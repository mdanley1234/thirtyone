package edu.guilford;

import edu.guilford.playerModels.BasicModel;
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

        game.playGame();

        System.out.println(group.toString());

        System.out.println("The winning player is player number " + group.getWinner());

        int[] wins = new int[5];

        for (int i = 0; i < 1000; i++) {
            TestModel p1 = new TestModel();
            TestModel p2 = new TestModel();
            TestModel p3 = new TestModel();
            TestModel p4 = new TestModel();
            BasicModel p5 = new BasicModel();

            Group grp = new Group();
            grp.addPlayer(p1);
            grp.addPlayer(p2);
            grp.addPlayer(p3);
            grp.addPlayer(p4);
            grp.addPlayer(p5);

            ThirtyOneGame gm = new ThirtyOneGame(grp);
            gm.playGame();

            int winner = grp.getWinner();
            wins[winner - 1]++;
        }

        System.out.println("Statistics after 1000 games:");
        for (int i = 0; i < wins.length; i++) {
            System.out.println("Player " + (i + 1) + " wins: " + wins[i]);
        }

    }
}