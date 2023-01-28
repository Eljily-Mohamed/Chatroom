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
	  private int nmbr_Clients= 0 ;
	  private List<String> nomUsres = new ArrayList<String>();
	  private List<Conversation> clients = new ArrayList<Conversation>();
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
					System.out.println(action); // output = 25
				}
				catch (NumberFormatException ex){
					ex.printStackTrace();
				}
				if( action == 1){
					++nmbr_Clients;
					Conversation conversation = new Conversation(socket,nmbr_Clients);
					clients.add(conversation);
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
	  
	 class Conversation extends Thread{
		 protected Socket socket_client ;
		 protected int numero_client;
		 
		 //on doit ajoute tab qui contient le noms de users
		 public Conversation(Socket socket_client , int numero_client) {
			this.socket_client = socket_client;
			this.numero_client = numero_client;
		}
		 
		//public void brodcast_message(String message,Socket socket, String nomUser)
		 public void brodcast_message(String message,Socket socket){
			
		//for(String nomClient:nomUsres) {
			 for (Conversation client:clients) {
				 try {
					  //if (nomClient.equals(nomUser)) {
					    if(client.socket_client != socket) {
					    	 pw = new PrintWriter(client.socket_client.getOutputStream(),true);
							 pw.println(message); 
							   break;
					    } 
//					    else {
//					    	 pw = new PrintWriter(client.socket_client.getOutputStream(),true);
//							 pw.println(message); 
//					    }
					 
					  //}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			  //}
			} 
		
		//messgae vers tous monde 
		
		}
		//end methode brodcast vers tous les monde 

		  @Override
			public void run() {
				try {
					is = socket_client.getInputStream();
			        isr = new InputStreamReader(is);
				    br = new BufferedReader(isr);
					
					pw = new PrintWriter(socket_client.getOutputStream(),true);
					String ipClinet = socket_client.getRemoteSocketAddress().toString();
					pw.println("Bien venue , vous etes le client"+ipClinet);
					System.out.println("le connexion bien etablie a client :"+ipClinet);
					//pw.println("Ajout nom ici : ");
					//nomUsres.add(br.readLine());
					
					while(true) {
						String req=br.readLine();
						System.out.println(req);
						String req_Reponse[] = req.split(":");
						System.out.println(req);
						if(req_Reponse.length == 2) {
							String nomClient = req_Reponse[0];
							String message = req_Reponse[1];
							 //brodcast_message(message,socket_client,nomClient);	
							   brodcast_message(message,socket_client);
						}
						else {
							 //brodcast_message(req,socket_client,"unll");
							   brodcast_message(req,socket_client);
						}
					}
	          				
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//end methode run  for class COnversation 
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
            Room  r = (Room) objectInputStream.readObject();

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
