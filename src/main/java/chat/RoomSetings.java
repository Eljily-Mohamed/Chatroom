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
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        Label selected = new Label();
        
        //create handler for this comboBox 
        EventHandler<ActionEvent> event =
        new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e){
                selected.setText(cb.getValue() + " selected");
            }
        };
       
        cb.setOnAction(event);

      
        //creation de notre button qui login a l'un de room available
        Button logininRoom = new Button("Login");
        logininRoom.setFont(Font.font("Comic Sans MS"));
         
        //ajout logique pour la recuperation de la valeur selectione par user
        
       
            logininRoom.setOnAction((Event) -> {
                if(!selected.getText().isEmpty()){
                String[] words = selected.getText().split("\\W");
                    System.out.println(words[0]);
                }
                else{
                    System.out.println("not Room selectione ");
                }
            });     
       
        HBox hboxRoom = new HBox();
        hboxRoom.setSpacing(10);
        hboxRoom.setStyle("-fx-background-color: #DAF7A6 ;"); 
        hboxRoom.setPadding(new Insets(10,20,20,10));
        hboxRoom.getChildren().addAll(selected,textRoom,cb,logininRoom);
        hboxRoom.setAlignment(Pos.CENTER);
        
    
        //creation for buttons chat Private  

              //create ComboBox 
               
              Text textPrivate= new Text("Private Chat: ");
              textRoom.setFont(Font.font("Comic Sans MS"));

              String [] privateChoix = {"PV -> Numero","PV -> Name"};
              ComboBox cbPrivate =
              new ComboBox(FXCollections
                        .observableArrayList(privateChoix));
            
                        Label selectedPrivate = new Label();
        
                        //create handler for this comboBox 
                        EventHandler<ActionEvent> eventPrivate =
                        new EventHandler<ActionEvent>() {
                             public void handle(ActionEvent e){
                                selected.setText(cb.getValue() + " selected");
                            }
                        };
                       
                        cb.setOnAction(eventPrivate);
    
        Button logininPrivate = new Button("Login");
        logininPrivate.setFont(Font.font("Comic Sans MS"));
        
        HBox hboxPrivate = new HBox();
        hboxPrivate.setSpacing(10);
        hboxPrivate.setStyle("-fx-background-color: #DAF7A6 ;"); 
        hboxPrivate.setPadding(new Insets(10,20,20,10));
        hboxPrivate.getChildren().addAll(textPrivate,cbPrivate,logininPrivate);
        hboxPrivate.setAlignment(Pos.CENTER);
        
        VBox vboxroot = new VBox(hboxRoom,hboxPrivate);
        vboxroot.setAlignment(Pos.CENTER);

        
        Scene sceneRoom = new Scene(vboxroot,900,600);
        stage.setScene(sceneRoom);
        stage.setResizable(false);
        stage.show();
    }
}
