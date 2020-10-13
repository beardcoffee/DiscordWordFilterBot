package com.discordapp.bot.filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class WordCheck {

    protected List<String> wordList;
    protected FilterFlag flag;


    public WordCheck() {

    }

    public WordCheck(ArrayList<String> list, FilterFlag flag){
        this.flag = flag;
        this.wordList = list;
    }

    public WordCheck(String fileName, FilterFlag flag){
        this.flag = flag;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            wordList = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean checkWordList(String s);

    public FilterFlag getFlag(){
        return this.flag;
    }

}
