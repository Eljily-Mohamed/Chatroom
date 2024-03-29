package chat;

//for socket communication
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//for javaFX lib
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Client extends Application {
	   PrintWriter pw ;
       int idRoom;

	   Client (int idRoom){
		    this.idRoom = idRoom;
	   }
	   //on doit ajout le methode main ici on va faire apelle a la methode lunch()
       public static void main(String[] args) {
		    launch(args);
	   }

	   @Override
	   public void start(Stage primaryStage) throws Exception {

				 //*************************************//
	    	    //***********  Affichage  *************//

		 	    // pour Affichge de stage on doit utilise le methode Show
				primaryStage.setTitle("CHAT_SOCKET");
				BorderPane BP = new BorderPane();
					
				HBox hbox = new HBox();
				hbox.setSpacing(10);
				hbox.setStyle("-fx-background-color: #DAF7A6 ;"); 
				hbox.setPadding(new Insets(10,20,20,10));

				ObservableList<String> listmodel =FXCollections.observableArrayList();
				ListView<String> listview = new ListView<String>(listmodel);
				VBox vbox_chat = new VBox();
				vbox_chat.setSpacing(10);
				vbox_chat.setPadding(new Insets(10));
				vbox_chat.getChildren().add(listview);
				BP.setCenter(vbox_chat);
		 
	            Text labelmessage = new Text("Message ");
		        labelmessage.setFont(Font.font("Comic Sans MS"));
		 
				TextField textmessge = new TextField("Your message here "); //cree text filde permet de ajout text
				textmessge.setFont(Font.font("Comic Sans MS"));
				textmessge.setPrefSize(300, 30);
				
				Button buttonEnvoyer = new Button("Envoyer");//creation de notre button
				buttonEnvoyer.setFont(Font.font("Comic Sans MS"));
				
				HBox hbox_message = new HBox();
				hbox_message.setSpacing(10);
				hbox_message.setPadding(new Insets(10,20,20,10));
				hbox_message.getChildren().addAll(labelmessage,textmessge,buttonEnvoyer);
				
				BP.setBottom(hbox_message);			
				BP.setTop(hbox);

				Scene scene = new Scene(BP,500,400);//500-->largere,hauteur
				primaryStage.setScene(scene);
				primaryStage.getIcons().add(new Image(Client.class.getResourceAsStream("icon_chat.jpg")));
				primaryStage.show();
		  
		    	//on va commance cree notre server
			    try {
						Socket socket = new Socket("localhost",1234);
						InputStream inputStream = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(inputStream);
						BufferedReader br = new BufferedReader(isr);
						pw = new PrintWriter(socket.getOutputStream(),true);
						pw.println("1");
						String idRoomString=String.valueOf(idRoom); 
						System.out.println("idRoom" + idRoomString); 
						pw.println(idRoom);
						new Thread(()->{	
					         while(true) {
					             try {
					     				String reponse = br.readLine();
										Platform.runLater(()->{
												listmodel.add(reponse);
										});
									}catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
									}
					    }
				}).start();
			 } catch(IOException e) {
				 e.printStackTrace();
			 }
			 
		//  });

		 //on doit utilse function for checking this stat
		 //logique for button Envoyer 
		 
		 buttonEnvoyer.setOnMouseClicked((evt)->{
              String messge = textmessge.getText();
              System.out.println(""+messge);
              pw.println(messge);
		 });
	}
}