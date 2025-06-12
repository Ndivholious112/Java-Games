import java.util.Random;
import java.util.Scanner;

public class optionsRPS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int playerScore = 0;
        int computerScore = 0;
        int round = 1;
        int maxAttempts = 14;

        System.out.println("Welcome to Rock-Paper-Scissors game!");
        System.out.println("Enter R for Rock, P for Paper, S for Scissors, or Q to Quit.");

        while (round <= maxAttempts) {
            System.out.println("\n===Round " + round + "====");
            System.out.print("Enter (R/P/S) or Q to quit: ");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.length() != 1) {
                System.out.println("Invalid input! Please enter R, P, S, or Q.");
                continue;
            }

            char playerChar = input.charAt(0);

            if (playerChar == 'q') {
                // Quit the game and show final results
                System.out.println("\nFinal Score:");
                System.out.println("You: " + playerScore);
                System.out.println("Computer: " + computerScore);

                if (playerScore > computerScore) {
                    System.out.println("You won the game!");
                } else if (computerScore > playerScore) {
                    System.out.println("Computer won the game!");
                } else {
                    System.out.println("The game is a draw!");
                }
                break;
            }

            int playerChoice = choice(playerChar);
            if (playerChoice == -1) {
                System.out.println("Invalid choice! Please enter R, P, S, or Q.");
                continue;
            }

            int computerChoice = random.nextInt(3);

            System.out.println("You chose: " + rockPaperScissor.Options[playerChoice]);
            System.out.println("Computer chose: " + rockPaperScissor.Options[computerChoice]);

            int result = rockPaperScissor.Results[playerChoice][computerChoice];

            if (result == 1) {
                playerScore++;
                System.out.println("You win this round!");
            } else if (result == -1) {
                computerScore++;
                System.out.println("Computer wins this round!");
            } else {
                System.out.println("This round is a draw!");
            }

            System.out.println("\nCurrent Score:");
            System.out.println("You: " + playerScore);
            System.out.println("Computer: " + computerScore);

            round++;
        }

        if (round > maxAttempts) {
            System.out.println("\nMaximum rounds reached!");
            System.out.println("Final Score:");
            System.out.println("You: " + playerScore);
            System.out.println("Computer: " + computerScore);

            if (playerScore > computerScore) {
                System.out.println("You won the game!");
            } else if (computerScore > playerScore) {
                System.out.println("Computer won the game!");
            } else {
                System.out.println("The game is a draw!");
            }
        }

        scanner.close();
    }

    // Converts character input to integer choice index
    private static int choice(char c) {
        switch (c) {
            case 'r':
                return 0;
            case 'p':
                return 1;
            case 's':
                return 2;
            default:
                return -1;
        }
    }
}
