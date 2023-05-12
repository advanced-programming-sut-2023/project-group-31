import controller.user_system.LoginController;
import controller.user_system.RegisterController;
import model.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.ViewUtils;
import view.user_system.commands.LoginCommands;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.UserMessages;

import java.util.regex.Matcher;

public class JavaUserTest {
    @Test
    public void RegisterControllerTest(){
        DataBase.setDataBase(Mockito.mock(DataBase.class));
        DataBase.getDataBase().setSlogans();
        DataBase.getDataBase().setRecoveryQuestions();
        Matcher matcher;
        matcher = RegisterCommands.getMatcher("user create -u userna11111me -p 123456Aa$ 123456Aa$ –email momirzadi200@gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.SUCCESS, RegisterController.userCreate());
        matcher = RegisterCommands.getMatcher("user create -u userna11111me -p 123456Aa$ 123456Aa$ –email momirzadi200@gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.USER_EXITS_BEFORE,RegisterController.userCreate());
        matcher = RegisterCommands.getMatcher("user create -u uuuu&& -p 123456Aa$ 123456Aa$ –email momirzadi200@gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.INVALID_FORMAT,RegisterController.userCreate());
        matcher = RegisterCommands.getMatcher("user create -u  -p 123456Aa$ 123456Aa$ –email momirzadi200@gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.NOT_ENOUGH_MESSAGES,RegisterController.userCreate());
        matcher = RegisterCommands.getMatcher("user create -u mamad -p 123456Aa$ 123456Aa$ –email momirzadi200@gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.SUCCESS,RegisterController.userCreate());
        matcher = RegisterCommands.getMatcher("user create -u mamad -p 1234$$faF56 1234 –email momirzadi200@gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.PASSWORD_NOT_MATCH,RegisterController.userCreate());
        matcher = RegisterCommands.getMatcher("user create -u mamad -p 1234$$faF56 1234 –email @gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.INVALID_FORMAT,RegisterController.userCreate());
        matcher = RegisterCommands.getMatcher("user create -u mamad -p random –email fdf@gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.RANDOM_PASSWORD,RegisterController.userCreate());
        matcher = RegisterCommands.getMatcher("user create -u mamadi -p mamadA34 mamadA34 –email fdf@gamil.com -s \"we do it\"", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.PASSWORD_IS_WEAK,RegisterController.userCreate());



        matcher = LoginCommands.getMatcher("user login -u mamad -p 123456Aa$", LoginCommands.LOGIN);
        ViewUtils.putInHashmap(matcher,LoginCommands.LOGIN.getRegex());
        Assertions.assertEquals(UserMessages.SUCCESS, LoginController.loginUser());

        matcher = LoginCommands.getMatcher("user login -u mamad -p 123456A$", LoginCommands.LOGIN);
        ViewUtils.putInHashmap(matcher,LoginCommands.LOGIN.getRegex());
        Assertions.assertEquals(UserMessages.PASSWORD_IS_NOT_CORRECT,LoginController.loginUser());

    }

    public void loginControllerTest(){

        Matcher matcher;
        matcher = RegisterCommands.getMatcher("user login -u mamad -p 123456Aa$", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.SUCCESS,RegisterController.userCreate());

        matcher = RegisterCommands.getMatcher("user login -u mamad -p 123456A$", RegisterCommands.REGISTER);
        ViewUtils.putInHashmap(matcher,RegisterCommands.REGISTER.getRegex());
        Assertions.assertEquals(UserMessages.SUCCESS,RegisterController.userCreate());

    }
}
