package com.example.firebasesetup;

public class DatabaseModel {

    String username;
    String password;

    //Default Constructor is always required for Firebase, specially for DataSnapshot.getValue(CLASS);
    public DatabaseModel() {}

    public DatabaseModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
