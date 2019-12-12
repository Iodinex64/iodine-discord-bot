package cmd;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Mentioned extends ListenerAdapter {
    //reply priority:
    //hello > goodbye > question > generic
    public static void MentionedCMD(MessageReceivedEvent event) {
        event.getChannel().sendTyping().queue();
        String msgContent = event.getMessage().getContentRaw();
        String userID = event.getMessage().getAuthor().getAsMention();
        //greetings
        if (msgContent.toLowerCase().contains("hello ")
                || msgContent.toLowerCase().contains("hi ")
                || msgContent.toLowerCase().contains(" hi")
                || msgContent.toLowerCase().contains("hey ")) {
            event.getChannel().sendMessage("Hello " + userID + "!").queue();
        //farewells
        } else if (msgContent.toLowerCase().contains("bye ")
                || msgContent.toLowerCase().contains(" bye")
                || msgContent.toLowerCase().contains("seeya ")) {
            event.getChannel().sendMessage("Goodbye " + userID + "!").queue();
         //questions
        } else if (msgContent.toLowerCase().charAt(msgContent.length() - 1) == '?'
                || msgContent.toLowerCase().contains("? ")) {
            event.getChannel().sendMessage("I don't know!").queue();
         //generic
        } else {
            event.getChannel().sendMessage("who tag <:stinky:652104373010169865>").queue();
        }
    }
}