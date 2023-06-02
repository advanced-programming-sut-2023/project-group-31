module workshop2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;



    exports view;
    exports view.user_system.menu;
    opens view to javafx.fxml;
    opens view.user_system.menu_controller to javafx.fxml;
}