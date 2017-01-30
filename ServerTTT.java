import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Zaara Tasnim Rifat on 6/3/2016.
 */

class ChildSocket implements Runnable{
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    static String str= "Server has been started successfully!";
    int id;

    public ChildSocket(Socket cs,int id){
        socket = cs;
        this.id = id;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Thread t = new Thread(this,"Server");
        t.start();
        System.out.println("Client ["+ id+ "] has joined successfully!");
    }

    @Override
    public void run() {
           /* in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());*/

        {
            try {
                Object o;
                o = in.readObject();
                String str;
                str = o.toString();
                System.out.println(str);
                out.writeObject(str);
                //out2.writeObject(str);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}


class CreateSocket implements Runnable{
    ServerSocket serverSocket;
    int id=0;
    Socket childSocket1, childSocket2;

    public CreateSocket(ServerSocket ss){
        serverSocket = ss;
        Thread t1 = new Thread(this,"ServerSocket");
        t1.start();
        System.out.println("Waiting for connection");
    }

    @Override
    public void run() {
        //in1 =null, in2=null;
        // out1=null, out2=null;
        //while(true){
        try{
            childSocket1 = serverSocket.accept();
            System.out.println("connected to server");
            id++;
            //new ChildSocket(childSocket1,id);
            ObjectInputStream in1 = new ObjectInputStream(childSocket1.getInputStream());
            ObjectOutputStream out1 = new ObjectOutputStream(childSocket1.getOutputStream());

            childSocket2 = serverSocket.accept();
            System.out.println("connected to server");
            id++;
            ObjectInputStream in2 = new ObjectInputStream(childSocket2.getInputStream());
            ObjectOutputStream out2 = new ObjectOutputStream(childSocket2.getOutputStream());

            //
            Object o;
            o= in1.readObject();
            String name1 = o.toString();
            out2.writeObject(name1);
            o= in2.readObject();
            String name2 = o.toString();
            out1.writeObject(name2);




            //
            while(true)
            {
                try {
                    //Object o;
                    o = in1.readObject();
                    String str;
                    str = o.toString();
                    System.out.println(str);
                    //out1.writeObject(str);
                    out2.writeObject(str);

                    o = in2.readObject();
                    str = o.toString();

                    out1.writeObject(str);
                    //out2.writeObject(str);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }






        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("name reading problem");
        }


    }

    //}
}

public class ServerTTT {

    public static void main(String[] args) {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(11111);
            CreateSocket newSocket = new CreateSocket(serverSocket);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
