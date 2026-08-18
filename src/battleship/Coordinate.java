package battleship;

public class Coordinate {

    private final int row;
    private final int column;

    public Coordinate(String coordinate) {
        this.row = coordinate.charAt(0) - 'A';
        this.column = Integer.parseInt(coordinate.substring(1)) - 1;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static boolean isValid(String coordinate) {
        if (coordinate == null
                || coordinate.length() < 2
                || coordinate.length() > 3) {
            return false;
        }

        char row = coordinate.charAt(0);

        if (row < 'A' || row > 'J') {
            return false;
        }

        String columnText = coordinate.substring(1);

        if (!columnText.matches("\\d+")) {
            return false;
        }

        int column = Integer.parseInt(columnText);

        return column >= 1 && column <= 10;
    }
}