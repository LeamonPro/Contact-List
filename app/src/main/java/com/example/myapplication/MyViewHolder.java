package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
     TextView name;
     TextView phoneNumber;
     ImageButton edit,delete;
     int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyViewHolder(View view){
        super(view);
        name=view.findViewById(R.id.name);
        phoneNumber=view.findViewById(R.id.number);
        edit=view.findViewById(R.id.edit);
        delete=view.findViewById(R.id.delete);


    }
}