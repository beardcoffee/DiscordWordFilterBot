package com.discordapp.bot.chat;

import com.discordapp.bot.command.Command;
import com.discordapp.bot.command.OffenceCountCommand;
import com.discordapp.bot.config.Config;
import com.discordapp.bot.filter.FilterFlag;
import com.discordapp.bot.filter.WordFilter;
import com.discordapp.bot.message.FilteredMessage;
import com.discordapp.bot.message.FilteredMessageDAO;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;
import java.util.stream.Collectors;


public class ChatListener extends ListenerAdapter implements EventListener {

    private WordFilter wordFilter;
    private FilteredMessageDAO filteredMessageDAO;
    private String prefix;
    private List<Command> commands = new ArrayList<>();
    private HashMap<User,Long> userThrottle = new HashMap<>();

    public ChatListener(WordFilter filter, FilteredMessageDAO msgDao, String prefix){
        this.wordFilter = filter;
        this.filteredMessageDAO = msgDao;
        this.prefix = prefix;
        Collections.addAll(commands,
                new OffenceCountCommand(filteredMessageDAO));
    }

    public void onMessageReceived(MessageReceivedEvent event){
        Message message = event.getMessage();
        String msgContent = message.getContentRaw();

        //System.out.println(msgContent);

        FilterFlag messageFlag = wordFilter.checkBlacklistedWord(msgContent);

        if(messageFlag != FilterFlag.PASS){
            //delete flagged message
            message.delete().queue();

            //create filteredMessage obj to store in db
            FilteredMessage filteredMessage = new FilteredMessage(message, messageFlag);

            //log information into DB about the flagged message and author.
            filteredMessageDAO.addFilteredMessage(filteredMessage);
        }else {
            //check for command message
            if (msgContent.indexOf(prefix) == 0) {
                runCommand(event);
            }
        }

    }

    private void runCommand(MessageReceivedEvent event){
        Message message = event.getMessage();
        User user = message.getAuthor();

        //check to see if user is throttled
        if(userThrottle.get(user) != null){
            //4000ms throttle
            if(System.currentTimeMillis() - userThrottle.get(user) <= 4000){
                return;
            }
        }

        userThrottle.put(user, System.currentTimeMillis());

        List<String> commandTokens = Arrays.asList(message.getContentRaw().substring(1).split("\\s"));

        for(Command cmd : commands){
            if(commandTokens.get(0).toLowerCase().equals(cmd.toString())){
                cmd.execute(event);
            }
        }

    }
}
