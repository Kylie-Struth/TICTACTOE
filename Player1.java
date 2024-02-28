class Player1 extends AbstractPlayer implements Player {
    private int wins;
    private GameBoard board;

    public Player1(String name, char symbol, GameBoard board) {
        super(name, symbol);
        this.board = board;
        this.wins = 0;
    }

    // This is my overridden method
    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public void incrementWins() {
        this.wins++;
        Player player2 = new Player2("Player 2", 'O', this.board);
        LeaderBoard.updateLeaderboard(this, player2);
    }
}