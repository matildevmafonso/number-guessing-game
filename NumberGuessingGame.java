// Arquivo: NumberGuessingGame.java
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Game game = new Game();
    private static final HighScoreManager highScoreManager = new HighScoreManager();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getIntInput("Escolha uma opção: ");
            
            switch (choice) {
                case 1:
                    startGame();
                case 2:
                    displayHighScores();
                case 3:
                    System.out.println("Até logo!");
                    return;
                default: 
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Guessing Game ===");
        System.out.println("1. Play");
        System.out.println("2. See High Scores");
        System.out.println("3. Quit");
    }

    private static void startGame() {
        System.out.println("\nChoose the difficulty level:");
        System.out.println("1. Easy (1-50)");
        System.out.println("2. Medium (1-100)");
        System.out.println("3. Hard (1-200)");
        
        int difficulty = getIntInput("Dificulty: ");
        if (difficulty < 1 || difficulty > 3) {
            System.out.println("Invalid difficulty!");
            return;
        }

        game.startNewGame(difficulty);
        playGame();
    }

    private static void playGame() {
        System.out.println("\nThe game has begun! Try to guess the number.");

        while (!game.isGameOver()) {
            int guess = getIntInput("Guess: ");
            String result = game.checkGuess(guess);

            if (result.equals("correct")) {
                handleWin();
                return;
            }
            System.out.println("The number is " + result + " than " + guess + "!");
        }

        System.out.println("\nGame Over! No more attempts available.");
    }

    private static void handleWin() {
        int attempts = game.getCurrentAttemps();
        System.out.printf("\nCongrats! You finished in %d attempts!%n", attempts);

        System.out.print("Type your name for the high score: ");
        String name = scanner.next();
        
        try {
            highScoreManager.saveHighScore(name, attempts);
        } catch (IOException e) {
            System.out.println("Error while saving high score: " + e.getMessage());
        }
    }

    private static void displayHighScores() {
        try {
            List<String> scores = highScoreManager.loadHighScores();
            System.out.println("\n=== High Scores ===");
            
            if (scores.isEmpty()) {
                System.out.println("No high scores registred!");
                return;
            }
            
            for (int i = 0; i < scores.size(); i++) {
                String[] parts = scores.get(i).split(":");
                System.out.printf("%d. %s - %s attempts%n", i+1, parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Error while loading scores: " + e.getMessage());
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please insert a valid number!");
                scanner.nextLine(); // Limpa o buffer
            }
        }
    }
}