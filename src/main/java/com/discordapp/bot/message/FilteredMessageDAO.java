package com.discordapp.bot.message;

import java.sql.*;

public class FilteredMessageDAO {

    private String databaseUrl;
    private String databaseUser;
    private String databasePass;
    private final String SQL_INSERT = "INSERT INTO FILTERED_MESSAGES " +
            "(MESSAGE, MESSAGE_AUTHOR_NAME, MESSAGE_AUTHOR_ID,FILTER_FLAG) VALUES (?,?,?,?)";

    public FilteredMessageDAO(String databaseUrl, String user, String pass){
        this.databaseUrl = databaseUrl;
        this.databaseUser = user;
        this.databasePass = pass;

    }

    public void addFilteredMessage(FilteredMessage message){
        Connection con = null;
        PreparedStatement preparedStatement;

        //log information into DB about the flagged message and author.
        try {
            //create session
            con = DriverManager.getConnection(
                    databaseUrl,databaseUser,databasePass);

            preparedStatement = con.prepareStatement(SQL_INSERT);

            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setString(2, message.getMessageAuthor());
            preparedStatement.setString(3, message.getMessageAuthorId());
            preparedStatement.setString(4, message.getFilterFlag());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException throwables) {}
            }
        }
    }

    public int getMessageCount(String author){
        Connection con = null;


        try {
            //create session
            con = DriverManager.getConnection(
                    databaseUrl,databaseUser,databasePass);

            Statement statement = con.createStatement();

            String sql = "SELECT Count(*) AS total FROM filtered_messages WHERE message_author_id = '"+author+"'";

            ResultSet rs = statement.executeQuery(sql);

            rs.next();
            return rs.getInt(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException throwables) {}
            }
        }
        return 0;
    }
}
