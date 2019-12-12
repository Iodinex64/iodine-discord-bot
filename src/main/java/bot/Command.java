package bot;

import cmd.*;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class Command extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            //Init
            if (event.getAuthor().isBot()) {
                return;
            }
            String msg = event.getMessage().getContentRaw();
            String[] args = msg.split("\\s+");

            //These methods check every message
            Moderation.AutoMod(event);

            //Command 0: replies with a random gif tagged with "bunny" from Giphy API.
            if (args[0].equalsIgnoreCase(Bot.cmdPrefix + "bunny")) {
                cmd.Bunny.bunnyCMD(event);

                //Command 1: displays an info card all about this bot.
            } else if (args[0].equalsIgnoreCase(Bot.cmdPrefix + "info")) {
                cmd.Info.InfoCMD(event);

                //Command 2: mute a user for 'x' amount of time. (time optional)
            } else if (args[0].equalsIgnoreCase(Bot.cmdPrefix + "mute")) {
                if (Objects.requireNonNull(event.getGuild().getMember(event.getAuthor())).hasPermission(Permission.KICK_MEMBERS)) {
                    if (args.length == 2) {
                        cmd.Mute.MuteCMD1(event, args[1]);
                    } else if (args.length == 4) {
                        if (args[3] != null) {
                            cmd.Mute.MuteCMD2(event, args[1], args[2], args[3]);
                        }
                    } else {
                        cmd.Mute.MuteCMD3(event);
                    }
                }
                //Command 3: kick a user from the server.
            } else if (args[0].equalsIgnoreCase(Bot.cmdPrefix + "kick")) {
                if (Objects.requireNonNull(event.getGuild().getMember(event.getAuthor())).hasPermission(Permission.KICK_MEMBERS)) {
                    if (args.length == 2) {
                        Moderation.KickCMD(event);
                    } else {
                        Moderation.KickFail(event);
                    }
                }
                //Command 4: ban a user from the server.
            } else if (args[0].equalsIgnoreCase(Bot.cmdPrefix + "ban")) {
                if (Objects.requireNonNull(event.getGuild().getMember(event.getAuthor())).hasPermission(Permission.BAN_MEMBERS)) {
                    if (args.length == 3) {
                        if (args[2] != null) {
                            Moderation.BanCMD(event, args[2]);
                        } else {
                            Moderation.BanCMD(event, "0");
                        }
                    } else {
                        Moderation.BanFail(event);
                    }
                }
                //Command 5: unban a user in the server.
            } else if (args[0].equalsIgnoreCase(Bot.cmdPrefix + "unban")) {
                if (Objects.requireNonNull(event.getGuild().getMember(event.getAuthor())).hasPermission(Permission.BAN_MEMBERS)) {
                    if (args.length == 2) {
                        Moderation.UnBanCMD(event, args[1]);
                    } else {
                        Moderation.UnBanFail(event);
                    }
                }
                //Command 6: Tuck a user
            } else if (args[0].equalsIgnoreCase(Bot.cmdPrefix + "tuck")) {
                Funny.Tuck(event);
                //Command 7: Command list
            } else if (args[0].equalsIgnoreCase(Bot.cmdPrefix + "help")) {
                Help.HelpCMD(event);
                //Bot replies
            } else if (event.getMessage().getContentRaw().contains(Bot.jda.getSelfUser().getAsMention())) {
                AIReply.CleverReply(event);
            }
        } catch (Exception e) {
            event.getChannel().sendMessage("I can't do that for some reason...").queue();
        }
    }
}