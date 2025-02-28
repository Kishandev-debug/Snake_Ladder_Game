import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakeLadderGame {
    private static final int WIN_POSITION = 100;
    private static Map<Integer, Integer> snakes;
    private static Map<Integer, Integer> ladders;

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    private static void initializeBoard() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();

        // Defining Snakes
        snakes.put(99, 7);
        snakes.put(92, 35);
        snakes.put(78, 41);
        snakes.put(50, 5);
        snakes.put(26, 10);

        // Defining Ladders
        ladders.put(3, 22);
        ladders.put(6, 25);
        ladders.put(11, 40);
        ladders.put(20, 59);
        ladders.put(34, 79);
        ladders.put(70, 90);
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int player1Position = 0;
        int player2Position = 0;
        boolean isPlayer1Turn = true;

        System.out.println("Welcome to Snake and Ladder Game!");
        System.out.println("Two players will take turns rolling the dice.");

        while (player1Position < WIN_POSITION && player2Position < WIN_POSITION) {
            System.out.println("\nPress Enter to roll the dice...");
            scanner.nextLine();
            int diceRoll = random.nextInt(6) + 1;
            System.out.println("Rolled: " + diceRoll);

            if (isPlayer1Turn) {
                player1Position = movePlayer(player1Position, diceRoll);
                System.out.println("Player 1 moves to: " + player1Position);
                if (player1Position == WIN_POSITION) {
                    System.out.println("🎉 Player 1 Wins! 🎉");
                    break;
                }
            } else {
                player2Position = movePlayer(player2Position, diceRoll);
                System.out.println("Player 2 moves to: " + player2Position);
                if (player2Position == WIN_POSITION) {
                    System.out.println("🎉 Player 2 Wins! 🎉");
                    break;
                }
            }
            isPlayer1Turn = !isPlayer1Turn; // Switch turn
        }
        scanner.close();
    }

    private static int movePlayer(int currentPosition, int diceRoll) {
        int newPosition = currentPosition + diceRoll;

        if (newPosition > WIN_POSITION) {
            return currentPosition; // Stay in the same position if it exceeds 100
        }

        if (snakes.containsKey(newPosition)) {
            System.out.println("Oh no! Bitten by a snake 🐍");
            return snakes.get(newPosition);
        }

        if (ladders.containsKey(newPosition)) {
            System.out.println("Great! Climbing a ladder 🔼");
            return ladders.get(newPosition);
        }

        return newPosition;
    }
}
