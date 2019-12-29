package cmd;

import bot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class Info extends ListenerAdapter {

    public static void InfoCMD(MessageReceivedEvent event) {
        event.getChannel().sendTyping().queue();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("IodineBot", null);
        eb.setColor(Color.cyan);
        eb.addField("Version", Bot.botVersion, false);
        eb.setDescription("I am a bot written by my owner Iodine, intended for personal use." +
                "\nI was written in Java 11, using Gradle and Dv8tion's JDA. " +
                "\nDevelopment began on me on the 1st of December, 2019.");
        eb.addField("GitHub", "https://github.com/Iodinex64/iodine-discord-bot/", false);
        eb.addField("Artwork",  "My current profile picture was designed by Thyth#8208", false);
        eb.setFooter("By Iodine.", "https://cdn.discordapp.com/avatars/147109505438711808/4f0b05cc092450fcd5a5fef9590d7015.webp?size=128");
        event.getChannel().sendMessage(eb.build()).queue();
    }
}
