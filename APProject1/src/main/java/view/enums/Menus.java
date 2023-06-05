package view.enums;

public enum Menus {
  LOGIN("loginmenu"),
  REGISTER("registermenu"),
  MAIN("mainmenu"),
  PROFILE("profilemenu"),
  PICKQUESION("pickquestion"),

  START_GAME("startgame")
 ;
      private final String name;
     Menus(String name){
         this.name=name;
     }
     public String getName(){
         return name;
     }
}
