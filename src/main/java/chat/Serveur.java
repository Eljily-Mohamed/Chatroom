package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//on doit cree notre server multithread 
public class Serveur extends Thread {

	  PrintWriter pw;
	  InputStream is ;
	  InputStreamReader isr ;
	  BufferedReader br ;
      //end varaible strem
      private int action ;
	  //for action 1
	  private boolean isActive = true;
	  //private int nmbr_Clients= 0 ;
	  private static int nmbr_Roomlogin;
	  //private List<String> nomUsres = new ArrayList<String>();
	  //private List<Conversation> clients = new ArrayList<Conversation>();
	  //end
	  //for action 2 
	  ArrayList  <Room> rooms = new ArrayList<Room>();
      //for login in room 
	  private int idRoom;

	  public static void main(String[] args) {
		new Serveur().start();
	}

	  @Override
	public void run() {
          try {
			ServerSocket serveurSocket = new ServerSocket(1234);
			while(isActive) {
				Socket socket = serveurSocket.accept();
                is = socket.getInputStream();
			    isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				try{
					action = Integer.parseInt(br.readLine());
					// System.out.println(action); // output = 25
				}
				catch (NumberFormatException ex){
					ex.printStackTrace();
				}
				if( action == 1){
					try{
						nmbr_Roomlogin = Integer.parseInt(br.readLine());
						System.out.println("idRoom" + nmbr_Roomlogin); // 
					}
					catch (NumberFormatException ex){
						ex.printStackTrace();
					}
					System.out.println(); 
					Conversation conversation = new Conversation(socket);

					conversation.start();
				}
				if(action == 2){
					System.out.println("echo hello world");
                    CreationRoom createdRoom = new CreationRoom(socket);
					createdRoom.start();
				}
				if(action  == 3 ){
					 System.out.println("fetch Data from server ") ;
					 // get the input stream from the connected socket
					 OutputStream outputStream = socket.getOutputStream();
					 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
					 System.out.println("Sending Rooms available to client");
					 objectOutputStream.writeObject(rooms);
				}
				if(action == 4){
					System.out.println("login in the room available to client ");
					try{
						idRoom = Integer.parseInt(br.readLine());
						System.out.println(idRoom); // output = 25
					}
					catch (NumberFormatException ex){
						ex.printStackTrace();
					}

					rooms.forEach((e) -> {
						if(e.getIdroom() == idRoom ){
							try {
							  OutputStream outputStream = socket.getOutputStream();
							  ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
							  System.out.println("Sending Room get by id  to client");
							  objectOutputStream.writeObject(e);					
							} catch (Exception err) {
								System.out.println("Error " +err);
							}
			
						}
					});

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	
//////////////////class conversation 
	  
	  static class Conversation extends Thread{
		private Socket sc;
		private int nmbr_Room;
		private static ArrayList<Conversation> clients = new ArrayList<>();
	
		private synchronized void add() {
			this.clients.add(this);
		}
	
		private synchronized void remove() {
			this.clients.remove(this);
		}
	
		public Conversation(Socket nsock) {
			sc = nsock;
			this.nmbr_Room = nmbr_Roomlogin;
			add();
			//add this to the arraylist, one instance/thread for every connection.
		}
	
		private synchronized void dispatchMessage(String message) {
			//send one message 
	
			try {
				OutputStream out = this.sc.getOutputStream();
				PrintWriter pw = new PrintWriter(out);
				//now send the message
				System.out.printf("dispatchMessage: Sending message '%s'\n", message); //visualize what's happening in the console
			   // pw.print(message); //deliberately send no newline yet
				pw.println(message);
				pw.flush();
	
			} catch (IOException e) {
				System.out.println("dispatchMessage caught exception :(");
				e.printStackTrace();
			}
	
		}
	
		private synchronized void dispatch(String message) {
			for (Conversation st : clients) {
				if(st == this || this.nmbr_Room != st.nmbr_Room ) continue; //statics :/
				st.dispatchMessage(message);
				System.out.println("id conversation : " + st.nmbr_Room);
			}
			System.out.println(" id room de conversation now = "+this.nmbr_Room);
		}
	
		@Override
		public void run() {
			//listen for input, if received dispatch it
			InputStream inst;
			OutputStream outst;
			PrintWriter pw;
	
			try {
				//instantiate objects for reading and writing to the socket
				inst = sc.getInputStream();
				outst = sc.getOutputStream();
				pw = new PrintWriter(outst);
				Scanner in = new Scanner(inst);
				String inMessage;
	
				while (true) {
					inMessage = in.nextLine(); //maybe replace this with something less newline-dependant.
					//the above call *should* block until input is actually received.
					//that's rather fortunate for our CPU usage.
					System.out.printf("run(): received: '%s'.\n", inMessage);
					dispatch(inMessage);
				}
	
			} catch (IOException e) {
			}
	
		}
	}
	 //end class Conversation 

	/**
	 * InnerServeur
	 */


	class CreationRoom extends Thread{
         
		private Socket socketRoomCr ;
     
		CreationRoom (Socket socketCLinet){
           this.socketRoomCr = socketCLinet;
		}
		
		@Override
		public void run() {
		   try {
			 // get the input stream from the connected socket
			 InputStream inputStream = socketRoomCr.getInputStream();
			 // create a DataInputStream so we can read data from it.
			 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
             Room r = (Room) objectInputStream.readObject();

			 System.out.println("Received [" + r + "] infos from: " + socketRoomCr);
			 r.setIdroom(rooms.size());
			 rooms.add(r);
			 // print the room available for login 
			 if(!rooms.isEmpty()){
				System.out.println("les romms available");
				rooms.forEach((e) -> {
					System.out.println(e.getName());
					System.out.println(e.getKey());
					System.out.println(e.getStatu());
					System.out.println(e.getIdroom());
				}); 
			}
			else{
				System.out.println("Aucune room exsite ");
			}

		   } catch (Exception e) {
			   System.out.println(e);
		   }
		}

	 }

	//end class for creation de Room 
    //start class for fetching rooms and envoi tous client 
	
}
