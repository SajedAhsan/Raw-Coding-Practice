package Java.Tcp_BroadCast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Socket socket = new Socket("localhost", 8888);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server!");
            // Thread for sending messages
            new Thread(() -> {

                try {
                    while (true) {
                        String msg = sc.nextLine();
                        oos.writeObject(msg);
                        oos.flush();
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected from server");
                }

            }).start();

            // Thread for receiving messages
            new Thread(() -> {
                try {
                    while (true) {
                        String msg = (String) ois.readObject();
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    System.out.println("Server disconnected");
                }
            }).start();
        } catch (Exception e) {
            System.out.println("Cannot connect to server");
        }
    }
}