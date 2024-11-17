package com.example.l_feladat_2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    EditText editTextWebpage;
    TextView textViewLoginData, textViewLoginData2;

    public static String loginData = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "MenuActivity is in onCraete status");
        editTextWebpage = findViewById(R.id.editTextWebpage);

        textViewLoginData = findViewById(R.id.textViewLoginData);
        textViewLoginData2 = findViewById(R.id.textViewLoginData2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("adat");
            textViewLoginData.setText(data);
        }

        if (!loginData.equals("")){
            textViewLoginData2.setText(loginData);
        }

    }

    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MenuActivity is in onStart status");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MenuActivity is in onRestart status");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MenuActivity is in onResume status");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MenuActivity is in onPause status");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MenuActivity is in onStop status");
    }    @Override

    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MenuActivity is in onDestroy status");
    }


    public void openCalculator(View view) {
        Toast.makeText(this, "Ez az alkalmazás nem létezik!", Toast.LENGTH_SHORT).show();
    }

    public void openCamera(View view) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    public void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/");
        startActivity(intent);
    }

    public void openContacts(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivity(intent);
    }

    public void openWebpage(View view) {
        String url = editTextWebpage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // Azonosítás (Activity váltás)
    public void openIdentification(View view) {
        startActivity(new Intent(MenuActivity.this, AzonositasActivity.class));

    }
}