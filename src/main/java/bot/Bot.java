package bot;

import cmd.AIReply;
import com.kdotj.simplegiphy.SimpleGiphy;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    //version of bot to be printed in info
    public static String botVersion = "v1.0";
    //reference to bot object
    public static JDA jda;
    //prefix used to check for commands
    static String cmdPrefix = "!";

    public static void main(String[] args) throws LoginException {
        //You will need to pass tokens in with cmd: first discord, then giphy.
        String discordToken = args[0];
        String giphyToken = args[1];
        AIReply.Init();
        SimpleGiphy.setApiKey(giphyToken);
        jda = new JDABuilder(AccountType.BOT).setToken(discordToken).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("your messages closely..."));
        jda.addEventListener(new Command());
    }
}