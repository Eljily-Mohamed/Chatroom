package chat;

import java.util.ArrayList;

class test {
     public static void main(String[] args) {
        ArrayList<Room> rooms = new ArrayList<Room>();
        for(int i =0 ; i< 4  ; i++){
             Room r = new Room("","");
             rooms.add(r);
        }

        for(Room  room : rooms){
            System.out.println("Room " + room.getName() + "" + room.getIdroom());
        }
     }
}