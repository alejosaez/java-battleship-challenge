package battleship;

public class Ship {

    private final String name;
    private final int length;
    private int remainingParts;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.remainingParts = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public void hit() {
        if (remainingParts > 0) {
            remainingParts--;
        }
    }

    public boolean isSunk() {
        return remainingParts == 0;
    }
}