# Tic Tac Toe Game
## Description
This is a simple Tic Tac Toe game implemented in Java with a Swing GUI. The game allows two human players to take turns marking spaces on a 3x3 grid to try and get three marks in a row, column, or diagonal. The game includes a leaderboard feature that tracks the number of wins for each player.

## How to Play
**Starting the Game:** Run the TicTacToeGame class to start the game.

**Game Board:** The game board consists of a 3x3 grid of cells where players can place their marks (X or O).

**Taking Turns:** Players take turns clicking on empty cells on the game board to place their mark. Player 1 marks with 'X', and Player 2 marks with 'O'.

**Winning the Game:** The game ends when one player successfully places three marks in a row, column, or diagonal.

**Draw:** If all cells are filled and no player has achieved three marks in a row, the game ends in a draw.

**Restarting the Game:** If a player wins, a dialog will appear asking if they want to play again. Selecting "Yes" will reset the game board and start a new game.

**Viewing the Leaderboard:** To view the leaderboard, click on the "Options" menu in the game window and select "Leaderboard". The leaderboard displays the number of wins for each player.

## Features
**Swing GUI:** The game features a user-friendly graphical interface for gameplay.

**Sound Effects:** Win and invalid move events are accompanied by sound effects to enhance the gaming experience.

**Leaderboard:** The game includes a leaderboard feature that tracks and displays the number of wins for each player.

**Custom Exception Handling:** The game handles invalid moves with a custom exception (InvalidMoveException) to ensure smooth gameplay.

**File I/O:** The game uses file I/O to save and update the leaderboard data.

**Layout Managers:** Layout managers are used to organize components in the GUI for optimal display.

**Multithreading:** Multithreading is implemented for game flow to prevent blocking the GUI thread and ensure responsiveness.
