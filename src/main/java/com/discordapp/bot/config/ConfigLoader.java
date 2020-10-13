package com.discordapp.bot.config;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

public class ConfigLoader {

    //private static String fileName;
    public ConfigLoader(){

    }

    public void saveConfig(String fileName, String json){
        try {
            Files.write(Paths.get(fileName), json.getBytes());
        }catch(IOException e){
            //LoggingUtil.get().log(Level.SEVERE, "Config save IOException");
        }
    }

    public <T>T loadConfig(String fileName, Class<T> type){
        byte[] encodedJson = new byte[0];
        try {
            encodedJson = Files.readAllBytes(Paths.get(fileName));
        } catch (IOException e) {
            //LoggingUtil.get().log(Level.SEVERE, "Config load IOException");
        }

        Gson gson = new Gson();

        String decodedJson = new String(encodedJson, Charset.defaultCharset());

        return gson.fromJson(decodedJson, type);
    }

}

