package com.discordapp.bot.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenWordCheck extends WordCheck{


    public TokenWordCheck(){
    }

    public TokenWordCheck(ArrayList<String> list, FilterFlag flag){
        super(list, flag);
    }

    public TokenWordCheck(String fileName, FilterFlag flag){
        super(fileName, flag);
    }

    public boolean checkWordList(String s){

        List<String> tokenizedMessage = Arrays.asList(s.split("\\s"));

        for(String token: tokenizedMessage){
            if(wordList.contains(token)){
                return true;
            }
        }

        return false;
    }
}
