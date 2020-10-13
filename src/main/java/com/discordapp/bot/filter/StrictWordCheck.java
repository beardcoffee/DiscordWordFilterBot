package com.discordapp.bot.filter;

import java.util.ArrayList;

public class StrictWordCheck extends WordCheck{


    public StrictWordCheck(){
    }

    public StrictWordCheck(ArrayList<String> list, FilterFlag flag){
        super(list,flag);
    }

    public StrictWordCheck(String fileName, FilterFlag flag){
        super(fileName, flag);
    }

    public boolean checkWordList(String s){
        String message = s.replaceAll("\\s", "");
        for(int i = 0; i < wordList.size(); i++){
            if(message.contains(wordList.get(i))){
                return true;
            }
        }
        return false;
    }
}
