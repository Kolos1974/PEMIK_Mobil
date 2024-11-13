package com.example.l_feladat_1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textViewTitle, textViewCopy;
    EditText editTextText;

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

        // 2., Toast üzenet
        Toast.makeText(MainActivity.this, "Alkalmazáz használatra kész!", Toast.LENGTH_LONG).show();

        // 3., "Hello World!" felirat helyett "1. feladat"
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText("1. feladat");

        textViewCopy = findViewById(R.id.textViewCopy);
        editTextText = findViewById(R.id.editTextText);

        // 5., Szöveg törlése
        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewCopy.setText("");
                editTextText.setText(null);
            }
        });


    }

    // 4., Szöveg másolása
    public void copyText(View view) {
        if (editTextText.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Írjon be valamilyen szöveget!", Toast.LENGTH_SHORT).show();
        }
        else {
            textViewCopy.setText(editTextText.getText());
        }
    }


}