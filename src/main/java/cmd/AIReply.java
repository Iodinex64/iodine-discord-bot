package cmd;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.alicebot.ab.*;

public class AIReply {

    static Chat chatSession;

    public static void Init() {
        //Resources must be in root where FatJar is.
        Bot bot = new Bot("iodine", "resources/main", "chat");
        chatSession = new Chat(bot);
    }

    public static void CleverReply(MessageReceivedEvent event) {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                event.getChannel().sendTyping().queue();
            }
        }, 600);
        //String msg = Utils.RemoveWholeTag(event.getMessage().getContentRaw()); //really want to use this but functionality from JDA is broken it seems
        String msg = event.getMessage().getContentRaw().replace("!chat", "");
        String re = chatSession.multisentenceRespond(msg);
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                event.getChannel().sendMessage(re).queue();
            }
        }, 3400);
    }
}