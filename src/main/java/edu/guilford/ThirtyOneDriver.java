package edu.guilford;

import java.util.Scanner;

import edu.guilford.playerModels.AdvancedModel;
import edu.guilford.playerModels.BasicModel;
import edu.guilford.playerModels.Group;
import edu.guilford.playerModels.TestModel;

public class ThirtyOneDriver {
    public static void main(String[] args) {

        System.out.println("31 Card Game Program Testing:");

        // Test 1: Initial testing

        // Create a player group of 5 test bots
        Group group = new Group();
        for (int i = 0; i < 5; i++) {
            group.addPlayer(new TestModel());
        }

        // Create a game of ThirtyOne
        ThirtyOneGame firstGame = new ThirtyOneGame(group);

        System.out.println("Initial Player Group");
        System.out.println(group.toString());

        // Iterate through the rounds
        Scanner scanner = new Scanner(System.in);
        while (group.playersRemaining() > 1) {
            firstGame.playRound();
            System.out.println("Next Round:");
            System.out.println(group.toString());

            System.out.println("Press Enter to continue to the next round...");
            scanner.nextLine();
        }
        scanner.close();
        System.out.println("The winning player is player number " + group.getWinner());

        /*
         * Conclusion: The above test should test a very simple model that has no logic capacity.
         * It should iterate through all the rounds producing a random winner
         */

        // Test 2: A smarter model
        /*
         * This test will implement a smarter logic capable model and pit it against 4 TestModels.
         * A loop will play 1000 games using the 1 smarter model against 4 dumb models and print statistics.
         */

        System.out.println("");
        System.out.println("1000 Testing of BasicModel (player 5) against 4 TestModels (players 1-4)");

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

        /**
         * Conclusion: The smarter model is significantly better than the dumb model, winning ~99% of the time!
         */

         // Test 3: Advanced model
         /*
          * This test will implement the Basic Model and Advanced Model for testing
          */

        System.out.println("");
        System.out.println("1000 Testing of BasicModel & AdvancedModel (players 1-3, 4-6)");

        int[] wins2 = new int[6];

        for (int i = 0; i < 1000; i++) {
            // Create 3 basic models
            BasicModel p1 = new BasicModel();
            BasicModel p2 = new BasicModel();
            BasicModel p3 = new BasicModel();

            // Create 3 advanced models
            AdvancedModel p4 = new AdvancedModel();
            AdvancedModel p5 = new AdvancedModel();
            AdvancedModel p6 = new AdvancedModel();

            Group grp = new Group();
            grp.addPlayer(p1);
            grp.addPlayer(p2);
            grp.addPlayer(p3);
            grp.addPlayer(p4);
            grp.addPlayer(p5);
            grp.addPlayer(p6);
            ThirtyOneGame gm = new ThirtyOneGame(grp);
            gm.playGame();

            int winner = grp.getWinner();
            wins2[winner - 1]++;
        }

        int basicModelWins = wins2[0] + wins2[1] + wins2[2];
        int advancedModelWins = wins2[3] + wins2[4] + wins2[5];

        System.out.println("Statistics after 1000 games:");
        System.out.println("BasicModel (players 1-3) wins: " + basicModelWins);
        System.out.println("AdvancedModel (players 4-6) wins: " + advancedModelWins);

        /**
         * Conclusion: As expected, the advanced model is significantly stronger and wins ~90% of the time over the basic model!
         */
    }
}