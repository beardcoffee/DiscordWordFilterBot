package com.discordapp.bot;

import com.discordapp.bot.chat.ChatListener;
import com.discordapp.bot.config.Config;
import com.discordapp.bot.config.ConfigLoader;
import com.discordapp.bot.filter.WordFilter;
import com.discordapp.bot.message.FilteredMessageDAO;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public static void main(String[] args){
        ConfigLoader configLoader = new ConfigLoader();
        Config config = configLoader.loadConfig("config.json", Config.class);

        FilteredMessageDAO filteredMessageDAO = new FilteredMessageDAO(
                config.getDatabaseUrl(),
                config.getDatabaseUser(),
                config.getDatabasePass()
        );

        try {
            //Create JDABuilder with low memory profile settings, only enable message events.
            JDABuilder.createLight(config.getToken(), GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(new ChatListener(new WordFilter(), filteredMessageDAO, config.getPrefix()))
                    //Create JDA instance.
                    .build();

        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

}
