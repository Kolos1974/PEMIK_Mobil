package com.example.l_feladat_5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> list;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editTextText;
    ImageView imageView;



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

        listView = findViewById(R.id.listView);
        String[] array = getResources().getStringArray(R.array.fruits);
        list = new ArrayList<>(Arrays.asList(array));
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list );
        listView.setAdapter(adapter);

        editTextText = findViewById(R.id.editTextText);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                editTextText.setText(list.get(position));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Biztos?")
                        .setMessage(R.string.dialog_message)
                        .setPositiveButton("IGEN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                list.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("NEM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();
                return false;
            }
        });

        imageView = findViewById(R.id.imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = new TranslateAnimation(0, 0, 200, 0);
                animation.setDuration(1000);
                imageView.startAnimation(animation);
            }
        }, 1000);
    }

    public void addFruit(View view) {
        String fruitStr = String.valueOf(editTextText.getText());
        if (!fruitStr.isEmpty()){
            list.add(fruitStr);
            adapter.notifyDataSetChanged();
        }
    }

    public void removeLastFruit(View view) {
        if (!list.isEmpty()) {
            list.remove(list.size()-1);
            adapter.notifyDataSetChanged();
        }
    }

    public void removeFruit(View view) {
        String fruitStr = String.valueOf(editTextText.getText());
        if (list.contains(fruitStr)) {
            list.remove(fruitStr);
            adapter.notifyDataSetChanged();
        }

    }
}