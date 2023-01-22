package chat;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RoomSetings extends Application {

    String item ;
    
    public static void main(String[] args) {
        launch(args);
  }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Room Chat");
         Text textRoom = new Text("Room Available: ");
		 textRoom.setFont(Font.font("Comic Sans MS"));

        //cree text for display for identifie le room available 

        //creating checkboxes for rentre dans une chat room available
        ArrayList <Room> Romms = new ArrayList<Room>();
      
        Room vietnamese = new Room("Eljily", "Vietnamese");
        Room english = new Room("Mohamed", "English");
        Room russian = new Room("Ahmed", "Russian");
        
        Romms.add(vietnamese);
        Romms.add(english);
        Romms.add(russian);

        ArrayList <String> namesRooms = new ArrayList<String>();
        for (Room room : Romms) {
               namesRooms.add(room.getName());
        }


         //create ComboBox 
        ComboBox cb =
        new ComboBox(FXCollections
                  .observableArrayList(namesRooms));
        
        //create handler for this comboBox 
        EventHandler<ActionEvent> event =
        new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e){
               item = cb.getValue().toString();
            }
        };
       
        cb.setOnAction(event);

        System.out.println("item selectione : "+item);
        //creation de notre button qui login a l'un de room available
        Button logininRoom = new Button("Login");
        logininRoom.setFont(Font.font("Comic Sans MS"));
         
 

        HBox hboxRoom = new HBox();
        hboxRoom.setSpacing(10);
        hboxRoom.setStyle("-fx-background-color: #DAF7A6 ;"); 
        hboxRoom.setPadding(new Insets(10,20,20,10));
        hboxRoom.getChildren().addAll(textRoom,cb,logininRoom);
        hboxRoom.setAlignment(Pos.CENTER);

        //ajout logique pour la recuperation de la valeur selectione par user
    
      
        Scene sceneRoom = new Scene(hboxRoom,900,600);
        stage.setScene(sceneRoom);
        stage.setResizable(false);
        stage.show();
    }
}
