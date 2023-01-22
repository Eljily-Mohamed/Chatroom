package chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Room extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Room Chat");
        BorderPane borderPane = new BorderPane();


        Scene sceneRoom = new Scene(borderPane,900,600);
        stage.setScene(sceneRoom);
        stage.show();
    }
}
