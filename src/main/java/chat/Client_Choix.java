package chat;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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

  }

}