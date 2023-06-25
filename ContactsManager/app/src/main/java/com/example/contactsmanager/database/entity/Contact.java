package com.example.contactsmanager.database.entity;

public class Contact {

    public static final String TABLE_NAME = "name";
    public static final String COLUMN_ID  = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NAME = "column";

    private static String name;
    private static String email;
    private static int id;

    public Contact() {}

    public Contact(String name, String email, int id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Creating SQLite Queries
    public static final String CREATE_TABLE = "CREATE TABLE + "
            + TABLE_NAME +"( "
            + COLUMN_ID + "INTEGER PRIMARY KEY AUTO INCREMENT, "
            + TABLE_NAME + " TEXT,"
            + COLUMN_EMAIL + " DATETIME DEFAULT CURRENT TIMESTAMP"
            + ")";

}
