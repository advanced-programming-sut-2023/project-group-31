package view.user_system.messages;

public enum UserMessages {
    SUCCESS("success!"),
    INVALID_USERNAME_FORMAT("Invalid username format!"),
    NOT_ENOUGH_MESSAGES("Not enough messages!"),
    PASSWORD_NOT_MATCH("Password not match!"),
    CONFIRM("")
    //TODO: add enums
    ;
    private String txt;


    UserMessages(String txt) {
        this.txt = txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }


    public String getTxt() {
        return txt;
    }

    public static UserMessages getMessage(String txt){
        for(UserMessages message:values()){
            if(message.getTxt().contains(txt)){
                return message;
            }
        }
        return null;
    }


}
