// This is my parent class that extends two child classes
abstract class AbstractPlayer {
    private String name;
    private char symbol;

    public AbstractPlayer(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}