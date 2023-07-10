package com.example.journalapp.utilities;

import android.app.Application;

public class JournalUser extends Application {

    private String username;
    private String userID;
    private static JournalUser instance;


    //Singleton Design Pattern
    public static JournalUser getInstance() {
        if (instance == null) {
            instance = new JournalUser();
        }
        return instance;
    }

    public JournalUser() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public static void setInstance(JournalUser instance) {
        JournalUser.instance = instance;
    }
}
