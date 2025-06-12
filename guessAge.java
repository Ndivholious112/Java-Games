import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class guessAge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer[] ages = Arrays.stream(ageOption.ages).boxed().toArray(Integer[]::new);
        
        //int[] ages = ageOption.ages;
        int correctAge = ageOption.correctAge;
        int maxAttempts = 3;
        boolean guessedCorrectly = false;

        System.out.println("Guess the correct Age");

        while (!guessedCorrectly) {
            printAges(ages);
            int attempts = 0;
        
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Attempt " + (attempts + 1) + " of " + maxAttempts + ". Enter your guess: ");
                if (scanner.hasNextInt()) {
                    int guess = scanner.nextInt();
                    attempts++;
        
                    if (guess == correctAge) {
                        System.out.println("Congratulations! You guessed the correct age.");
                        guessedCorrectly = true;
                    } else {
                        System.out.println("Please Try Again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }
        
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all attempts. The correct age was: " + correctAge);
        
                List<Integer> ageList = Arrays.asList(ages);
                Collections.shuffle(ageList);
                ages = ageList.toArray(new Integer[0]);
                correctAge = ages[0];
        
                System.out.println("\n New round!");
            }
        }

        scanner.close();
    }

    private static void printAges(Integer[] ages) {
        for (int age : ages) {
            System.out.print(age + " ");
        }
        System.out.println();
    }
}

