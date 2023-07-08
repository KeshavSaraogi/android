 package com.example.firestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {

     public static final String KEY_NAME = "name";
     public static final String KEY_EMAIL = "email";

    private EditText nameET, emailET;
    private Button saveInfo, readInfo;
    private TextView text, display;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference friendsReference = db.collection("Users").document("Friends");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.username);
        emailET = findViewById(R.id.email);
        display = findViewById(R.id.text);
        saveInfo = findViewById(R.id.button);
        readInfo = findViewById(R.id.readButton);

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFireStore();
            }
        });
        readInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

    }

     private void saveDataToFireStore() {
        String name = nameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();

        Map<String, Object> data = new HashMap<>();
        data.put(KEY_NAME, name);
        data.put(KEY_EMAIL, email);

        db.collection("Users")
                .document("Friends")
                .set(data)
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
 }