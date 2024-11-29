package com.example.l_examproject;

import java.util.ArrayList;
import java.util.Date;

public class ToDo {

    public static ArrayList<ToDo>todoArrayList = new ArrayList<>();
    public static String TODO_EDIT_EXTRA =  "todoEdit";

    private int id;
    private String title;
    private String description;
    private Date deleted;


    public ToDo(int id, String title, String description, Date deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public ToDo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    public static ToDo getToDoForID(int passedToDoID)
    {
        for (ToDo todo : todoArrayList)
        {
            if(todo.getId() == passedToDoID)
                return todo;
        }

        return null;
    }
    public static ArrayList<ToDo> nonDeletedToDos()
    {
        ArrayList<ToDo> nonDeleted = new ArrayList<>();
        for(ToDo todo : todoArrayList)
        {
            if(todo.getDeleted() == null) {
                nonDeleted.add(todo);
            }
        }

        return nonDeleted;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
