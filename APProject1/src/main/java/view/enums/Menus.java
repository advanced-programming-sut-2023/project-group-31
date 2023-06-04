package view.enums;

public enum Menus {
  LOGIN("loginmenu"),
  REGISTER("registermenu"),
  MAIN("mainmenu"),
  PROFILE("profilemenu"),
  PICKQUESION("pickquestion"),
  SCOREBOARD("scoreboard")
 ;
      private final String name;
     Menus(String name){
         this.name=name;
     }
     public String getName(){
         return name;
     }
}
