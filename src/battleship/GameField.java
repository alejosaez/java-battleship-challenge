package battleship;

public class GameField {

    private static final int SIZE = 10;

    private final char[][] field = new char[SIZE][SIZE];

    public GameField() {
        fill();
    }

    private void fill() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                field[row][col] = '~';
            }
        }
    }

    public void print() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (int row = 0; row < SIZE; row++) {
            System.out.print((char) ('A' + row));

            for (int col = 0; col < SIZE; col++) {
                System.out.print(" " + field[row][col]);
            }

            System.out.println();
        }

        System.out.println();
    }

    public boolean isTooClose(
            int minRow,
            int maxRow,
            int minCol,
            int maxCol
    ) {
        int fromRow = Math.max(0, minRow - 1);
        int toRow = Math.min(SIZE - 1, maxRow + 1);

        int fromCol = Math.max(0, minCol - 1);
        int toCol = Math.min(SIZE - 1, maxCol + 1);

        for (int row = fromRow; row <= toRow; row++) {
            for (int col = fromCol; col <= toCol; col++) {
                if (field[row][col] == 'O') {
                    return true;
                }
            }
        }

        return false;
    }

    public void placeShip(
            int minRow,
            int maxRow,
            int minCol,
            int maxCol
    ) {
        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                field[row][col] = 'O';
            }
        }
    }

    public boolean shoot(int row, int col) {

        if (field[row][col] == 'O') {
            field[row][col] = 'X';
            return true;
        }

        field[row][col] = 'M';
        return false;
    }
}