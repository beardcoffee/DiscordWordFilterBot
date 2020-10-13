package com.discordapp.bot.utils;

import com.discordapp.bot.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.List;


public class TextUtil {



    public static String greenText(String input){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("```css\n");
        stringBuilder.append(input + "\n");
        stringBuilder.append("```");

        return stringBuilder.toString();
    }

    public static String wrappedText(String input){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("`\n");
        stringBuilder.append(input + "\n");
        stringBuilder.append("`");

        return stringBuilder.toString();
    }

    public static String embed(String input){

        return "[" + input + "](https://.#) ";
    }
}
