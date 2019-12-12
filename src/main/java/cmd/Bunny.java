package cmd;

import com.kdotj.simplegiphy.SimpleGiphy;
import com.kdotj.simplegiphy.data.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class Bunny extends ListenerAdapter {
    public static void bunnyCMD(MessageReceivedEvent event) {
        event.getChannel().sendTyping().queue();
        GiphyListResponse re = SimpleGiphy.getInstance().search("bunny", "35", "0", "pg-13");
        Random rand = new Random();
        int ranInt = rand.nextInt(re.getData().size());
        String bunnyLink = re.getData().get(ranInt).getBitlyGifUrl();
        if (bunnyLink != null) {
            event.getChannel().sendMessage(bunnyLink).queue();
        } else {
            event.getChannel().sendMessage("I couldn't find any bunnies!").queue();
        }
    }
}
