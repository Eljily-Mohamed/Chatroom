package chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
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

        

        //creation de notre button qui login a l'un de room available
        Button logininRoom = new Button("");
        logininRoom.setFont(Font.font("Comic Sans MS"));


        Scene sceneRoom = new Scene(borderPane,900,600);
        stage.setScene(sceneRoom);
        stage.setResizable(false);
        stage.show();
    }
}
