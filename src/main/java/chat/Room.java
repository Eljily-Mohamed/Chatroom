package chat;

public class Room {
     private static int numberRoom ;
     private String name ;
     private String statu ;
     private String key="" ;

     Room (String name, String statu){
        this.name = name;
        this.statu = statu;
        this.numberRoom ++ ;
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
    
}
