package com.example.l_feladat_7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MainActivity2 extends AppCompatActivity {

    int startCount = 0;
    EditText editTextName, editTextAge;
    TextView textViewStartCount, textViewStartDates;
    SharedPreferences sharedPreferences;
    String currentDate;




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

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        textViewStartCount = findViewById(R.id.textViewStartCount);
        textViewStartDates = findViewById(R.id.textViewStartDates);

        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "---");
        String age = sharedPreferences.getString("age", "---");
        editTextName.setText(name);
        editTextAge.setText(age);

        // Első futás
        if (sharedPreferences.getBoolean("first_run", true)) {
            Toast.makeText(this, "Első indítás!\nKérlek add meg a neved és a korod!", Toast.LENGTH_LONG).show();
        }
        // Nem első futás
        else {
            Toast.makeText(this, "Szia " + name + " !", Toast.LENGTH_LONG).show();
            editTextName.setEnabled(false);
            editTextAge.setEnabled(false);
        }

        // File
        File filesDir = getExternalFilesDir(null);
        String fileName = filesDir.getAbsolutePath() + "/startCount.txt";

        // File olvasás
        try {
            File file = new File(fileName);
            if (file.exists()){
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextInt()) {
                    startCount = scanner.nextInt();
                }
                scanner.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        startCount++;
        textViewStartCount.setText(Integer.toString(startCount));

        // File írás
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
            outputStream.write(Integer.toString(startCount).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        currentDate = sdf.format(new Date());

        // File írás (internal storage)
        FileOutputStream fout = null;
        try {
            fout = openFileOutput("adat.txt", MODE_APPEND);
            fout.write((currentDate+"\n").getBytes());
        } catch (Exception e){
            Log.e("hiba", "Az adat.txt file-ba nem sikerült az írás!", e);
        } finally {
            try {
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // File olvasás (internal storage)
        FileInputStream fin = null;
        try {
            fin = openFileInput("adat.txt");
            int content;
            StringBuilder stringBuilder = new StringBuilder();
            while ((content = fin.read()) != -1){
                stringBuilder.append((char) content );
            }
            textViewStartDates.setText(stringBuilder.toString());

        } catch (IOException e) {
            Log.e("hiba", "Az adat.txt file-ból nem sikerült az olvasás!", e);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    protected void onStop(){
        super.onStop();

        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("first_run", true)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("first_run", false);
            editor.putString("name", editTextName.getText().toString());
            editor.putString("age", editTextAge.getText().toString());
            editor.apply();
        }


    }

}