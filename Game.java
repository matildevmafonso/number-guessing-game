import java.util.Random;

public class Game {
    private int targetNumber;
    private int maxAttempts;
    private int currentAttempts;

    public void startNewGame(int difficulty) {
        Random random = new Random();
        switch (difficulty){
            case 1:
                targetNumber = random.nextInt(50);
                maxAttempts = 10;
            case 2:
                targetNumber = random.nextInt(100);
                maxAttempts = 7;
            case 3:
                targetNumber = random.nextInt(200);
                maxAttempts = 7;
        }
        currentAttempts = 0;
    }

    public String checkGuess(int guess){
        currentAttempts++;
        if(guess == targetNumber){
            return "correct";
        } 
        return (guess < targetNumber) ? "higher" : "lower";
    }

    public boolean isGameOver(){
        return currentAttempts >= maxAttempts;
    }

    public int getCurrentAttemps(){
        return currentAttempts;
    }
}