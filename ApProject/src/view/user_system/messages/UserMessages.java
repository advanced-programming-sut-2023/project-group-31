package view.user_system.messages;

public enum UserMessages {
    SUCCESS("success!"),
    INVALID_FORMAT("Invalid format!"),
    NOT_ENOUGH_MESSAGES("Not enough messages!"),
    PASSWORD_NOT_MATCH("Password not match!"),
    SYSTEM_ERROR("System error!"),
    ANSWER_NOT_MATCH("Answer and answer confirm does not match!"),

    PASSWORD_IS_WEAK("Password is weak: "),
    USER_EXITS_BEFORE("User have exited before do use want username- "),

    EMAIL_EXITS("Email Exits."),
    RANDOM_PASSWORD("")
    //TODO: add enums
    ;
    private String txt;


    UserMessages(String txt) {
        this.txt = txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public UserMessages setAndPrintMessage(String txt){
        this.txt+=txt;
        return this;
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
