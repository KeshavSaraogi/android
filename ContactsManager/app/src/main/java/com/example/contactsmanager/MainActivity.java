package com.example.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.contactsmanager.Adapter.ContactsAdapter;
import com.example.contactsmanager.database.entity.Contact;


public class MainActivity extends AppCompatActivity {

    private ContactsAdapter contactsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addAndEditContacts(boolean b, Contact contact, int positions) {
    }
}