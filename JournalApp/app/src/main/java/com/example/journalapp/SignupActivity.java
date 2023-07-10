package com.example.journalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    EditText emailCreate, passwordCreate, usernameCreate;
    Button buttonSignup;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser firebaseUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignup = findViewById(R.id.signUpButton);
        usernameCreate = findViewById(R.id.usernameCreate);
        emailCreate = findViewById(R.id.emailCreate);
        passwordCreate = findViewById(R.id.passwordCreate);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    //user logged in
                }
                else {
                    //no user in
                }
            }
        };

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(emailCreate.getText().toString())
                && !TextUtils.isEmpty(passwordCreate.getText().toString())) {
                    String email = emailCreate.getText().toString().trim();
                    String password = passwordCreate.getText().toString().trim();
                    String username = usernameCreate.getText().toString().trim();

                    createUserAccount(email, password, username);
                }
                else {
                    Toast.makeText(SignupActivity.this, "Empty Fields Detected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createUserAccount(String email, String password, final String username) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is taken to addJournal Activity
                            firebaseUser = firebaseAuth.getCurrentUser();
                            assert firebaseUser != null;
                            final String currentUserID = firebaseUser.getUid();

                            //Create a userMap so we can create a user in the User Collection in Firebase
                            Map<String, String> userObj = new HashMap<>();
                            userObj.put("userID", currentUserID);
                            userObj.put("username", username);

                            //adding users to Firestore
                            collectionReference.add(userObj)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            //check if the user is successfully registered
                                            // if yes, move the user to AddJournal Activity
                                            if (Objects.requireNonNull(task.getResult()).exists()) {
                                                String name = task.getResult().getString("username");
                                                Intent intent = new Intent(SignupActivity.this, AddJournalActivity.class);

                                                intent.putExtra("username", name);
                                                intent.putExtra("userID", currentUserID);
                                                startActivity(intent);
                                            }
                                            else {

                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignupActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
