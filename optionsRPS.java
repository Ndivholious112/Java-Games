import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class optionsRPS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String computerName = rockPaperScissor.computerNames[random.nextInt(rockPaperScissor.computerNames.length)];
        System.out.println("Hey, I'm " + computerName + "!");

        System.out.println("Welcome to Rock-Paper-Scissors game!");
        System.out.println("Enter R for Rock, P for Paper, S for Scissors, or Q to Quit.");

        System.out.print("What's your name? ");
        String playerName = scanner.nextLine().trim();

        System.out.print("Do you want to add more players? (Yes/No): ");
        String morePlayersResponse = scanner.nextLine().trim().toLowerCase();

        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add(playerName);

        if (morePlayersResponse.equals("yes") || morePlayersResponse.equals("y")) {
            int additionalPlayers = 0;
            while (true) {
                System.out.print("How many additional players? ");
                String input = scanner.nextLine().trim();
                try {
                    additionalPlayers = Integer.parseInt(input);
                    if (additionalPlayers < 1) {
                        System.out.println("Please enter a positive number.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a valid integer.");
                }
            }

            for (int i = 0; i < additionalPlayers; i++) {
                String randomName;
                do {
                    randomName = rockPaperScissor.randomUsername[random.nextInt(rockPaperScissor.randomUsername.length)];
                } while (playerNames.contains(randomName));
                playerNames.add(randomName);
            }

            System.out.println("\nPlayers in this game:");
            for (String name : playerNames) {
                System.out.println("- " + name);
            }
        } else {
            System.out.println("\nStarting a one-on-one game between " + playerName + " and " + computerName + ".");
        }
        playerNames.add(computerName);

        ArrayList<Integer> playerScores = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            playerScores.add(0);
        }

        int round = 1;

        while (playerNames.size() > 2) {
            System.out.println("\n=== Round " + round + " ===");

            if (round > 1) {
                for (int i = 0; i < playerScores.size(); i++) {
                    playerScores.set(i, 0);
                }
            }

            for (int i = 0; i < playerNames.size(); i++) {
                String currentPlayer = playerNames.get(i);

                if (currentPlayer.equals(computerName)) {
                    continue;
                }

                System.out.println("\n" + currentPlayer + "'s turn.");

                int playerChoice;
           
                if (currentPlayer.equals(playerName)) {
                    while (true) {
                        System.out.print("Enter (R/P/S) or Q to quit: ");
                        String input = scanner.nextLine().trim().toLowerCase();

                        if (input.length() != 1) {
                            System.out.println("Invalid input! Please enter R, P, S, or Q.");
                            continue;
                        }

                        char playerChar = input.charAt(0);

                        if (playerChar == 'q') {
                            System.out.println("\n" + currentPlayer + " chose to quit the game.");
                            printFinalScores(playerNames, playerScores);
                            scanner.close();
                            return;
                        }

                        playerChoice = choice(playerChar);
                        if (playerChoice == -1) {
                            System.out.println("Invalid choice! Please enter R, P, S, or Q.");
                            continue;
                        }
                        break;
                    }
                } else {
                
                    playerChoice = random.nextInt(3);
                    System.out.println(currentPlayer + " chose: " + rockPaperScissor.Options[playerChoice]);
                }

                int computerChoice = random.nextInt(3);
                System.out.println(computerName + " chose: " + rockPaperScissor.Options[computerChoice]);

                int result = rockPaperScissor.Results[playerChoice][computerChoice];

                if (result == 1) {
                    playerScores.set(i, playerScores.get(i) + 1);
                    System.out.println(currentPlayer + " wins this round!");
                } else if (result == -1) {
                    int computerIndex = playerNames.indexOf(computerName);
                    playerScores.set(computerIndex, playerScores.get(computerIndex) + 1);
                    System.out.println(computerName + " wins this round!");
                } else {
                    System.out.println("This round is a draw!");
                }
            }

            if (playerNames.size() > 2) {
                int lowestScore = Integer.MAX_VALUE;
                int lowestScoreIndex = -1;
                
                for (int i = 0; i < playerNames.size(); i++) {
                    if (playerScores.get(i) < lowestScore) {
                        lowestScore = playerScores.get(i);
                        lowestScoreIndex = i;
                    }
                }
                
                ArrayList<Integer> lowestScoreIndices = new ArrayList<>();
                for (int i = 0; i < playerNames.size(); i++) {
                    if (playerScores.get(i) == lowestScore) {
                        lowestScoreIndices.add(i);
                    }
                }
                
                if (lowestScoreIndices.size() == 1) {
                    System.out.println(playerNames.get(lowestScoreIndex) + " has been eliminated with a score of " + lowestScore);
                    playerNames.remove(lowestScoreIndex);
                    playerScores.remove(lowestScoreIndex);
                } else {
                 
                    int randomLoserIndex = lowestScoreIndices.get(random.nextInt(lowestScoreIndices.size()));
                    System.out.println("There was a tie for lowest score. " + playerNames.get(randomLoserIndex) + 
                                      " eliminated with a score of " + lowestScore);
                    playerNames.remove(randomLoserIndex);
                    playerScores.remove(randomLoserIndex);
                }
            }

            System.out.println("\nCurrent Scores:");
            for (int i = 0; i < playerNames.size(); i++) {
                System.out.println(playerNames.get(i) + ": " + playerScores.get(i));
            }

            round++;
        }

        boolean userStillIn = playerNames.contains(playerName);
        if (!userStillIn) {
            System.out.println("\n You lost: End Game.");
            printFinalScores(playerNames, playerScores);
            scanner.close();
            return;
        }

        System.out.println("\n=== Final rounds between " + playerNames.get(0) + " and " + playerNames.get(1) + " ===");
        
        playerScores.set(0, 0);
        playerScores.set(1, 0);
        
        int attempts = 0;

        while (attempts < 3) {
            attempts++;
            System.out.println("\n=== Final Round " + attempts + " ===");
            
            for (int i = 0; i < 2; i++) {
                String currentPlayer = playerNames.get(i);
                System.out.println("\n" + currentPlayer + "'s turn.");

                int playerChoice;
                if (currentPlayer.equals(playerName)) {
                    while (true) {
                        System.out.print("Enter (R/P/S) or Q to quit: ");
                        String input = scanner.nextLine().trim().toLowerCase();

                        if (input.length() != 1) {
                            System.out.println("Invalid input! Please enter R, P, S, or Q.");
                            continue;
                        }

                        char playerChar = input.charAt(0);

                        if (playerChar == 'q') {
                            System.out.println("\n" + currentPlayer + " chose to quit the game.");
                            printFinalScores(playerNames, playerScores);
                            scanner.close();
                            return;
                        }

                        playerChoice = choice(playerChar);
                        if (playerChoice == -1) {
                            System.out.println("Invalid choice! Please enter R, P, S, or Q.");
                            continue;
                        }
                        break;
                    }
                } else {
                    playerChoice = random.nextInt(3);
                    System.out.println(currentPlayer + " chose: " + rockPaperScissor.Options[playerChoice]);
                }

                int opponentIndex = (i == 0) ? 1 : 0;
                int opponentChoice;
                if (playerNames.get(opponentIndex).equals(computerName) || !playerNames.get(opponentIndex).equals(playerName)) {
                    opponentChoice = random.nextInt(3);
                    System.out.println(playerNames.get(opponentIndex) + " chose: " + rockPaperScissor.Options[opponentChoice]);
                } else {
                    opponentChoice = random.nextInt(3);
                }

                int result = rockPaperScissor.Results[playerChoice][opponentChoice];

                if (result == 1) {
                    playerScores.set(i, playerScores.get(i) + 1);
                    System.out.println(currentPlayer + " wins this round!");
                } else if (result == -1) {
                    playerScores.set(opponentIndex, playerScores.get(opponentIndex) + 1);
                    System.out.println(playerNames.get(opponentIndex) + " wins this round!");
                } else {
                    System.out.println("This round is a draw!");
                }

                System.out.println("\nCurrent Scores:");
                for (int idx = 0; idx < 2; idx++) {
                    System.out.println(playerNames.get(idx) + ": " + playerScores.get(idx));
                }
            }
        }

        if (playerScores.get(0) > playerScores.get(1)) {
            System.out.println("\n" + playerNames.get(0) + " wins the game!");
        } else if (playerScores.get(1) > playerScores.get(0)) {
            System.out.println("\n" + playerNames.get(1) + " wins the game!");
        } else {
            System.out.println("\nThe game is a tie after 3 final rounds!");
        }

        scanner.close();
    }

    private static void printFinalScores(ArrayList<String> playerNames, ArrayList<Integer> playerScores) {
        System.out.println("\nFinal Scores:");
        for (int i = 0; i < playerNames.size(); i++) {
            System.out.println(playerNames.get(i) + ": " + playerScores.get(i));
        }
    }

    private static int choice(char c) {
        switch (c) {
            case 'r': return 0;
            case 'p': return 1;
            case 's': return 2;
            default: return -1;
        }
    }
}
