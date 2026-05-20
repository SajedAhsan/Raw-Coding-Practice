package Java.Tcp_BroadCast;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private static List<ObjectOutputStream> clients =
        Collections.synchronizedList(new ArrayList<>());
    public static void main(String[] args) {
        try
        {
            ServerSocket serverSocket = new ServerSocket(8888);
            while(true)
            {
                Socket socket = serverSocket.accept();
                System.out.println("New Client Connected!!!!!!!");
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                clients.add(oos);
                new Thread(()->{
                    while (true) {
                        try {
                            String msg = (String)ois.readObject();
                            synchronized(clients)
                            {
                                for(ObjectOutputStream out : clients)
                                {
                                    if(out == oos) continue;
                                    out.writeObject(msg);
                                    out.flush();
                                }
                            }
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
