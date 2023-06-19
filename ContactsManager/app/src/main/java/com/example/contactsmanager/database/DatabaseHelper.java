package com.example.contactsmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.contactsmanager.database.entity.Contact;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "contactDB";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contact.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contact.TABLE_NAME);
        onCreate(db);
    }

    //Insert Values Into Database
    public long InsertIntoTable(String name, String email) {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contact.COLUMN_NAME, name);
        values.put(Contact.COLUMN_EMAIL, email);

        long id = sql.insert(Contact.TABLE_NAME,null, values);
        sql.close();
        return id;
    }

    //Retrieve Values From Database
    public Contact getContact(long id) {
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cursor = sql.query(Contact.TABLE_NAME,
                new String[] {
                        Contact.COLUMN_ID,
                        Contact.COLUMN_NAME,
                        Contact.COLUMN_EMAIL},
                Contact.COLUMN_ID + "=?",
                new String[] {
                        String.valueOf(id)
                },
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact(
                cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)),
                cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)));

        cursor.close();
        return contact;
    }

    //Retrieve All Contacts
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Contact.TABLE_NAME + " ORDER BY " + Contact.COLUMN_ID + " DESC";
        SQLiteDatabase sql = this.getWritableDatabase();
        Cursor cursor = sql.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)));
                contact.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)));

                contacts.add(contact);
            }
            while (cursor.moveToNext());
        }
        sql.close();
        return contacts;
    }

    public int updateContact() {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contact.COLUMN_NAME, Contact.getName());
        values.put(Contact.COLUMN_EMAIL, Contact.getEmail());
        return sql.update(Contact.TABLE_NAME, values, Contact.COLUMN_ID + " = ? ", new String[] {String.valueOf(Contact.getId())});
    }

    public void deleteContact() {
        SQLiteDatabase sql = this.getWritableDatabase();
        sql.delete(Contact.TABLE_NAME, Contact.COLUMN_ID + " = ?",
                new String[] {String.valueOf(Contact.getId())});
        sql.close();
    }
}
