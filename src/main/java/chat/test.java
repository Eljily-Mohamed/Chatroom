package chat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) throws IOException {
       
        List<Room> messages = new ArrayList<>();
        messages.add(new Room("Hello from the other side!" , "public"));
        messages.add(new Room("How are you doing?" , "private"));
        messages.add(new Room("What time is it?" , "private"));
        messages.add(new Room("Hi hi hi hi." , "private"));

        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(messages);
        //get  the room setings donc on doit cree une list de room dans notre servers 
        
        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}
