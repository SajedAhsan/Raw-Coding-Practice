package Java.Snake_Game;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.spec.ECFieldF2m;

public class Server {
    public static void main(String[] args) {
        try
        {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.print("\033[2J");
            System.out.print("\033[?25l");
            new Thread(() ->
            {
                int point = 0;
                while (true) {
                    System.out.print("\033[H");
                    Resource.changePos();
                    Resource.print();
                    try{ Thread.sleep(500); }
                    catch(Exception e){}
                    point++;
                    if(point == 20)
                    {
                        Resource.generatePoint();
                    }
                    if(point == 30)
                    {
                        Resource.omitPoint();
                        point = 0;
                    }
                }
            }).start();
            while (true) {
                char move = (Character)ois.readObject();
                Resource.movement(move);
            }
            

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
