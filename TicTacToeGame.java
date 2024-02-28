public class TicTacToeGame {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Player player1 = new Player1("Player 1", 'X', board);
        Player player2 = new Player2("Player 2", 'O', board);
        GameLogic gameLogic = new GameLogic(board, player1, player2);

        GameGUI gui = new GameGUI(board, gameLogic);
        gui.createAndShowGUI();
    }
}














