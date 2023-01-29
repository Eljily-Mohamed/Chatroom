package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RoomSetings extends Application {
    
    //varaibles for the room setings
    String item ; //item name 
    //Stream
    PrintWriter pw;
    InputStream is ;
    InputStreamReader isr ;
    BufferedReader br ;
    OutputStream outputStream;
    //socket connection
    Socket socket ;
    //id for user pour get room user
    String id ;


    public static void main(String[] args) {
        launch(args);
  }

    @Override
    public void start(Stage stage) throws Exception {

	    //*************************************//
	    //***********  Affichage  *************//

        stage.setTitle("Room Chat");
        Text textRoom = new Text("Room Available: ");
		textRoom.setFont(Font.font("Comic Sans MS"));

         //*************************************//
	    //***********  Sectione 1  *************//

        /*sectione 1 : qui contient selection de room apartire de rooms available 
                       apres login in this room */
        
        //qui recupere le nombre de Rooms 
        ArrayList <Room> Romms = new ArrayList<Room>();
        //appelle a la fonction fetche Rooms ligne:
        connexionEtabler("3");
        fetchRooms(Romms);
        //creation de Map qui contient id de Room with le nom de Room 
        Map<Integer,String> rooms = new HashMap<>(); 
        for (Room room : Romms) {
            rooms.put(room.getIdroom(),room.getName());
        }
        //create ComboBox 
        ComboBox cb =new ComboBox(FXCollections
                       .observableArrayList(rooms.entrySet().toArray()));

        //creation de label qui s'affcihe item selectione 
        Label selected = new Label(); 
        //create handler for this comboBox 
        EventHandler<ActionEvent> event =
        new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e){
                String [] values =cb.getValue().toString().split("=");
                //ajoute text a lable name l'element selectioner
                selected.setText(cb.getValue() + " selected" + values[0]);
                //recupere id for this room and stocke dans notre varaible id
                id=values[0];
            }
        };

        cb.setOnAction(event);
        //end selecte 
      
        //creation de notre button qui login a l'un de room available
        Button logininRoom = new Button("Login");
        logininRoom.setFont(Font.font("Comic Sans MS"));
            
        //creation de notre Hbox qui contient la liste des rooms available et aussi buttons login 
        HBox hboxRoom = new HBox();
        hboxRoom.setSpacing(10);
        hboxRoom.setStyle("-fx-background-color: #DAF7A6 ;"); 
        hboxRoom.setPadding(new Insets(10,20,20,10));
        hboxRoom.getChildren().addAll(selected,textRoom,cb,logininRoom);
        hboxRoom.setAlignment(Pos.CENTER);
        
        //*************************************//
	    //***********  Sectione 2 *************//

        /*sectione 2 : qui contient creation de Chat privee */
    
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
        
         
        //*************************************//
	    //***********  Sectione 3 *************//

        /*sectione 3 : pour creation de Room et specifie private ou public  */
        
         
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


             //*************************************//
	        //***********  logique  ***************//

	    //logique sectione 3

        createButton.setOnAction((Event) -> { 
            if(!textNameRoom.getText().isEmpty()){
                  //display for this buttons 
                  Room room = new Room("", "") ;
                  String host = textNameRoom.getText();
                  room.setName(host);
                  
                  
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

                  vboxroot.getChildren().remove(0,vboxroot.getChildren().size());
                  vboxroot.getChildren().addAll(hboxCraetion,hboxStatu);
                  vboxroot.setMargin(hboxStatu , new Insets(20,20,30,10));
                  System.out.println("affichage de buttons chois par user pour selectione  ");


                //buttons type de room logique and display for button public 
                 
                    privateButton.setOnMouseClicked((Eventprivate) -> { 
                    int leftLimit = 48; // numeral '0'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 32;
                    Random random = new Random();
                  
                    String generatedString = random.ints(leftLimit, rightLimit + 1)
                      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                      .limit(targetStringLength)
                      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                      .toString();
                    
                    room.setKey(generatedString);
                    room.setStatu("private");
                    createRoom(room);
                    try {  
                      connexion(rooms.size(),stage);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                  });

                  publicButton.setOnAction((eventPubic) -> {
                       room.setStatu("public");  
                       createRoom(room);
                       try {
                          connexion(rooms.size(),stage);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                  });

                  //end buttons type de room
             }else{
                    //affichage le nom de la room empty 
                    System.out.println("Empty Room name ");
                  }
                 
         });  





//////////////////////////////button2 

logininRoom.setOnAction((Event) -> {
    if(!selected.getText().isEmpty()){
    String[] words = selected.getText().split(" ");
         
    Room privat = loginRoom(id);
    System.out.println("id room is : "+privat.getIdroom());
    if(privat.getStatu().compareToIgnoreCase("private") == 0){
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

        buttonAuth.setOnAction((eventAuth) -> {
            id=textAuth.getText();
            if(privat.getKey().compareTo(id) == 0){
                  System.out.println("valide key ");
                  try {
                    connexion(privat.getIdroom(),stage);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else{
                  System.out.println("not valide key ");
            }
        });

    }
    else{
        
    }        
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


             //*************************************//
	        //***********  functions  *************//

    //function qui permet de recupere le room available from server 
    void fetchRooms(ArrayList<Room>  listRooms ){        
       try {
            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            //recupere romms list from server and stocket dans notre list que on va retournee 
            List<Room> listOfRooms = (List<Room>) objectInputStream.readObject();
            //System.out.println("le nombre de rooms egal : " + listOfRooms.size());
            listRooms.addAll(listOfRooms);
        }catch (Exception e) {
            System.out.println("error : " + e);
        }
    }

     //function permet de etablier connexion 
     void connexionEtabler(String reqType) throws Exception{
                //utilise in ligne 68 in the same file RoomSetings.java 
                socket = new Socket("localhost", 1234);
                System.out.println("Connected!");
                // get the output stream from the socket.
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                pw = new PrintWriter(socket.getOutputStream(),true);
                pw.println(reqType);                
     }

        //conexion a serveur pour ajoute le new Room
         void createRoom(Room r){
               try {
                        socket = new Socket("localhost", 1234);
                        System.out.println("Connected!");
                        // get the output stream from the socket.
                        is = socket.getInputStream();
                        isr = new InputStreamReader(is);
                        br = new BufferedReader(isr);
                        pw = new PrintWriter(socket.getOutputStream(),true);
                        pw.println("2");
                        OutputStream outputStream = socket.getOutputStream();
                        // create an object output stream from the output stream so we can send an object through it
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                        System.out.println("Sending messages to the ServerSocket");
                        objectOutputStream.writeObject(r);          
    
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
         }

         //conexion a server et login  in room specifie using id for this room 
         Room loginRoom(String idRoom){
             try {
                  //utilise in ligne 68 in the same file RoomSetings.java 
                  socket = new Socket("localhost", 1234);
                  System.out.println("Connected!");
                  // get the output stream from the socket.
                  is = socket.getInputStream();
                  isr = new InputStreamReader(is);
                  br = new BufferedReader(isr);
                  pw = new PrintWriter(socket.getOutputStream(),true);
                  pw.println("4");
                  pw.println(idRoom);
                  //recupere room and recorede new matche  
			      // get the input stream from the connected socket
			      InputStream inputStream = socket.getInputStream();
			      // create a DataInputStream so we can read data from it.
			      ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                  Room r = (Room) objectInputStream.readObject();
                  return r;  

             } catch (Exception e) {
                // TODO: handle exception
             }
            return null;
         }
         
         //connexion in Room specifique 
         void connexion(int idRoom, Stage Stage) throws Exception{
            System.out.println("Connexion in room chat ");
            System.out.println("idRoom" + idRoom);
            Client client = new Client(idRoom);
            client.start(Stage);
         }
                 
}
