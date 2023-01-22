package chat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Room extends Application {
    
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
          
        Language vietnamese = new Language("vi", "Vietnamese");
        Language english = new Language("en", "English");
        Language russian = new Language("ru", "Russian");
        ObservableList<Language> languages //
        = FXCollections.observableArrayList(vietnamese, english, russian);

ChoiceBox<Language> cb = new ChoiceBox<Language>(languages);
        //creation de notre button qui login a l'un de room available
        Button logininRoom = new Button("Login");
        logininRoom.setFont(Font.font("Comic Sans MS"));
         
        HBox hboxRoom = new HBox();
        hboxRoom.setSpacing(10);
        hboxRoom.setStyle("-fx-background-color: #DAF7A6 ;"); 
        hboxRoom.setPadding(new Insets(10,20,20,10));
        hboxRoom.setAlignment(Pos.CENTER);

        //ajout logique pour la recuperation de la valeur selectione par user
        
         //Text value = new Text(""+cb.getSelectionModel().getSelectedItem());
        System.out.println("slectione : "+cb.getSelectionModel().getSelectedItem());
        hboxRoom.getChildren().addAll(textRoom,cb,logininRoom);
        Scene sceneRoom = new Scene(hboxRoom,900,600);
        stage.setScene(sceneRoom);
        stage.setResizable(false);
        stage.show();
    }
}
