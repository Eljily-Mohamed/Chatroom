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
import javafx.scene.control.TextField;
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
            
       
        HBox hboxRoom = new HBox();
        hboxRoom.setSpacing(10);
        hboxRoom.setStyle("-fx-background-color: #DAF7A6 ;"); 
        hboxRoom.setPadding(new Insets(10,20,20,10));
        hboxRoom.getChildren().addAll(selected,textRoom,cb,logininRoom);
        hboxRoom.setAlignment(Pos.CENTER);
        
    
    /////////////////creation for buttons chat Private ////////////////////////////////////////////////////
        
        

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
                                selectedPrivate.setText(cbPrivate.getValue() + "");
                            }
                        };
                       
                        cbPrivate.setOnAction(eventPrivate);
    
        Button logininPrivate = new Button("Login");
        logininPrivate.setFont(Font.font("Comic Sans MS"));
        


        HBox hboxPrivate = new HBox();
        hboxPrivate.setSpacing(10);
        hboxPrivate.setStyle("-fx-background-color: #DAF7A6 ;"); 
        hboxPrivate.setPadding(new Insets(20,20,20,10));
        hboxPrivate.getChildren().addAll(textPrivate,cbPrivate,logininPrivate);
        hboxPrivate.setAlignment(Pos.CENTER);
        

        //////////////////////////HBOX for creation de romm 
         
        Text labelcree = new Text("Cree Your Room");
        labelcree.setFont(Font.font("Comic Sans MS"));
        
        TextField textNameRoom = new TextField("Add Name "); //cree text filde permet de ajout text
        textNameRoom.setFont(Font.font("Comic Sans MS"));
        textNameRoom.setPrefSize(300, 30);
        
        Button createButton = new Button("Cree");//creation de notre button
        createButton.setFont(Font.font("Comic Sans MS"));
        
        HBox hboxCraetion = new HBox();
        hboxCraetion.setSpacing(10);
        hboxCraetion.setPadding(new Insets(10,20,20,10));
        hboxCraetion.getChildren().addAll(labelcree,textNameRoom,createButton);
        hboxCraetion.setAlignment(Pos.CENTER);
  
        VBox vboxroot = new VBox(hboxRoom,hboxPrivate,hboxCraetion);
        vboxroot.setMargin(hboxCraetion,new Insets(30,0,0,0) );
        vboxroot.setAlignment(Pos.CENTER);

////////////////////////////////////////////////////////////////////////////////////Logique for buttons 

//////////////////////buton 1

               //ajout logique pour la recuperation de la valeur selectione par user   

            createButton.setOnAction((Event) -> {
                if(!textNameRoom.getText().isEmpty()){
                  String host = textNameRoom.getText();
                  
                  Button privateButton = new Button("Private");
                  privateButton.setFont(Font.font("Comic Sans MS"));
                   
                  Button publicButton = new Button("Public");
                  publicButton.setFont(Font.font("Comic Sans MS"));
                   
                  HBox hboxStatu = new HBox();
                  hboxStatu.setSpacing(10);
                  hboxStatu.setStyle("-fx-background-color: #DAF7A6 ;"); 
                  hboxStatu.setPadding(new Insets(20,20,20,10));
                  hboxStatu.getChildren().addAll(privateButton , publicButton);
                  hboxStatu.setAlignment(Pos.TOP_CENTER);
                  
                  vboxroot.getChildren().add(hboxStatu);
                  vboxroot.setMargin(hboxStatu , new Insets(20,20,30,10));
                  System.out.println("affichage de buttons chois par user pour selectione  ");

                }
                else{
                    System.out.println("not Room selectione ");
                }
         });  


//////////////////////////////button2 

logininRoom.setOnAction((Event) -> {
    if(!selected.getText().isEmpty()){
    String[] words = selected.getText().split(" ");
        //if room public donc on a besoine de faire l'authantification si no on doit faire l'authantification
        Text labelAuth = new Text("Private Romm ");
        labelAuth.setFont(Font.font("Comic Sans MS"));
        
        TextField textAuth = new TextField("Add Key "); //cree text filde permet de ajout text
        textAuth.setFont(Font.font("Comic Sans MS"));
        textAuth.setPrefSize(300, 30);
        
        Button buttonAuth = new Button("Login");//creation de notre button
        buttonAuth.setFont(Font.font("Comic Sans MS"));
        
        HBox hboxAuthe = new HBox();
        hboxAuthe.setSpacing(10);
        hboxAuthe.setPadding(new Insets(10,20,20,10));
        hboxAuthe.getChildren().addAll(labelAuth,textAuth,buttonAuth);
        hboxAuthe.setAlignment(Pos.CENTER);
  
        vboxroot.getChildren().remove(0,vboxroot.getChildren().size());
        vboxroot.getChildren().addAll(hboxRoom,hboxAuthe);
        vboxroot.setMargin(hboxAuthe , new Insets(20,20,30,10));
        
    }
    else{
        System.out.println("not Room selectione ");
    }
});

//////////////////////////////button3 
             
      //on ajouer for selectione button

      logininPrivate.setOnAction((Event) -> {
        if(!selectedPrivate.getText().isEmpty()){
        String[] words = selectedPrivate.getText().split("->");
             //if room public donc on a besoine de faire l'authantification si no on doit faire l'authantification
        Text labelAuth = new Text("Cle comminucation ");
        labelAuth.setFont(Font.font("Comic Sans MS"));
        
        TextField textAuth = new TextField("Add Your" +words[1]); //cree text filde permet de ajout text
        textAuth.setFont(Font.font("Comic Sans MS"));
        textAuth.setPrefSize(300, 30);
        
        Button buttonAuth = new Button("Login");//creation de notre button
        buttonAuth.setFont(Font.font("Comic Sans MS"));
        
        HBox hboxAuthe = new HBox();
        hboxAuthe.setSpacing(10);
        hboxAuthe.setPadding(new Insets(10,20,20,10));
        hboxAuthe.getChildren().addAll(labelAuth,textAuth,buttonAuth);
        hboxAuthe.setAlignment(Pos.CENTER);
        
        vboxroot.getChildren().remove(0,vboxroot.getChildren().size());
        vboxroot.getChildren().addAll(hboxPrivate,hboxAuthe);
        vboxroot.setMargin(hboxAuthe , new Insets(20,20,30,10));

        }
        else{
            System.out.println("not Room selectione ");
        }
    });



        Scene sceneRoom = new Scene(vboxroot,900,600);
        stage.setScene(sceneRoom);
        stage.setResizable(false);
        stage.show();
    }
}
