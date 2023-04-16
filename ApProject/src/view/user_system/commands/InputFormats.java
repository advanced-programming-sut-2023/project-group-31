package view.user_system.commands;

public enum InputFormats {
    USERNAME("username"),
    PASSWORD("password");
    String name;
    String format;


    InputFormats(String name, String format,boolean isCompulsory) {
        this.name = name;
        this.format = format;

    }


    InputFormats(String name) {
        this.name = name;
        this.format = null;
    }


    public String getFormat() {
        return format;
    }

    public String getName() {
        return name;
    }

    public static InputFormats getInputFormat(String name){
        for(InputFormats inputFormat:values()){
            if(inputFormat.getName().equals(name)){
                return inputFormat;
            }
        }
        return null;
    }
}
