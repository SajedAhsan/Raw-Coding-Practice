package Java.Snake_Game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try
        {
            Socket socket = new Socket("localhost",8888);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while(true)
            {
                System.out.println("Enter move :");
                char c = sc.next().charAt(0);
                oos.writeObject(c);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
