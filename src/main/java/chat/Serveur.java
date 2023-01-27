package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
      private int action;
	  //for action 1
	  private boolean isActive = true;
	  private int nmbr_Clients= 0 ;
	  private List<String> nomUsres = new ArrayList<String>();
	  private List<Conversation> clients = new ArrayList<Conversation>();
	  //end
	  //for action 2 


	  
	  public static void main(String[] args) {
		new Serveur().start();
	}

	  @Override
	public void run() {
          try {
			ServerSocket serveurSocket = new ServerSocket(1234);
			while(isActive) {
				Socket socket = serveurSocket.accept();

				if(action == 1){
					++nmbr_Clients;
					Conversation conversation = new Conversation(socket,nmbr_Clients);
					clients.add(conversation);
					conversation.start();
				}
				if(action == 2){
					
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

	  
}
