
package Java.Tic_Tac_Toe;
import java.util.Random;

public class AutomaticTicTacToe {

    static char win(char[][] grid) {
        if (grid[0][0] != ' ' && grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2])
            return grid[0][0];
        if (grid[1][0] != ' ' && grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2])
            return grid[1][0];
        if (grid[2][0] != ' ' && grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2])
            return grid[2][0];

        if (grid[0][0] != ' ' && grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0])
            return grid[0][0];
        if (grid[0][1] != ' ' && grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1])
            return grid[0][1];
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2])
            return grid[0][2];

        if (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
            return grid[0][0];
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
            return grid[0][2];

        return 'a';
    }

    static void print(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j]);
                if (j != 2) System.out.print("|");
            }
            System.out.println();
            if (i != 2) System.out.println("-----");
        }
    }

    static boolean invalid(int x, int y) {
        return x < 0 || y < 0 || x >= 3 || y >= 3;
    }

    public static void main(String[] args) throws InterruptedException {

        Random rand = new Random();

        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = ' ';

        int turn = 0;
        int turnCount = 0;

        // clear once + hide cursor
        System.out.print("\033[2J");
        System.out.print("\033[?25l");

        while (turnCount < 9) {
            System.out.print("\033[H");

            print(grid);

            int x, y;
            do {
                x = rand.nextInt(3);
                y = rand.nextInt(3);
            } while (grid[x][y] != ' ');

            if (turn == 0) {
                grid[x][y] = 'O';
            } else {
                grid[x][y] = 'X';
            }

            turnCount++;

            char w = win(grid);
            if (w != 'a') {
                System.out.print("\033[H");
                print(grid);
                System.out.println("Winner is: " + w);
                 // show cursor again
                System.out.print("\033[?25h");
                return;
            }

            turn ^= 1;

            Thread.sleep(1000);
        }

        System.out.print("\033[H");
        print(grid);
        System.out.println("The match is draw");
        System.out.print("\033[?25h");
    }
}