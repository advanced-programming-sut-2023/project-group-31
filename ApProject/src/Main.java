import controller.user_menu.RegisterController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Main {
    public static void main(String[] args) {
        Matcher matcher= Pattern.compile("-s (?<mamad>1*)").matcher("-s ");
        matcher.matches();
        System.out.println(matcher.group("mamad")=="");

    }

}


