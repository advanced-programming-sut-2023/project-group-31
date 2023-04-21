package view.user_system.commands;

public enum InputFormats {
    USERNAME("username","[\\w]+",true),
    PASSWORD("password",true),
    EMAIL("email","([\\w]|\\.)+@([\\w]|\\.)+.([\\w]|\\.)+",true),
    QUESTION_NUMBER("question-number","([\\w]|\\.)+@([\\w]|\\.)+.([\\w]|\\.)+",true)

    ;
    private String name;
    private String format;
    private boolean isCompulsory=true;


    InputFormats(String name, String format,boolean isCompulsory) {
        this.name = name;
        this.format = format;
        this.isCompulsory=isCompulsory;
    }

    InputFormats(String name,boolean isCompulsory) {
        this.name = name;
        this.isCompulsory=isCompulsory;

    }




    public String getFormat() {
        return format;
    }

    public String getName() {
        return name;
    }

    public boolean isCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(boolean compulsory) {
        isCompulsory = compulsory;
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
