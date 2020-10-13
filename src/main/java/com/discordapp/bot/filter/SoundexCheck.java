package com.discordapp.bot.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoundexCheck extends WordCheck{

    public SoundexCheck(){
    }

    public SoundexCheck(ArrayList<String> list, FilterFlag flag){
        super(list,flag);
    }

    public SoundexCheck(String fileName, FilterFlag flag){
        super(fileName, flag);
    }

    public boolean checkWordList(String s){
        List<String> tokenizedMessage = Arrays.asList(s.split("\\s"));

        for(String token : tokenizedMessage){
            if(wordList.contains(soundexEncode(token))){
                return true;
            }
        }

        return false;
    }

    public String soundexEncode(String s){
        char[] x = s.toUpperCase().toCharArray();


        char firstLetter = x[0];

        //RULE 2
        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'B':
                case 'F':
                case 'P':
                case 'V': {
                    x[i] = '1';
                    break;
                }

                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z': {
                    x[i] = '2';
                    break;
                }

                case 'D':
                case 'T': {
                    x[i] = '3';
                    break;
                }

                case 'L': {
                    x[i] = '4';
                    break;
                }

                case 'M':
                case 'N': {
                    x[i] = '5';
                    break;
                }

                case 'R': {
                    x[i] = '6';
                    break;
                }

                default: {
                    x[i] = '0';
                    break;
                }
            }
        }

        //RULE 1
        String output = "" + firstLetter;

        //RULE 3
        for (int i = 1; i < x.length; i++)
            if (x[i] != x[i - 1] && x[i] != '0')
                output += x[i];

        //RULE 4
        output = output + "0000";
        return output.substring(0, 4);
    }
}
