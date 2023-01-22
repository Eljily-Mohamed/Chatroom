package chat;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Client_Choix extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          launch(args);  
	}
    
	// this is stat number 1  
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub


		     BorderPane BP1 = new BorderPane();
		    //creation de notre title 
		     Text title_text = new Text("VOICI LES SERVICES ");
		     title_text.setFont(Font.font("Comic Sans MS",13));
		     
			 //Hboxe for choose the service user 
		     HBox hbox = new HBox();
		     hbox.setSpacing(10);
		     hbox.setStyle("-fx-background-color: #0057B8 ;"); 
		     hbox.setPadding(new Insets(30));
	
		     BP1.setTop(hbox);
		     
			 //text  for hello user 
		     Text Text_helo = new Text("Helo world");
		     Text_helo.setFont(Font.font("Comic Sans MS",13));
             Text_helo.setX(110);
             Text_helo.setY(100);
		    
		     //les differnets buttons
		     Button button_chat = new Button("Messagrie      ");//creation de notre button
			 button_chat.setFont(Font.font("Comic Sans MS",15));
			 button_chat.setLayoutX(23);
			 Button button_file = new Button("Transfere File ");//creation de notre button
			 button_file.setFont(Font.font("Comic Sans MS",15));
			 VBox vbox_buttons = new VBox();
			 vbox_buttons.setSpacing(10);
			 vbox_buttons.setStyle("-fx-background-color: #FFF ;"); 
			 vbox_buttons.setPadding(new Insets(10));
			 vbox_buttons.getChildren().addAll(title_text,button_chat,button_file);
			 vbox_buttons.setAlignment(Pos.CENTER);
			 BP1.setCenter(vbox_buttons);
		     
		    //on cree une vbox va s'affcihe 
		    VBox vbox_service = new VBox();
		    vbox_service.setSpacing(10);
		    vbox_service.setStyle("-fx-background-color: #FFD700 ;"); 
		    vbox_service.setPadding(new Insets(40));
		    BP1.setBottom(vbox_service); 
			
			//et aussi notre Scene 
		    Scene scen1 = new Scene(BP1,900,600);
		    stage.setScene(scen1);
		    stage.setResizable(false);
		    stage.show();

				        
			//logique for notre buttons and change this scene 
             
			button_chat.setOnAction((Event) -> {
				
			//incude chat action ici 

			stage.setTitle("CHAT_SOCKET");
			BorderPane BP = new BorderPane();
			   
			
				
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
				
				
			HBox hbox_chat = new HBox();
			hbox_chat.setSpacing(10);
			hbox_chat.setStyle("-fx-background-color: #DAF7A6 ;"); 
			hbox_chat.setPadding(new Insets(10,20,20,10));
			hbox_chat.getChildren().addAll(labelhost,textHost,labelPort,textPort,buttonconnexion); 
				
				
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
			stage.setScene(scene);
			stage.getIcons().add(new Image(Client.class.getResourceAsStream("icon_chat.jpg")));
			stage.show();

			});
 
			button_file.setOnAction((Event) -> {
			   
			});
	   
	}

}
