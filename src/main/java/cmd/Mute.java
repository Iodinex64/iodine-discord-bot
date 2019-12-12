package cmd;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Mute extends ListenerAdapter {

    //Command version 1 - indefinite mute
    public static void MuteCMD1(MessageReceivedEvent event, String userName) {
        //if (event.getAuthor().)
        try {
            event.getChannel().sendTyping().queue();
            Member mem = event.getMessage().getMentionedMembers().get(0);
            Role mute = event.getGuild().getRolesByName("Muted", true).get(0);
            if (mem != null && mute != null) {
                if (!mem.getRoles().contains(mute)) {
                    event.getChannel().sendMessage("Shut up, " + userName + ".").queue();
                    event.getGuild().addRoleToMember(mem, mute).complete();
                } else {
                    event.getChannel().sendMessage("Alright, you can talk again, " + userName + ". Behave!").queue();
                    event.getGuild().removeRoleFromMember(mem, mute).complete();
                }
            }
        } catch (Exception e) {
            MuteCMD3(event);
        }
    }

    //Command version 2 - timed mute
    public static void MuteCMD2(MessageReceivedEvent event, String userName, String seconds, String mult) {
        try {
            event.getChannel().sendTyping().queue();
            Member mem = event.getMessage().getMentionedMembers().get(0);
            Role mute = event.getGuild().getRolesByName("Muted", true).get(0);
            int timeMult;
            if (mem != null && mute != null) {
                if (!mem.getRoles().contains(mute)) {
                    event.getChannel().sendMessage("Shut up, " + userName + ".").queue();
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
                                timeMult = 1;
                                break;
                        }
                    } else {
                        timeMult = 1;
                    }
                    new java.util.Timer().schedule(new java.util.TimerTask() {
                        @Override
                        public void run() {
                            if (mem.getRoles().contains(mute)) {
                                event.getChannel().sendMessage("Time's up. You can talk now, " + userName + ". Behave!").queue();
                                event.getGuild().removeRoleFromMember(mem, mute).complete();
                            }
                        }
                        //forcing as absolute value to prevent negative numbers
                        //from potential integer overflow
                    }, Math.abs((Integer.parseInt(seconds) * 1000) * timeMult));
                } else {
                    event.getChannel().sendMessage(userName + " is already muted, idiot. To unmute:\n`!mute <User>`").queue();
                }
            }
        } catch (Exception e) {
            MuteCMD3(event);
        }
    }

    //Command version 3 - incorrectly typed mute response
    public static void MuteCMD3(MessageReceivedEvent event) {
        event.getChannel().sendMessage("That's not how you mute someone! Write it properly.\n`!mute <User> <Time> <Time Format (s/m/h/d)>`").queue();
    }
}
