package com.example.contactsmanager.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.contactsmanager.database.entity.Contact;
import com.example.contactsmanager.database.entity.ContactDAO;

@Database(entities = {Contact.class}, version = 1)
public abstract class DB extends RoomDatabase {

    //Link the database to the DAO
    public abstract ContactDAO getContactDAO();
}
