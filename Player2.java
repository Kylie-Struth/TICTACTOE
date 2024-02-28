class Player2 extends AbstractPlayer implements Player {
    private int wins;
    private GameBoard board;

    public Player2(String name, char symbol, GameBoard board) {
        super(name, symbol);
        this.board = board;
        this.wins = 0;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public void incrementWins() {
        this.wins++;
        Player player1 = new Player1("Player 1", 'X', this.board);
        LeaderBoard.updateLeaderboard(player1, this);
    }
}