 package com.example.firestoreapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {

     public static final String KEY_NAME = "name";
     public static final String KEY_EMAIL = "email";

    private EditText nameET, emailET;
    private Button saveInfo, readInfo, updateInfo, deleteInfo;
    private TextView textView, display;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference friendsReference = db.collection("Users").document("Friends");
    private CollectionReference collectionReference = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.username);
        emailET = findViewById(R.id.email);
        display = findViewById(R.id.text);
        saveInfo = findViewById(R.id.button);
        updateInfo = findViewById(R.id.update);
        readInfo = findViewById(R.id.readButton);
        deleteInfo = findViewById(R.id.delete);

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveDataToFireStore();
                saveDataToDocument();
            }
        });
        readInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //readData();
                getAllDocumentsInCollection();
            }
        });
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        deleteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

     private void saveDataToDocument() {
        String name = nameET.getText().toString();
        String email = nameET.getText().toString();

        Friends friend = new Friends(name, email);
        collectionReference.add(friend);

     }

     private void saveDataToFireStore() {
        String name = nameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();

         Friends friendOne = new Friends();
         friendOne.setName(name);
         friendOne.setEmail(email);



//        Map<String, Object> data = new HashMap<>();
//        data.put(KEY_NAME, name);
//        data.put(KEY_EMAIL, email);

        db.collection("Users")
                .document("Friends")
                .set(friendOne)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Data Added",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Data Failed",Toast.LENGTH_SHORT).show();
                    }
                });
     }
     private void readData() {
        friendsReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString(KEY_NAME);
                    String email = documentSnapshot.getString(KEY_EMAIL);
                    display.setText("Username: " + name + "\nEmail: " + email);
                }
            }
        });
     }
     private void updateDate() {
         String name = nameET.getText().toString().trim();
         String email = emailET.getText().toString().trim();

         Map<String, Object> data = new HashMap<>();
         data.put(KEY_NAME, name);
         data.put(KEY_EMAIL, email);

         friendsReference.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void unused) {
                 Toast.makeText(MainActivity.this, "Data Updated",Toast.LENGTH_SHORT).show();
             }
         });
     }
     private void deleteData() {
        friendsReference.update(KEY_NAME, FieldValue.delete());
        friendsReference.update(KEY_EMAIL,FieldValue.delete());
     }
     private void deleteAll() {
        friendsReference.delete();
     }

     private void getAllDocumentsInCollection() {
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String data = "";

                //Looping through all the documents in the collection
                for (QueryDocumentSnapshot snapshots: queryDocumentSnapshots) {
                    //Log.v("TAGY",snapshots.getString(KEY_NAME));
                    //Log.v("TAGY",snapshots.getString(KEY_EMAIL));

                    //Transforming snapshots into Friends
                    Friends friend = snapshots.toObject(Friends.class);
                    data += "Name :" + friend.getName() + "Email: " + friend.getEmail();
                }
                textView.setText(data);
            }
        });
     }

     @Override
     protected void onStart(){
        super.onStart();
        getAllDocumentsInCollection();

        //listening all the time during the app lifecycle
//         friendsReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//             @Override
//             public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                 if (error != null){
//                     Toast.makeText(MainActivity.this, "Error Found",Toast.LENGTH_SHORT).show();
//                 }
//                 if (value != null && value.exists()) {
//
//                     Friends friend = value.toObject(Friends.class);
////                     String name = value.getString(KEY_NAME);
////                     String email = value.getString(KEY_EMAIL);
//
//                     display.setText("Username: " + friend.getName() + "\nEmail: " + friend.getEmail());
//                 }
//             }
//         });
     }
 }
