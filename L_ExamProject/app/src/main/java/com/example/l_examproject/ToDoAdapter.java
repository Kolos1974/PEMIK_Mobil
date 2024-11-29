package com.example.l_examproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ToDoAdapter extends ArrayAdapter<ToDo>{

    public ToDoAdapter(Context context, List<ToDo> notes)
    {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ToDo todo = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_cell, parent, false);

        TextView title = convertView.findViewById(R.id.cellTitle);
        TextView desc = convertView.findViewById(R.id.cellDesc);

        title.setText(todo.getTitle());
        desc.setText(todo.getDescription());

        return convertView;
    }


}
