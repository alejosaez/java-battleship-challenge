package battleship;

public class Player {

    private final String name;
    private final GameField field;

    public Player(String name) {
        this.name = name;
        this.field = new GameField();
    }

    public String getName() {
        return name;
    }

    public GameField getField() {
        return field;
    }
}