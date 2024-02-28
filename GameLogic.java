public class GameLogic {
    private GameBoard board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public GameLogic(GameBoard board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Start with player 1
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean isGameOver() {
        if (board.checkWin(player1.getSymbol())) {
            player1.incrementWins();
            return true;
        } else if (board.checkWin(player2.getSymbol())) {
            player2.incrementWins();
            return true;
        } else if (board.isFull()) {
            return true; // Draw
        } else {
            return false;
        }
    }

    public Player getWinner() {
        if (board.checkWin(player1.getSymbol())) {
            return player1;
        } else if (board.checkWin(player2.getSymbol())) {
            return player2;
        } else {
            return null;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void reset() {
        board.reset();
        currentPlayer = player1; // Reset to player 1
    }
}
