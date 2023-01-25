package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//on doit cree notre server multithread


//class server for 

public class Server extends Thread {
	
	  PrintWriter pw;
	  InputStream is ;
	  InputStreamReader isr ;
	  BufferedReader br ;
	  

	  private List<Room> rooms = new ArrayList<Room>();

	  public static void main(String[] args) {
		new Serveur().start();
	}
	  @Override
	public void run() {
          try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("le systeme est pret pour accepter les connexions");
            Socket socket = ss.accept();
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
    
            Room m = (Room) is.readObject();
           
            System.out.println(m);
    
            os.writeObject(m);
            socket.close(); 
               
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	 }
  }
	  
//end class etablier connexion 
