import java.io.*;
import javax.swing.*;

public class LeaderBoard {
    private static final String FILENAME = "leaderboard.txt";
    private static final String TITLE = "Tic Tac Toe Leaderboard";

    // This is my method that creates a file and writes the number of wins each player has to leaderboard.txt
    public static void updateLeaderboard(Player player1, Player player2) {
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
                StringBuilder leaderboardText = new StringBuilder();
                String line;
                boolean player1Found = false;
                boolean player2Found = false;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) { // Check if the line contains the ":" delimiter
                        String playerName = parts[0];
                        int wins = Integer.parseInt(parts[1].trim().split(" ")[0]); // Parse the integer value
                        if (playerName.equals(player1.getName())) {
                            wins += player1.getWins();
                            leaderboardText.append(playerName).append(": ").append(wins).append(" win").append(wins == 1 ? "" : "s").append("\n");
                            player1Found = true;
                        } else if (playerName.equals(player2.getName())) {
                            wins += player2.getWins();
                            leaderboardText.append(playerName).append(": ").append(wins).append(" win").append(wins == 1 ? "" : "s").append("\n");
                            player2Found = true;
                        } else {
                            leaderboardText.append(line).append("\n");
                        }
                    }
                }
                if (!player1Found) {
                    leaderboardText.append(player1.getName()).append(": ").append(player1.getWins()).append(" win").append(player1.getWins() == 1 ? "" : "s").append("\n");
                }
                if (!player2Found) {
                    leaderboardText.append(player2.getName()).append(": ").append(player2.getWins()).append(" win").append(player2.getWins() == 1 ? "" : "s").append("\n");
                }

                // Write the updated leaderboard back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
                    writer.write(TITLE);
                    writer.newLine();
                    writer.write(leaderboardText.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void displayLeaderboard() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            StringBuilder leaderboardText = new StringBuilder();
            leaderboardText.append("Leaderboard:\n");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) { // Check if the line contains the ":" delimiter
                    leaderboardText.append(parts[0]).append(": ").append(parts[1]).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, leaderboardText.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}