package com.discordapp.bot.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

    protected String commandName;

    public Command(){}
    public String getCommandName(){return this.commandName;}

    public abstract void execute(MessageReceivedEvent event);

    protected abstract boolean parsable(Message message);

    @Override
    public String toString() {
        return this.commandName;
    }
}
