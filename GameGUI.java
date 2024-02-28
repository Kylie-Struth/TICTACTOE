import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameGUI {
    private GameBoard board;
    private GameLogic gameLogic;
    private JFrame frame;
    private JButton[][] buttons;
    private JPanel boardPanel;
    private JLabel statusLabel;
    private static final String WINNER_SOUND_PATH = "sounds/winner.aiff";
    private static final String INVALID_SOUND_PATH = "sounds/invalid.wav";

    public GameGUI(GameBoard board, GameLogic gameLogic) {
        this.board = board;
        this.gameLogic = gameLogic;
    }

    // This is my Overloaded constructor
    public GameGUI(GameBoard board, GameLogic gameLogic, JFrame frame) {
        this.board = board;
        this.gameLogic = gameLogic;
        this.frame = frame;
    }

    // This is my Layout Manager with a JFrame.
    public void createAndShowGUI() {
        if (frame == null) {
            frame = new JFrame("Tic Tac Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            createMenuBar();
            createStatusLabel();
            boardPanel = new JPanel(new GridLayout(3, 3));
            createButtons();
            frame.add(boardPanel, BorderLayout.CENTER);
            frame.add(statusLabel, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
        }

        frame.setVisible(true);
        updateStatusLabel();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Options");
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem leaderboardMenuItem = new JMenuItem("Leaderboard");

        newGameMenuItem.addActionListener(e -> resetGame());
        resetMenuItem.addActionListener(e -> resetGame());
        exitMenuItem.addActionListener(e -> System.exit(0));
        leaderboardMenuItem.addActionListener(e -> LeaderBoard.displayLeaderboard());

        gameMenu.add(newGameMenuItem);
        gameMenu.add(resetMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(leaderboardMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);
        menuBar.add(gameMenu);

        frame.setJMenuBar(menuBar);
    }

    //This is ny JLabel element
    private void createStatusLabel() {
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

   //This is ny JButton element
    private void createButtons() {
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("");
                button.setPreferredSize(new Dimension(100, 100));
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        // This is my implementation of Multithreading
        public void actionPerformed(ActionEvent e) {
            char[][] gameBoard = board.getBoard();
            if (gameBoard[row][col] == ' ') {
                gameBoard[row][col] = gameLogic.getCurrentPlayer().getSymbol();
                updateButton(row, col);

                new Thread(() -> {
                    if (gameLogic.isGameOver()) {
                        endGame();
                    } else {
                        gameLogic.switchPlayer();
                        updateStatusLabel();
                    }
                }).start();
            } else {
                playSound(INVALID_SOUND_PATH);
                //This is ny JOptionPane element
                JOptionPane.showMessageDialog(frame, "Invalid move! Cell already occupied.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
                // This is my method that throws the custom exception and is caught and handled without the game crashing
                throw new InvalidMoveException("Invalid move! Cell already occupied.");
            }
        }
    }

    private void updateButton(int row, int col) {
        buttons[row][col].setText(String.valueOf(gameLogic.getCurrentPlayer().getSymbol()));
    }

    private void resetGame() {
        board.reset();
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText("");
            }
        }
        gameLogic.reset();
        updateStatusLabel();
    }

    private void endGame() {
        Player winner = gameLogic.getWinner();
        if (winner != null) {
            // This is my implementation of an audio
            playSound(WINNER_SOUND_PATH);
            int choice = JOptionPane.showConfirmDialog(frame, winner.getName() + " wins! Play another game?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                board.reset();
                gameLogic.reset();
                updateButtons();
                updateStatusLabel();
            } else {
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            board.reset();
            gameLogic.reset();
            updateButtons();
            updateStatusLabel();
        }
    }

    private void updateButtons() {
        char[][] gameBoard = board.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(gameBoard[i][j]));
            }
        }
    }

    private void updateStatusLabel() {
        statusLabel.setText(gameLogic.getCurrentPlayer().getName() + "'s Turn");
    }

    private void playSound(String soundPath) {
        try {
            File soundFile = new File(soundPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
