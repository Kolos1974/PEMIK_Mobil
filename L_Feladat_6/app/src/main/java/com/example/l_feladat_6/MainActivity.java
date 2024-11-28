package com.example.l_feladat_6;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextPassword;

    FirebaseDatabase database;
    DatabaseReference myRef, userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Write a message to the database
        database = FirebaseDatabase.getInstance("https://test-b0518-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference("message");

        myRef.setValue("Hello, World!");



        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);

    }

    public void registerUser(View view) {
        String username = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();
        
        if (username.equals("") | password.equals("")){
            Toast.makeText(this, "Nincs teljesen kitöltve", Toast.LENGTH_LONG).show();
        } else {
            userRef = database.getReference(username);
            userRef.setValue(password);
            Toast.makeText(this, "Sikeres regisztráció", Toast.LENGTH_LONG).show();

            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        }

    }
}