package cmd;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.alicebot.ab.*;
import java.io.File;

//CHAT BOT WORKS!!! Just need to be able to relocate AIML files
public class AIReply {

    static Chat chatSession;

    public static void Init() {
        String resourcePath = GetRscPath();
        Bot bot = new Bot("iodine", resourcePath, "chat");
        chatSession = new Chat(bot);
    }

    public static void CleverReply(MessageReceivedEvent event) {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                event.getChannel().sendTyping().queue();
            }
        }, 600);
        String msg = Utils.RemoveWholeTag(event.getMessage().getContentRaw());
        String re = chatSession.multisentenceRespond(msg);
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                event.getChannel().sendMessage(re).queue();
            }
        }, 3400);
    }

    private static String GetRscPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        return path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
    }
}