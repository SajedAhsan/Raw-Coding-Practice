package Java.Tic_Tac_Toe;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static boolean invalid(int x, int y) {
        return x < 0 || y < 0 || x >= 3 || y >= 3;
    }
    public static void main(String[] args) {
        try
        {
            Socket socket = new Socket("localhost",8888);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            int i = 1;
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Move " + i + ": ");
                int x = sc.nextInt();
                int y = sc.nextInt();
                if(invalid(x, y) && Server.grid[x][y] != ' ')
                {
                    System.out.println("Invalid input give me the input again");
                    continue;
                }
                oos.writeObject(x);
                oos.writeObject(y);
                String s = (String)ois.readObject();
                i++;
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
