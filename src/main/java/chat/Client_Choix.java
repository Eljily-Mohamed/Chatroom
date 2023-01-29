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

	private Scene scene;

	public static void main(String[] args) {
          launch(args);  
	}
 
	@Override
	public void start(Stage stage) throws Exception {
     
	         //*************************************//
	        //***********  Affichage  *************//

             //start 1ere BorderPanel
		     BorderPane BP1 = new BorderPane();
		     //Message to Know the Service Provider
		     Text titletext = new Text("VOICI LES SERVICES ");
		     titletext.setFont(Font.font("Comic Sans MS",13));
			 //start Hboxe to choose the service 
		     HBox hbox = new HBox();
		     hbox.setSpacing(10);
		     hbox.setStyle("-fx-background-color: #0057B8 ;"); 
		     hbox.setPadding(new Insets(30));
	         //end Hbox

		     BP1.setTop(hbox);
		     
			 //text  for hello user 
		     Text textHelo = new Text("Helo world");
		     textHelo.setFont(Font.font("Comic Sans MS",13));
             textHelo.setX(110);
             textHelo.setY(100);

		     //buttons for each service
		     Button buttonChat = new Button("Messagrie      ");
			 buttonChat.setFont(Font.font("Comic Sans MS",15));
			 buttonChat.setLayoutX(23);

			 Button buttonFile = new Button("Transfere File ");
			 buttonFile.setFont(Font.font("Comic Sans MS",15));

             //start vbox contient buttons
			 VBox vboxButtons = new VBox();
			 vboxButtons.setSpacing(10);
			 vboxButtons.setStyle("-fx-background-color: #FFF ;"); 
			 vboxButtons.setPadding(new Insets(10));
			 vboxButtons.getChildren().addAll(titletext,buttonChat,buttonFile);
			 vboxButtons.setAlignment(Pos.CENTER);
             //end vbox_buttons

			 BP1.setCenter(vboxButtons);
		     
		    //start vbox for Footer
		    VBox vboxFooter = new VBox();
		    vboxFooter.setSpacing(10);
		    vboxFooter.setStyle("-fx-background-color: #FFD700 ;"); 
		    vboxFooter.setPadding(new Insets(40));
		    BP1.setBottom(vboxFooter); 
			

             //*************************************//
	        //***********  logique  ***************//
			
			//for button buttonChat

			buttonChat.setOnMouseClicked((e) -> {
                  RoomSetings rS = new RoomSetings();
				  try {
					rS.start(stage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			//for button on va faire apres 
          

			//Display this Scene in stage 
		    scene = new Scene(BP1,900,600);//width, height
		    stage.setScene(scene);
		    stage.setResizable(false);
		    stage.show();

   }

}