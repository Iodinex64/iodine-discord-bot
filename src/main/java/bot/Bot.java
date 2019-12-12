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

    //prefix used to check for commands
    static String cmdPrefix = "!";
    static String discordToken = "NjUwMzUyNjk1ODEwNzE5NzQ0.XeeOEw.WQnhoPKSDgo_Qz1hYRG5JRbEINk";
    static String giphyToken = "0LyEMkppjZQHR75P0KtKLb3Bx7r9rKQ5";

    //version of bot to be printed in info
    public static String botVersion = "v1.0";

    //reference to bot object
    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        AIReply.Init();
        SimpleGiphy.setApiKey(giphyToken);
        jda = new JDABuilder(AccountType.BOT).setToken(discordToken).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("your messages closely..."));
        jda.addEventListener(new Command());
    }
}