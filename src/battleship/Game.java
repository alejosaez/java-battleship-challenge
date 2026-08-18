package battleship;

import java.util.Scanner;

public class Game {

    private final Scanner scanner = new Scanner(System.in);
    private final GameField field = new GameField();

    public void start() {
        field.print();

        System.out.println("Enter the coordinates of the ship:");

        String startInput = scanner.next();
        String endInput = scanner.next();

        if (!Coordinate.isValid(startInput)
                || !Coordinate.isValid(endInput)) {
            System.out.println("Error!");
            return;
        }

        Coordinate start = new Coordinate(startInput);
        Coordinate end = new Coordinate(endInput);

        if (!isStraightShip(start, end)) {
            System.out.println("Error!");
            return;
        }

        printShipInfo(start, end);
    }

    private boolean isStraightShip(Coordinate start, Coordinate end) {
        return start.getRow() == end.getRow()
                || start.getColumn() == end.getColumn();
    }

    private void printShipInfo(Coordinate start, Coordinate end) {
        int length;

        if (start.getRow() == end.getRow()) {
            length = Math.abs(start.getColumn() - end.getColumn()) + 1;
        } else {
            length = Math.abs(start.getRow() - end.getRow()) + 1;
        }

        System.out.println("Length: " + length);
        System.out.print("Parts:");

        if (start.getRow() == end.getRow()) {
            int step = start.getColumn() <= end.getColumn() ? 1 : -1;

            for (int column = start.getColumn(); ; column += step) {
                System.out.print(
                        " "
                                + (char) ('A' + start.getRow())
                                + (column + 1)
                );

                if (column == end.getColumn()) {
                    break;
                }
            }
        } else {
            int step = start.getRow() <= end.getRow() ? 1 : -1;

            for (int row = start.getRow(); ; row += step) {
                System.out.print(
                        " "
                                + (char) ('A' + row)
                                + (start.getColumn() + 1)
                );

                if (row == end.getRow()) {
                    break;
                }
            }
        }
    }
}