package chat;

import java.io.Serializable;

public class Room implements Serializable{
     private int idroom ; 
     private String name ;
     private String statu ;
     private String key="" ;

     Room (String name, String statu){
        this.name = name;
        this.statu = statu;
     }


     public void setStatu(String statu) {
         this.statu = statu;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getName() {
         return name;
     }
     public String getStatu() {
         return statu;
     }

     public void setKey(String key) {
         this.key = key;
     }
     public String getKey() {
         return this.key;
     }
     //methode toString 
     @Override
     public String toString() {
         return this.name;
     }
     //methode pour recupere id de room 

     public int getIdroom() {
         return idroom;
     }
     public void setIdroom(int idroom) {
         this.idroom = idroom;
     }
    
}
