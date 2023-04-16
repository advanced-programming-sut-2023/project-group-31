package view.user_system.messages;

public enum Messages {
    SUCCESS("success: "),
    INVALID_USERNAME_FORMAT("Invalid username format: ")
    //TODO: add enums
    ;
    private String txt;
    private String input; //the part of input in witch error occurred

    Messages(String txt) {
        this.txt = txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void addInput(String input) {
        txt+= input;
    }

    public String getTxt() {
        return txt;
    }

    public static Messages getMessage(String txt){
        for(Messages message:values()){
            if(message.getTxt().contains(txt)){
                return message;
            }
        }
        return null;
    }
}
