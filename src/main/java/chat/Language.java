package chat;

public class Language {
    private String name;
    private String statu;

    Language (String name){
          this.name = name;
    }
    Language (String name , String statu){
         this(name);
         this.statu = statu;
    }
}
