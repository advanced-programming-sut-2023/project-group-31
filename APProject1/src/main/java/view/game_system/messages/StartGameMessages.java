package view.game_system.messages;

import javafx.scene.control.Alert;

public enum StartGameMessages {
    SUCCESS("successful!",Alert.AlertType.CONFIRMATION),
    MAP_NOT_FOUND("map not found!"),
    INVALID_COMMAND("invalid command!"),
    COORDINATE_OUT_OF_BOUND("coordinate out of bound!"),
    INVALID_TEXTURE_TYPE("invalid texture type!"),
    BLOCK_IS_NOT_EMPTY("the block is not empty: "),
    NO_SUCH_USER("no such user found!"),
    USER_IS_ALREADY_ADDED("user is already added!"),
    MAXIMUM_NUMBER_OF_USERS("can not add any more user!"),
    LORD_HOUSE_IS_SELECTED_BEFORE("the lord house is already selected for another player!"),
    INVALID_COLOR("invalid color!"),
    COLOR_IS_ALREADY_SET("player is already in the team!"),
    INVALID_LORD_HOUSE_NUMBER("invalid lord house number!"),
    TOO_FEW_PLAYERS("too few players"),
    OUT_OF_MAP("The position is out of map"),
    THE_MAP_IS_ALREADY_SAVED("the map is already saved!"),

    CHOOSE_COLOR("choose all colors"),
    NOT_SUITABLE_TEXTURE("you can not put it here");
    private String txt;
    private String input;

    private Alert.AlertType alertType;


    StartGameMessages(String txt) {
        this.txt = txt;
        this.input = "";
    }

    StartGameMessages(String input, Alert.AlertType alertType) {
        this.input = input;
        this.alertType =alertType;
    }



    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }



    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Alert.AlertType getAlertType() {
        return alertType;
    }

    public static void showAlert(StartGameMessages message){
        Alert.AlertType alertType;
        if(message.getAlertType()==null){
            alertType = Alert.AlertType.ERROR;
        }else alertType = message.alertType;
        Alert alert = new Alert(alertType);
        alert.setTitle(alertType.name());
        alert.setHeaderText("start game" +((message.getAlertType() ==null||message.getAlertType().equals(Alert.AlertType.ERROR))?"failed":""));
        alert.setContentText(message.getTxt());
        alert.showAndWait();
    }

    public String getTxt() {
        return txt + input;
    }

}
