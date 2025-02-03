import java.io.*;
import java.util.*;

public class HighScoreManager {
    private final String highScoresFile = "highscores.txt";

    public void saveHighScore(String name, int score) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(highScoresFile, true))) {
            writer.write(name + ":" + score + "\n");
        }
    }

    public List<String> loadHighScores() throws IOException {
        List<String> scores = new ArrayList<>();
        File file = new File(highScoresFile);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    scores.add(scanner.nextLine());
                }
            }
        }
        scores.sort(Comparator.comparingInt(s -> Integer.parseInt(s.split(":")[1])));
        return scores.subList(0, Math.min(scores.size(), 10)); // Top 10
    }
}
