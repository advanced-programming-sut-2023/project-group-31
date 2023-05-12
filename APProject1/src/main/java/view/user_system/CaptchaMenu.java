package view.user_system;

import controller.ControllerUtils;
import model.DataBase;
import view.ViewUtils;
import view.user_system.messages.UserMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

public class CaptchaMenu extends ViewUtils {

    private static CaptchaMenu object = null;
    ArrayList<String> captchaDigits;

    private String captchaCode;

    public CaptchaMenu() {
        this.captchaDigits = new ArrayList<String>(Arrays.asList(DataBase.readResource("captchaDigits.txt").split("_")));
    }

    public static CaptchaMenu getObject() {
        if (object != null) {
            return object;
        }
        return new CaptchaMenu();
    }

    public boolean run() {
        System.out.println("Enter captcha code.");
        System.out.println(obscureCaptchaCode(generateCaptchaCode()));
        ControllerUtils.putInput("captchaCode", captchaCode);
        String input;
        Matcher matcher;
        int wrongAnswers = 0;
        while (true) {
            input = scanner.nextLine().trim();
            if (input.matches("[\\d]+")) {
                ControllerUtils.putInput("inputCaptcha", input);
                result = ControllerUtils.checkCaptchaMatching();
                if (result.equals(UserMessages.SUCCESS)) {
                    object = null;
                    return true;
                } else if (result.equals(UserMessages.FAIL)) {
                    System.out.println("Wrong captcha code! (you have only " + (3 - wrongAnswers) + "chance left!)");
                    wrongAnswers++;
                    if (wrongAnswers >= 2) {
                        object = null;
                        return false;
                    }
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private String generateCaptchaCode() {
        captchaCode = ControllerUtils.getCaptchaCode().getTxt();
        ArrayList<String>[] digitLines = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            digitLines[i] = new ArrayList<>(Arrays.asList(captchaDigits.get(Integer.parseInt(captchaCode.substring(i, i + 1))).trim().split("\n")));
        }
        String createdCaptchaCode = "";
        for (int i = 0; i < digitLines[0].size(); i++) {
            for (int j = 0; j < 4; j++) {
                createdCaptchaCode += digitLines[j].get(i);
            }
            createdCaptchaCode += "\n";
        }
        return createdCaptchaCode;
    }

    private String obscureCaptchaCode(String captchaCode) {
        int random = (int) Math.floor(Math.random() * captchaCode.length());
        String obscuredCaptchaCode = "";
        for (int i = 0; i < captchaCode.length(); i++) {

            if (captchaCode.charAt(i) != '\n' && captchaCode.charAt(i) != '*' && i % 30 == random) {
                obscuredCaptchaCode += "&";
            } else {
                obscuredCaptchaCode += captchaCode.charAt(i);
            }
        }
        return obscuredCaptchaCode;
    }
}
