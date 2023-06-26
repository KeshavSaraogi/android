package com.example.contactsmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactsmanager.database.DB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.contactsmanager.database.entity.Contact;
import com.example.contactsmanager.Adapter.ContactsAdapter;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    private ContactsAdapter contactsAdapter;
    private final ArrayList<Contact> contactArrayList  = new ArrayList<>();
    private DB database;
    // private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Favorite Contacts");

        RecyclerView recyclerView = findViewById(R.id.recycler_view_contacts);

        //ROOM Database Callbacks
        RoomDatabase.Callback callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                CreateContact("Bill Gates","bill@microsoft.com");
                CreateContact("Tim Cook","tim@apple.com");
                CreateContact("Mark Zukerburg","mark@meta.com");

                Log.i("TAG","Database Has Been Created");
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
                Log.i("TAG", "Database Has Been Opened");
            }
        };

        database = Room.databaseBuilder(
                getApplicationContext(),
                DB.class,
                "contact")
                .addCallback(callback)
                .build();

        //db = new DatabaseHelper(this);
        DisplayAllContactsInBackground();

        contactsAdapter = new ContactsAdapter(this, contactArrayList,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAndEditContacts(false, null, -1);
            }
        });
    }

    public void addAndEditContacts(final boolean isUpdated,final Contact contact,final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.layout_add_contact,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(view);

        TextView contactTitle = view.findViewById(R.id.new_contact_title);
        final EditText newContact = view.findViewById(R.id.name);
        final EditText contactEmail = view.findViewById(R.id.email);

        contactTitle.setText(!isUpdated ? "Add New Contact" : "Edit Contact");

        if (isUpdated && contact != null){
            newContact.setText(contact.getName());
            contactEmail.setText(contact.getEmail());
        }

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(isUpdated ? "Update" : "Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (isUpdated)
                                    DeleteContact(contact, position);
                                else
                                    dialogInterface.cancel();
                            }
                        }
                );

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(newContact.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please Enter a Name", Toast.LENGTH_SHORT).show();
                    return;
                }else
                    alertDialog.dismiss();
                if (isUpdated && contact != null)
                    UpdateContact(newContact.getText().toString(), contactEmail.getText().toString(),position);
                else
                    CreateContact(newContact.getText().toString(), contactEmail.getText().toString());
            }
        });
    }

    private void DeleteContact(Contact contact, int position) {
        contactArrayList.remove(position);
        database.getContactDAO().deleteContact(contact);;
        contactsAdapter.notifyDataSetChanged();
    }

    private void UpdateContact(String name, String email, int position){
        Contact contact = contactArrayList.get(position);
        contact.setName(name);
        contact.setEmail(email);
        database.getContactDAO().updateContact(contact);
        contactArrayList.set(position, contact);
        contactsAdapter.notifyDataSetChanged();
    }

    private void CreateContact(String name, String email){
        long id = database.getContactDAO().addContact(new Contact(name, email, 0));
        Contact contact = database.getContactDAO().getContact(id);
        if (contact != null){
            contactArrayList.add(0, contact);
            contactsAdapter.notifyDataSetChanged();
        }
    }

    private void DisplayAllContactsInBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //Background Task
                contactArrayList.addAll(database.getContactDAO().getContacts());

                //After The Completion of Background Task
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        contactsAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionsSettings)
            return true;
        return super.onOptionsItemSelected(item);
    }
}