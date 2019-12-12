package cmd;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Funny {

    public static void Tuck(MessageReceivedEvent event) {
        Member mem = event.getMessage().getMentionedMembers().get(0);
        event.getChannel().sendMessage("*Tucks " + mem.getAsMention() + ".*").queue();
    }
}
