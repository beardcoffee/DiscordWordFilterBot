package com.discordapp.bot.message;

import com.discordapp.bot.filter.FilterFlag;
import net.dv8tion.jda.api.entities.Message;

public class FilteredMessage {

    private String message;
    private String messageAuthor;
    private String messageAuthorId;
    private String filterFlag;

    public FilteredMessage(Message message, FilterFlag filterFlag){
        this.message = message.getContentRaw();
        this.messageAuthor = message.getAuthor().getAsTag();
        this.messageAuthorId = message.getAuthor().getId();
        this.filterFlag = filterFlag.toString();
    }

    public String getMessage() {
        return message;
    }

    public String getMessageAuthor(){
        return messageAuthor;
    }

    public String getMessageAuthorId(){
        return messageAuthorId;
    }

    public String getFilterFlag(){
        return filterFlag;
    }
}
