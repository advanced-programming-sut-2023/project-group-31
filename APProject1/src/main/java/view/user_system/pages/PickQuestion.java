package view.user_system.pages;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.enums.Menus;
import view.user_system.StrongHoldCrusaderGame;

import java.util.ArrayList;
import java.util.Random;

public class PickQuestion {
    private ToggleGroup TG;
    @FXML
    private TextField captchaText;
    @FXML
    private Label result;
    @FXML
    private RadioButton first;
    @FXML
    private RadioButton second;
    @FXML
    private RadioButton third;
    @FXML
    private RadioButton theNext;
    @FXML
    private ImageView captcha;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    private static ArrayList<Integer>captchaImage=LoginPage.getCaptchaImage();

    public void completeRegister(MouseEvent mouseEvent) {
        if (textField1.getText().equals("")||textField2.getText().equals("")||captchaText.getText().equals("")){
            result.setText("Fill all fields");
            resetCaptchaFailRegister();
            return;
        }
        if (TG.getSelectedToggle()==null){
            result.setText("choose your recovery answer");
            resetCaptchaFailRegister();
            return;
        }
//        String Url=captcha.getImage().getUrl().substring(93,97);
//        if (!Url.equals(captchaText.getText())){
//            result.setText("enter captcha correctly");
//            resetCaptchaFailRegister();
//            return;
//        }
        if (!textField1.getText().equals(textField2.getText())){
            result.setText("enter similar answer for question");
            resetCaptchaFailRegister();
            return;
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Moving to login");
        alert.setHeaderText("Successful register");
        alert.setContentText("Your account registered successfully\nproceeding to login menu!");
        alert.showAndWait();
        RegisterPage.getUser().setPasswordRecoveryAnswer(textField1.getText());
        StrongHoldCrusaderGame.changeMenu(Menus.LOGIN);
    }

    private void resetCaptchaFailRegister() {
        captchaText.setText("");
        Random random=new Random();
        int num=random.nextInt();
        if (num<0){
            num=-1*num;
        }
        num=num%50;
        captchaImage.get(num);
        captcha.setImage(new Image(LoginPage.class.getResource("/Media/Captcha/"+captchaImage.get(num)+".png").toString()));
    }

    public void resetCaptcha(MouseEvent mouseEvent) {
        captchaText.setText("");
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
        ToggleGroup toggleGroup=new ToggleGroup();
        first.setToggleGroup(toggleGroup);
        second.setToggleGroup(toggleGroup);
        third.setToggleGroup(toggleGroup);
        theNext.setToggleGroup(toggleGroup);
        TG=toggleGroup;
        Random random=new Random();
        int num=random.nextInt();
        if (num<0){
            num=-1*num;
        }
        num=num%50;
        captchaImage.get(num);
        captcha.setImage(new Image(LoginPage.class.getResource("/Media/Captcha/"+captchaImage.get(num)+".png").toString()));
    }

    public void set1(MouseEvent mouseEvent) {
        RegisterPage.getUser().setPasswordRecoveryQuestion("What is your best food?");
    }

    public void set2(MouseEvent mouseEvent) {
        RegisterPage.getUser().setPasswordRecoveryQuestion("where does your mather?");
    }

    public void set3(MouseEvent mouseEvent) {
        RegisterPage.getUser().setPasswordRecoveryQuestion("What do you prefer between Messi and Haj Qasem?");
    }

    public void set4(MouseEvent mouseEvent) {
        RegisterPage.getUser().setPasswordRecoveryQuestion("Chips mikhori?");
    }

}
