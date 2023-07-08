package com.example.firebasesetup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    Button button;
    EditText editText;
    TextView gold;

    Button saveCredentials;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mDatabase = FirebaseDatabase.getInstance().getReference("gold").child("price");
//        button = findViewById(R.id.button);
//        editText = findViewById(R.id.editTextNumber);
//        gold = findViewById(R.id.gold);
        saveCredentials = findViewById(R.id.saveCredentials);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.setValue(editText.getText().toString());
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //When any change occurs in the database
                gold.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //writing data to firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("myUsers");
        saveCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseModel modelOne = new DatabaseModel(username.getText().toString(), password.getText().toString());
                mDatabase.setValue(modelOne);
            }
        });

    }
}