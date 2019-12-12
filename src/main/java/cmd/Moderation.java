package cmd;

import bot.Bot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.utils.PermissionUtil;

import java.util.List;

public class Moderation extends ListenerAdapter {

    public static void AutoMod(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw().toLowerCase();
        if (msg.contains("nigger")
                || msg.contains("faggot")) {
            InternalMute(event, 1, "h");
        }
    }

    public static void BanCMD(MessageReceivedEvent event, String days) {
        try {
            Member mem = event.getMessage().getMentionedMembers().get(0);
            mem.ban(Integer.parseInt(days)).queue();
            event.getChannel().sendMessage("Successfully banned " + mem.getAsMention() + ".").queue();
        } catch (HierarchyException e) {
            event.getChannel().sendMessage("I can't let you ban this person!").queue();
        }
    }

    public static void UnBanCMD(MessageReceivedEvent event, String id) {
        String userID = Utils.RemoveTagSurrounds(id);
        User usr = Bot.jda.getUserById(userID);
        try {
            event.getGuild().unban(userID).queue();
        } catch (Exception e) {
            UnBanFail(event);
        }
        if (usr != null) {
            event.getChannel().sendMessage("Alright, fine. I unbanned " + usr.getAsMention() + " for you. Happy?").queue();
        } else {
            event.getChannel().sendMessage("Uhh... I couldn't get the user's name. Something probably went wrong.").queue();

        }
    }

    public static void KickCMD(MessageReceivedEvent event) {
        try {
            Member mem = event.getMessage().getMentionedMembers().get(0);
            mem.kick().queue();
            event.getChannel().sendMessage("Successfully kicked " + mem.getAsMention() + ".").queue();
        } catch (HierarchyException e) {
            event.getChannel().sendMessage("I can't let you kick this person!").queue();
        }
    }

    public static void KickFail(MessageReceivedEvent event) {
        event.getChannel().sendMessage("That's not how you kick someone, idiot. Try:\n`!kick <User>`").queue();
    }

    public static void BanFail(MessageReceivedEvent event) {
        event.getChannel().sendMessage("That's not how you ban someone, idiot. Try:\n`!ban <User> <Days (optional)>`").queue();
    }

    public static void UnBanFail(MessageReceivedEvent event) {
        event.getChannel().sendMessage("That's not how you unban someone, idiot. Try:\n`!unban <UserID> (user ID must be numbers only!`").queue();
    }

    private static void InternalMute(MessageReceivedEvent event, int seconds, String mult) {
        try {
            Member mem = event.getGuild().getMember(event.getMessage().getAuthor());
            Role mute = event.getGuild().getRolesByName("Muted", true).get(0);
            int timeMult = 1;
            event.getMessage().delete().queue();
            if (mem != null && mute != null) {
                if (!mem.getRoles().contains(mute)) {
                    event.getChannel().sendMessage("Shut up, " + mem.getAsMention() + ".").queue();
                    event.getGuild().addRoleToMember(mem, mute).complete();
                    //un-mute after time
                    if (mult != null) {
                        switch (mult) {
                            case "m":
                                timeMult = 60;
                                break;
                            case "h":
                                timeMult = 360;
                                break;
                            case "d":
                                timeMult = 8640;
                                break;
                            default:
                                break;
                        }
                    }
                    new java.util.Timer().schedule(new java.util.TimerTask() {
                        @Override
                        public void run() {
                            if (mem.getRoles().contains(mute)) {
                                event.getChannel().sendMessage("Time's up. You can talk now, " + mem.getAsMention() + ". Behave!").queue();
                                event.getGuild().removeRoleFromMember(mem, mute).complete();
                            }
                        }
                        //forcing as absolute value to prevent negative numbers
                        //from potential integer overflow
                    }, Math.abs((seconds * 1000) * timeMult));
                }
            }
        } catch (Exception e) {
            Mute.MuteCMD3(event);
        }
    }
}