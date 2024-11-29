package com.example.l_examproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        // delayedShowToDos();
    }

    public void toDosActivity(View view) {
        showToDosActivity();
    }

    protected void showToDosActivity(){
        Intent intent = new Intent(MainActivity.this, ToDos.class);
        startActivity(intent);
    }

    protected void delayedShowToDos(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showToDosActivity();
            }
        }, 5000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // delayedShowToDos();
    }


}