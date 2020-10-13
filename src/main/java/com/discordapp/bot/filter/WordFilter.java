package com.discordapp.bot.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordFilter {

    private final List<WordCheck> checks = new ArrayList<>();

    public WordFilter(){
        Collections.addAll(checks,
                new TokenWordCheck("TOKEN_BLACKLIST.TXT", FilterFlag.TOKEN_WORD_FILTER),
                new StrictWordCheck("STRICT_BLACKLIST.TXT", FilterFlag.STRICT_WORD_FILTER),
                new SoundexCheck("SOUNDEX_BLACKLIST.TXT", FilterFlag.SOUNDEX_WORD_FILTER));
    }

    private String preProcess(String s) {
        String message = s;

        //Remove leet speak
        message = message.replaceAll("1", "i")
                .replaceAll("!", "i")
                .replaceAll("3", "e")
                .replaceAll("4", "a")
                .replaceAll("@", "a")
                .replaceAll("5","s")
                .replaceAll("0", "o");


        //Remove special characters and excess spacing
        message.replaceAll("([^A-Za-z0-9\\s])","").replaceAll("\\s+"," ");

        //Make lowercase
        message.toLowerCase();

        return message;
    }

    public FilterFlag checkBlacklistedWord(String s) {
        String message = preProcess(s);

        for (WordCheck filterCheck : checks){
            if(filterCheck.checkWordList(message)){
                return filterCheck.flag;
            }
        }

        return FilterFlag.PASS;
    }
}
