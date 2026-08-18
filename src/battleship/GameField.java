package battleship;

public class GameField {

    private static final int SIZE = 10;

    public void print() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (int row = 0; row < SIZE; row++) {
            System.out.print((char) ('A' + row));

            for (int col = 0; col < SIZE; col++) {
                System.out.print(" ~");
            }

            System.out.println();
        }
    }
}