package Java.Tic_Tac_Toe;
import java.util.Scanner;

public class StaticTicTacToe {
    static char win(char[][] grid) {
        // row wise check
        if (grid[0][0] != ' ' && grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2])
            return grid[0][2];
        if (grid[1][0] != ' ' && grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2])
            return grid[1][2];
        if (grid[2][0] != ' ' && grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2])
            return grid[2][2];
        // col wise check
        if (grid[0][0] != ' ' && grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0])
            return grid[2][0];
        if (grid[0][1] != ' ' && grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1])
            return grid[2][1];
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2])
            return grid[2][2];
        // diagonal check
        if (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
            return grid[2][2];
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
            return grid[2][0];
        // no win condition
        return 'a';
    }

    static void print(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(j != 2)
                    System.out.print(grid[i][j] + "|");
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
            if (i != 2) {
                for (int k = 0; k < 5; k++) 
                {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    static boolean invalid(int x, int y) {
        return x < 0 || y < 0 || x >= 3 || y >= 3;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
        int turn = 0;
        int turnCount = 0;
        boolean end = false;
        print(grid);
        while (!end && turnCount < 9) {
            int x, y;
            if (turn == 0) {
                System.out.println("Now O's turn,enter position : ");
                x = sc.nextInt();
                y = sc.nextInt();
                if (invalid(x, y) == false && grid[x][y] == ' ') {
                    grid[x][y] = 'O';
                } else {
                    System.out.println("Invalid input give position again");
                    continue;
                }
            } else {
                System.out.println("Now X's turn,enter position : ");
                x = sc.nextInt();
                y = sc.nextInt();
                if (invalid(x, y) == false  && grid[x][y] == ' ') {
                    grid[x][y] = 'X';
                } else {
                    System.out.println("Invalid input,give position again");
                    continue;
                }
            }
            turnCount++;
            print(grid);
            if (win(grid) != 'a') {
                end = true;
                System.out.println("The winner is " + win(grid));
                return;
            }
            turn ^= 1;
        }
        System.out.println("The match is draw");

    }
}
