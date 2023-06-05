package view.user_system.pages;


import controller.ControllerUtils;
import controller.user_system.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Popups.Popup1;
import model.User;
import view.enums.Menus;
import view.user_system.StrongHoldCrusaderGame;
import view.user_system.commands.LoginCommands;
import view.user_system.messages.UserMessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

import static view.ViewUtils.putInHashmap;

public class LoginPage {
    @FXML
    private Button Fpassword;
    @FXML
    private TextField captchaText;
    @FXML
    private ImageView captcha;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label error;
    @FXML
    private Button loginButton;
    private static ArrayList<Integer>captchaImage;
    static {
        captchaImage=new ArrayList<>();
        captchaImage.add(1181);
        captchaImage.add(1381);
        captchaImage.add(1491);
        captchaImage.add(1722);
        captchaImage.add(1959);
        captchaImage.add(2163);
        captchaImage.add(2177);
        captchaImage.add(2723);
        captchaImage.add(2785);
        captchaImage.add(3541);
        captchaImage.add(3847);
        captchaImage.add(3855);
        captchaImage.add(3876);
        captchaImage.add(3967);
        captchaImage.add(4185);
        captchaImage.add(4310);
        captchaImage.add(4487);
        captchaImage.add(4578);
        captchaImage.add(4602);
        captchaImage.add(4681);
        captchaImage.add(4924);
        captchaImage.add(5326);
        captchaImage.add(5463);
        captchaImage.add(5771);
        captchaImage.add(5849);
        captchaImage.add(6426);
        captchaImage.add(6553);
        captchaImage.add(6601);
        captchaImage.add(6733);
        captchaImage.add(6960);
        captchaImage.add(7415);
        captchaImage.add(7609);
        captchaImage.add(7755);
        captchaImage.add(7825);
        captchaImage.add(7905);
        captchaImage.add(8003);
        captchaImage.add(8070);
        captchaImage.add(8368);
        captchaImage.add(8455);
        captchaImage.add(8506);
        captchaImage.add(8555);
        captchaImage.add(8583);
        captchaImage.add(8692);
        captchaImage.add(8776);
        captchaImage.add(8972);
        captchaImage.add(8996);
        captchaImage.add(9061);
        captchaImage.add(9386);
        captchaImage.add(9582);
        captchaImage.add(9633);
    }
    public void login(MouseEvent mouseEvent) {
        if (username.getText().equals("") || password.getText().equals("")||captchaText.getText().equals("")) {
            error.setText("Fill all fields");
            resetCaptchaFailLogin();
            // error.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        String Url=captcha.getImage().getUrl().substring(93,97);
        if (!Url.equals(captchaText.getText())){
            resetCaptchaFailLogin();
            return;
        }
        UserMessages result;
        Matcher matcher=LoginCommands.getMatcher("user login -u "+username.getText()+" -p "+password.getText(),LoginCommands.LOGIN);
        ControllerUtils.setInputs(putInHashmap(matcher,LoginCommands.LOGIN.getRegex()));
        if ((result = LoginController.loginUser())!= UserMessages.SUCCESS){
            error.setText(result.getTxt());
            resetCaptchaFailLogin();
            // error.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        ControllerUtils.setCurrentUser(User.getUserByUsername(username.getText()));
        StrongHoldCrusaderGame.changeMenu(Menus.MAIN);
    }

    private void resetCaptchaFailLogin() {
        Random random=new Random();
        int num=random.nextInt();
        if (num<0){
            num=-1*num;
        }
        num=num%50;
        captchaImage.get(num);
        captcha.setImage(new Image(LoginPage.class.getResource("/Media/Captcha/"+captchaImage.get(num)+".png").toString()));
    }

    public void initialize(){
        Random random=new Random();
        int num=random.nextInt();
        if (num<0){
            num=-1*num;
        }
        num=num%50;
        captchaImage.get(num);
        captcha.setImage(new Image(LoginPage.class.getResource("/Media/Captcha/"+captchaImage.get(num)+".png").toString()));
    }
    public void register(MouseEvent mouseEvent) {
        StrongHoldCrusaderGame.changeMenu(Menus.REGISTER);

    }
    public void exit(MouseEvent mouseEvent)
    {
        //datebase save user
        System.exit(0);

    }

    public void resetCaptcha(MouseEvent mouseEvent) {
        Random random=new Random();
        int num=random.nextInt();
        if (num<0){
            num=-1*num;
        }
        num=num%50;
        captchaImage.get(num);
        captcha.setImage(new Image(LoginPage.class.getResource("/Media/Captcha/"+captchaImage.get(num)+".png").toString()));
    }

    public void forgotPassword(MouseEvent mouseEvent) {
            User user=User.getUserByUsername(username.getText());
            if (!username.getText().equals("") && user != null) {
                Fpassword.setOnAction(e -> {
                    try {
                        Popup1.display(user);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
            else
                return;
        }


    public static ArrayList<Integer> getCaptchaImage() {
        return captchaImage;
    }
}