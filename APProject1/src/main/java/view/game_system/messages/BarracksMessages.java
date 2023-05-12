package view.game_system.messages;

public enum BarracksMessages {
    SUCCESS("successfully done!"),
    INVALID_COMMAND("Invalid command!"),
    NOT_ENOUGH_GOLD("not enough gold my lord!"),
    NOT_ENOUGH_WEAPON("not enough weapon my lord!"),
    PEOPLE_NEEDED("people needed!"),
    INVALID_TROOP_TYPE("no troop with this type!");
    String command;
    BarracksMessages(String command){
        this.command=command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
