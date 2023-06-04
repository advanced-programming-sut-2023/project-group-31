
module APProject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;


    exports  view;
    exports  view.user_system;
    exports  view.game_system;
    exports  model.game_stuff;
    exports  model.user_stuff;
    exports view.user_system.pages;
    exports model.game_stuff.people;
    exports model.game_stuff.people.enums;
    exports model.game_stuff.enums;
    exports model.game_stuff.buildings;
    exports model.Popups;

    opens view to javafx.fxml, javafx.base, com.google.gson;
    opens view.game_system to javafx.fxml, javafx.base, com.google.gson;
    opens view.user_system to javafx.fxml, javafx.base, com.google.gson;
    opens  model to com.google.gson;
    opens  model.user_stuff to com.google.gson;
    opens  model.game_stuff to com.google.gson;
    opens model.game_stuff.people to com.google.gson;
    opens view.user_system.pages to javafx.fxml;
    opens model.game_stuff.people.enums to com.google.gson;
    opens model.game_stuff.enums to com.google.gson;
    opens model.game_stuff.buildings to com.google.gson;
    opens model.Popups to javafx.fxml;
    opens view.user_system.menu_controller to javafx.fxml;
}