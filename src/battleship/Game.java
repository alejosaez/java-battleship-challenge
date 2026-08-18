package battleship;

import java.util.Scanner;

public class Game {

    private final Scanner scanner = new Scanner(System.in);
    private final GameField field = new GameField();

    private final Ship[] ships = {
            new Ship("Aircraft Carrier", 5),
            new Ship("Battleship", 4),
            new Ship("Submarine", 3),
            new Ship("Cruiser", 3),
            new Ship("Destroyer", 2)
    };

    public void start() {
        field.print(false);

        for (Ship ship : ships) {
            placeShip(ship);
        }

        System.out.println("The game starts!");
        System.out.println();

        field.print(true);

        takeShot();
    }

    private void placeShip(Ship ship) {
        System.out.println(
                "Enter the coordinates of the "
                        + ship.getName()
                        + " ("
                        + ship.getLength()
                        + " cells):"
        );

        while (true) {
            String startInput = scanner.next();
            String endInput = scanner.next();

            if (!Coordinate.isValid(startInput)
                    || !Coordinate.isValid(endInput)) {

                System.out.println(
                        "Error! Wrong ship location! Try again:"
                );
                continue;
            }

            Coordinate start = new Coordinate(startInput);
            Coordinate end = new Coordinate(endInput);

            if (!isStraightShip(start, end)) {
                System.out.println(
                        "Error! Wrong ship location! Try again:"
                );
                continue;
            }

            int length = calculateLength(start, end);

            if (length != ship.getLength()) {
                System.out.println(
                        "Error! Wrong length of the "
                                + ship.getName()
                                + "! Try again:"
                );
                continue;
            }

            int minRow = Math.min(start.getRow(), end.getRow());
            int maxRow = Math.max(start.getRow(), end.getRow());

            int minCol = Math.min(start.getColumn(), end.getColumn());
            int maxCol = Math.max(start.getColumn(), end.getColumn());

            if (field.isTooClose(
                    minRow,
                    maxRow,
                    minCol,
                    maxCol
            )) {
                System.out.println(
                        "Error! You placed it too close to another one. Try again:"
                );
                continue;
            }

            field.placeShip(
                    minRow,
                    maxRow,
                    minCol,
                    maxCol
            );

            field.print(false);
            break;
        }
    }

    private void takeShot() {
        System.out.println("Take a shot!");

        while (true) {
            String shotInput = scanner.next();

            if (!Coordinate.isValid(shotInput)) {
                System.out.println(
                        "Error! You entered wrong coordinates! Try again:"
                );
                continue;
            }

            Coordinate shot = new Coordinate(shotInput);

            boolean hit = field.shoot(
                    shot.getRow(),
                    shot.getColumn()
            );

            System.out.println();

            // tablero con niebla
            field.print(true);

            if (hit) {
                System.out.println("You hit a ship!");
            } else {
                System.out.println("You missed!");
            }

            System.out.println();

            // tablero real
            field.print(false);

            break;
        }
    }

    private boolean isStraightShip(
            Coordinate start,
            Coordinate end
    ) {
        return start.getRow() == end.getRow()
                || start.getColumn() == end.getColumn();
    }

    private int calculateLength(
            Coordinate start,
            Coordinate end
    ) {
        if (start.getRow() == end.getRow()) {
            return Math.abs(
                    start.getColumn() - end.getColumn()
            ) + 1;
        }

        return Math.abs(
                start.getRow() - end.getRow()
        ) + 1;
    }
}