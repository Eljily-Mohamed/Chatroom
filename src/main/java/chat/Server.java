package chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList  <Room> rooms = new ArrayList<Room>();
        
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7777);
        while(1>0){
            System.out.println("ServerSocket awaiting connections...");
            Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + socket + "!");
            
             if(!rooms.isEmpty()){
                 System.out.println("les romms available");
                 rooms.forEach((e) -> System.out.println(e.getName())); 
             }
             else{
                 System.out.println("Aucune room exsite ");
             }

            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            // read the list of messages from the socket
            List<Room> listOfRooms = (List<Room>) objectInputStream.readObject();
            System.out.println("Received [" + listOfRooms.size() + "] messages from: " + socket);

            rooms.addAll(listOfRooms);
            // print out the text of every message
            System.out.println("All messages:");
            listOfRooms.forEach((msg)-> System.out.println(msg.getName()));
            System.out.println("Closing sockets.");       
    
        }
    }
}