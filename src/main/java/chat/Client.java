package chat;

//for socket communication
import java.io.BufferedInputStream;
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
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Client extends Application {
	PrintWriter pw ;
	
	//on doit ajout le methode main ici on va faire apelle a la methode lunch()
	
     public static void main(String[] args) {
		  launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// pour Affichge de stage on doit utilise le methode Show
		
		 primaryStage.setTitle("CHAT_SOCKET");
		 BorderPane BP = new BorderPane();
		
		 
		 Button buttonChoixService = new Button();
		 //     chate
		 //     file transfere 
		 //     Game Hangman 
		 
		 
		 Text labelhost = new Text("Host: ");
		 labelhost.setFont(Font.font("Comic Sans MS"));
		 TextField textHost = new TextField("localhost"); //cree text filde permet de ajout text
		 textHost.setFont(Font.font("Comic Sans MS"));
		 Text labelPort = new Text("Port");
		 labelPort.setFont(Font.font("Comic Sans MS"));
		 TextField textPort = new TextField("1234"); //cree text filde permet de ajout text
		 textPort.setFont(Font.font("Comic Sans MS"));
		 Button buttonconnexion = new Button("Conx");//creation de notre button
		 buttonconnexion.setFont(Font.font("Comic Sans MS"));
		 
		 
	     HBox hbox = new HBox();
	     hbox.setSpacing(10);
	     hbox.setStyle("-fx-background-color: #DAF7A6 ;"); 
	     hbox.setPadding(new Insets(10,20,20,10));
	     hbox.getChildren().addAll(labelhost,textHost,labelPort,textPort,buttonconnexion); 
	     
	     
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
		 
		 
		 buttonconnexion.setOnAction((evt)->{
			 String host = textHost.getText();
			 int port = Integer.parseInt(textPort.getText());


			 //on va commance cree notre server
			 try {
				 Socket socket = new Socket(host,port);
				 InputStream inputStream = socket.getInputStream();
				 InputStreamReader isr = new InputStreamReader(inputStream);
				 
				 BufferedReader br = new BufferedReader(isr);
				 pw = new PrintWriter(socket.getOutputStream(),true);
				 new Thread(()->{	
					    while(true) {
					    	try {
					     	String reponse = br.readLine();
					     	Platform.runLater(()->{
					     	listmodel.add(reponse);
					     	});
					    	} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
					    }
				 }).start();
			 } catch(IOException e) {
				 e.printStackTrace();
			 }
			 
		 });

		 //on doit utilse function for checking this stat
		 

		 //logique for button Envoyer 
		 
		 buttonEnvoyer.setOnAction((evt)->{
              String messge = textmessge.getText();
              System.out.println(""+messge);
              pw.println(messge);
		 });
		 

	}
}