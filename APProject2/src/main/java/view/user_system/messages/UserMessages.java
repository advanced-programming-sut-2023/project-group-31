package view.user_system.messages;

public enum UserMessages {
    SUCCESS("success!"),
    FAIL("failed!"),
    INVALID_FORMAT("Invalid format!"),
    MESSAGE(""),
    NOT_ENOUGH_MESSAGES("Not enough messages!"),
    PASSWORD_NOT_MATCH("Password not match!"),
    SYSTEM_ERROR("System error!"),
    ANSWER_NOT_MATCH("Answer and answer confirm does not match!"),

    PASSWORD_IS_WEAK("Password is weak: "),
    USER_EXITS_BEFORE("User have exited before do use want username- "),

    EMAIL_EXITS("Email Exits."),
    RANDOM_PASSWORD(""),
    USER_NOT_EXITS("user does not exits"),
    PASSWORD_IS_NOT_CORRECT("your password is not correct."),

    MenuMessage("login message"),
    MESSAGES(""),
    CORRECT_ANSWER("correct answer!") ,
    WRONG_ANSWER("wrong answer!"),
    OLD_PASSWORD_IS_WRONG("old password is not correct!"),
    OLD_PASSWORD_MATCHES_NEW_PASSWORD("old password equals new password!"),
    NO_CHANGE("nothing changed!"), CONTINUE("CONTINUE");
    private String txt;


    UserMessages(String txt) {
        this.txt = txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public UserMessages setAndReturn(String txt){
        this.txt=txt;
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

    @Override
    public String toString() {
        return txt;
    }
}