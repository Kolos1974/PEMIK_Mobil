package com.example.l_feladat_6;

import static android.content.ContentValues.TAG;

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

public class MainActivity2 extends AppCompatActivity {

    EditText editTextName, editTextPassword;

    FirebaseDatabase database;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = FirebaseDatabase.getInstance("https://test-b0518-default-rtdb.europe-west1.firebasedatabase.app/");

        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
    }



    public void loginUser(View view) {
        String username = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();


        if (username.equals("") | password.equals("")) {
            Toast.makeText(this, "Nincs teljesen kitöltve", Toast.LENGTH_LONG).show();
        } else {
            userRef = database.getReference(username);
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is: " + value);

                        if (value.equals(password)) {
                            Toast.makeText(MainActivity2.this, "Sikeres bejelentkezés!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity2.this, "Sikertelen bejelentkezés!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity2.this, "Nincs ilyen felhasználó!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }

    }
}