package battleship;

public class GameField {

    private static final int SIZE = 10;

    private final char[][] field = new char[SIZE][SIZE];
    private final Ship[][] ships = new Ship[SIZE][SIZE];

    private int shipsRemaining = 5;

    public GameField() {
        fill();
    }

    private void fill() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                field[row][col] = '~';
                ships[row][col] = null;
            }
        }
    }

    public void print(boolean fog) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (int row = 0; row < SIZE; row++) {
            System.out.print((char) ('A' + row));

            for (int col = 0; col < SIZE; col++) {
                char cell = field[row][col];

                if (fog && cell == 'O') {
                    cell = '~';
                }

                System.out.print(" " + cell);
            }

            System.out.println();
        }
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
            Ship ship,
            int minRow,
            int maxRow,
            int minCol,
            int maxCol
    ) {
        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                field[row][col] = 'O';
                ships[row][col] = ship;
            }
        }
    }

    public ShotResult shoot(int row, int col) {

        char currentCell = field[row][col];

        if (currentCell == 'O') {

            Ship ship = ships[row][col];

            field[row][col] = 'X';
            ship.hit();

            if (ship.isSunk()) {
                shipsRemaining--;
                return ShotResult.SUNK;
            }

            return ShotResult.HIT;
        }

        if (currentCell == 'X') {
            return ShotResult.HIT;
        }

        if (currentCell == '~') {
            field[row][col] = 'M';
        }

        return ShotResult.MISS;
    }

    public boolean allShipsSunk() {
        return shipsRemaining == 0;
    }
}