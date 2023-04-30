package view.viewStyle;

public enum Colors {
    RED("red", "\\u001B[40m"),
    GREEN("green","\\u001B[42m"),
    BLUE("blue","\t\\u001B[44m"),
    PURPLE("purple","\\u001B[45m"),
    BLACK("black","\\u001B[40m"),
    YELLOW("yellow","\\u001B[43m"),
    WHITE("white","\\u001B[47m"),
    RESET("reset","\\033[0m");
    private String name;
    private String backgroundColorCode;

    Colors(String name, String backgroundColorCode) {
        this.name = name; this.backgroundColorCode=backgroundColorCode;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getBackgroundColorCode() {
        return backgroundColorCode;
    }
}
