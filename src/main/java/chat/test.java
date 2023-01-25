package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class test {

    PrintWriter pw ;
    InputStream inputStream ;
    InputStreamReader isr;           
    BufferedReader br;
    
    public static void main(String[] args) {

        try {
            System.out.println("welcome client");
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Room room = new Room("room1","public");
            os.writeObject(room);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        //out.writeObject(room); 
    }
  
}
