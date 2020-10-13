package com.discordapp.bot.command;

import com.discordapp.bot.message.FilteredMessageDAO;
import com.discordapp.bot.utils.TextUtil;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class OffenceCountCommand extends Command{

    private FilteredMessageDAO filteredMessageDAO;

    public OffenceCountCommand(FilteredMessageDAO filteredMessageDAO){
        this.filteredMessageDAO = filteredMessageDAO;
        this.commandName = "offences";
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        Message command = event.getMessage();

        if(parsable(command)){
            User mentionedUser = command.getMentionedUsers().get(0);
            String userId = mentionedUser.getId();
            int count = filteredMessageDAO.getMessageCount(userId);

            event.getChannel().sendMessage(TextUtil.greenText(mentionedUser.getAsTag()+ " has " + count + " chat offences!")).queue();

        }else{
            //can't run command
        }

    }

    @Override
    protected boolean parsable(Message message) {

        if(message.getContentRaw().split("\\s").length == 2){
            if(message.getMentionedUsers().size() == 1){
                return true;
            }
        }
        return false;
    }
}
