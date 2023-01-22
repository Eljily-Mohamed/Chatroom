package chat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Room extends Application {
    
    public static void main(String[] args) {
        launch(args);
  }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Room Chat");
        BorderPane borderPane = new BorderPane();
        
        //creating checkboxes for rentre dans une chat room available

        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
            "New Document", "Open ", 
            new Separator(), "Save", "Save as")
        );

        //creation de notre button qui login a l'un de room available
        Button logininRoom = new Button("");
        logininRoom.setFont(Font.font("Comic Sans MS"));
         
        HBox hboxRoom = new HBox();
        hboxRoom.setSpacing(10);
        hboxRoom.setStyle("-fx-background-color: #DAF7A6 ;"); 
        hboxRoom.setPadding(new Insets(10,20,20,10));
        hboxRoom.getChildren().addAll(cb,logininRoom);
      

        Scene sceneRoom = new Scene(borderPane,900,600);
        stage.setScene(sceneRoom);
        stage.setResizable(false);
        stage.show();
    }
}
