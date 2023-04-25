import controller.user_menu.RegisterController;
import model.DataBase;
import view.user_system.MenuSwitcher;

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
        DataBase.loadingApp();
        MenuSwitcher.run();

    }

}


