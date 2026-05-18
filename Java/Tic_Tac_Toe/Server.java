package Java.Tic_Tac_Toe;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static char[][] grid;
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
                if (j != 2)
                    System.out.print("|");
            }
            System.out.println();
            if (i != 2)
                System.out.println("-----");
        }
    }
    public static void main(String[] args) {
        Random rand = new Random();
        grid = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = ' ';
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            int count = 0;
            System.out.print("\033[2J");
            System.out.print("\033[?25l");
            while (count < 9) {
                int x = (Integer) ois.readObject();
                int y = (Integer) ois.readObject();
                grid[x][y] = 'O';
                count++;
                System.out.print("\033[H");
                print(grid);
                if(win(grid) != 'a')
                {
                    System.out.println("The winner is O");
                    return;
                }
                if(count >= 9) break;
                Thread.sleep(500);
                do {
                    x = rand.nextInt(3);
                    y = rand.nextInt(3);
                } while (grid[x][y] != ' ');
                grid[x][y] = 'X';
                System.out.print("\033[H");
                print(grid);
                if(win(grid) != 'a')
                {
                    System.out.println("The winner is X");
                    return;
                }
                oos.writeObject("Enter");
            }
            System.out.println("The match is draw");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
