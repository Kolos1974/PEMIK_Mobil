package com.example.l_examproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ToDos extends AppCompatActivity {

    private ListView todoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_to_dos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initWidgets();
        loadFromDBToMemory();
        setToDoAdapter();
        setOnClickListener();
    }

    private void initWidgets()
    {
        todoListView = findViewById(R.id.toDoListView);
    }

    private void loadFromDBToMemory()
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void setToDoAdapter()
    {
        ToDoAdapter toDoAdapter = new ToDoAdapter(getApplicationContext(), ToDo.nonDeletedToDos());
        todoListView.setAdapter(toDoAdapter);
    }

    private void setOnClickListener()
    {
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                ToDo selectedNote = (ToDo) todoListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), ToDoDetailActivity.class);
                editNoteIntent.putExtra(ToDo.TODO_EDIT_EXTRA, selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }

    public void newToDo(View view)
    {
        Intent newNoteIntent = new Intent(this, ToDoDetailActivity.class);
        startActivity(newNoteIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setToDoAdapter();
    }

}