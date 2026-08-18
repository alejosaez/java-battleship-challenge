package battleship;

import java.util.Scanner;

public class Game {

    private final Scanner scanner = new Scanner(System.in);

    private final Player player1 = new Player("Player 1");
    private final Player player2 = new Player("Player 2");

    public void start() {

        setupPlayer(player1);

        passMove();

        setupPlayer(player2);

        passMove();

        playGame();
    }

    private void setupPlayer(Player player) {

        System.out.println(
                player.getName()
                        + ", place your ships on the game field"
        );
        System.out.println();

        player.getField().print(false);
        System.out.println();

        Ship[] ships = createShips();

        for (Ship ship : ships) {
            placeShip(player, ship);
        }
    }

    private Ship[] createShips() {
        return new Ship[]{
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 2)
        };
    }

    private void placeShip(Player player, Ship ship) {

        GameField field = player.getField();

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

            int minRow = Math.min(
                    start.getRow(),
                    end.getRow()
            );

            int maxRow = Math.max(
                    start.getRow(),
                    end.getRow()
            );

            int minCol = Math.min(
                    start.getColumn(),
                    end.getColumn()
            );

            int maxCol = Math.max(
                    start.getColumn(),
                    end.getColumn()
            );

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
                    ship,
                    minRow,
                    maxRow,
                    minCol,
                    maxCol
            );

            System.out.println();
            field.print(false);
            System.out.println();

            break;
        }
    }

    private void playGame() {

        Player currentPlayer = player1;
        Player opponent = player2;

        while (true) {

            // tablero enemigo con niebla
            opponent.getField().print(true);

            System.out.println("---------------------");

            // tablero propio
            currentPlayer.getField().print(false);

            System.out.println();
            System.out.println(
                    currentPlayer.getName()
                            + ", it's your turn:"
            );

            takeShot(opponent);

            if (opponent.getField().allShipsSunk()) {
                break;
            }

            passMove();

            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }
    }

    private void takeShot(Player opponent) {

        GameField enemyField = opponent.getField();

        while (true) {

            String shotInput = scanner.next();

            if (!Coordinate.isValid(shotInput)) {
                System.out.println(
                        "Error! You entered wrong coordinates! Try again:"
                );
                continue;
            }

            Coordinate shot = new Coordinate(shotInput);

            ShotResult result = enemyField.shoot(
                    shot.getRow(),
                    shot.getColumn()
            );

            if (enemyField.allShipsSunk()) {
                System.out.println(
                        "You sank the last ship. You won. Congratulations!"
                );
                return;
            }

            if (result == ShotResult.SUNK) {
                System.out.println("You sank a ship!");
            } else if (result == ShotResult.HIT) {
                System.out.println("You hit a ship!");
            } else {
                System.out.println("You missed!");
            }

            return;
        }
    }

    private void passMove() {

        System.out.println();
        System.out.println(
                "Press Enter and pass the move to another player"
        );

        scanner.nextLine();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        System.out.println();
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
                    start.getColumn()
                            - end.getColumn()
            ) + 1;
        }

        return Math.abs(
                start.getRow()
                        - end.getRow()
        ) + 1;
    }
}