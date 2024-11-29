package com.example.l_examproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class ToDoDetailActivity extends AppCompatActivity {

    private EditText titleEditText, descEditText;
    private Button deleteButton;
    private ToDo selectedToDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_to_do_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initWidgets();
        checkForEditToDo();
    }


    private void initWidgets()
    {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteNoteButton);
    }

    private void checkForEditToDo()
    {
        Intent previousIntent = getIntent();

        int passedNoteID = previousIntent.getIntExtra(ToDo.TODO_EDIT_EXTRA, -1);
        selectedToDo = ToDo.getToDoForID(passedNoteID);

        if (selectedToDo != null)
        {
            titleEditText.setText(selectedToDo.getTitle());
            descEditText.setText(selectedToDo.getDescription());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }
    public void saveToDo(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedToDo == null) {
            int id = ToDo.todoArrayList.size();
            ToDo newToDo = new ToDo(id, title, desc);
            ToDo.todoArrayList.add(newToDo);
            sqLiteManager.addNoteToDatabase(newToDo);
        }
        else
        {
            selectedToDo.setTitle(title);
            selectedToDo.setDescription(desc);
            sqLiteManager.updateNoteInDB(selectedToDo);
        }


        finish();
    }

    public void deleteToDo(View view) {
        selectedToDo.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedToDo);
        finish();
    }


}