package com.example.l_feladat_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AzonositasActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_azonositas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);



    }

    public void sendLoginData(View view) {
        String data = editTextUsername.getText().toString()+" | "+editTextPassword.getText().toString();
        Intent intent = new Intent(AzonositasActivity.this, MenuActivity.class);
        intent.putExtra("adat", data);

        // Másik opció
        MenuActivity.loginData = data;


        startActivity(intent);
    }
}