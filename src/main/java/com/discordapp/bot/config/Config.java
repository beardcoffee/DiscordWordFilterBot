package com.discordapp.bot.config;

import java.util.HashSet;
import java.util.Set;

public class Config {

    private String token;
    private String databaseUrl;
    private String databaseUser;
    private String databasePass;
    private String prefix;
    private Set<String> roles = new HashSet<>();

    public Config(){
    }

    public String getToken(){
        return token;
    }

    public String getDatabaseUrl(){return databaseUrl;}

    public String getDatabaseUser(){ return databaseUser;}

    public String getDatabasePass(){return databasePass;}

    public String getPrefix(){return prefix;}

    public Set<String> getRoles() {
        return roles;
    }

}