package cmd;

import bot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class Help {

    public static void HelpCMD(MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("List of commands I know:");
        eb.setColor(Color.cyan);
        eb.addField("!bunny", "Displays a random rabbit GIF from Giphy.", false);
        eb.addField("!info", "Displays information about this bot", false);
        eb.addField("!mute", "Silences a tagged user for an optional amount of time.", false);
        eb.addField("!kick", "Kicks a tagged user from the server.", false);
        eb.addField("!ban", "Bans a tagged user from the server.", false);
        eb.addField("!unban", "Unbans a tagged user from the server.", false);
        eb.addField("!tuck", "Tucks a tagged user in. :)", false);
        eb.addField("!help", "Displays this panel of commands", false);
        eb.addField("!chat", "Have a conversation with me. Or well, try to, at least.", false);
        eb.setFooter("By Iodine.", "https://cdn.discordapp.com/avatars/147109505438711808/4f0b05cc092450fcd5a5fef9590d7015.webp?size=128");
        event.getChannel().sendMessage(eb.build()).queue();
    }
}
