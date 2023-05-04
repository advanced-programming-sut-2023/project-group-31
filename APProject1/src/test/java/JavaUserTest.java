import controller.ControllerUtils;
import controller.user_menu.RegisterController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import view.ViewUtils;
import view.user_system.RegisterMenu;
import view.user_system.commands.RegisterCommands;
import view.user_system.messages.UserMessages;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.regex.Matcher;

public class JavaUserTest {
    @Test
    public void RegisterMenuTest(){
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

        matcher = RegisterCommands.getMatcher("question pick -q 2 -a yes -c yes", RegisterCommands.PICK_QUESTION);
        ViewUtils.putInHashmap(matcher,RegisterCommands.PICK_QUESTION.getRegex());
        Assertions.assertEquals(UserMessages.SUCCESS,RegisterController.pickQuestion());
    }
}
