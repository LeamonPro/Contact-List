package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> usersList;
    private ImageButton add;

    private RecyclerView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usersList=new ArrayList<>();
        view=findViewById(R.id.list);
        add=findViewById(R.id.addButton);
        setUserInfo();
        setAdapter();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Set the dialog title and message
                builder.setTitle("Enter Contact Details");
                builder.setMessage("Please enter the contact's name and phone number:");

                // Set up the two EditText fields
                final View customLayout=getLayoutInflater().inflate(R.layout.addlayout,null);
                builder.setView(customLayout);

                // Set up the dialog buttons
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText nameEntred=customLayout.findViewById(R.id.nameentred);
                        EditText phoneEntred=customLayout.findViewById(R.id.phoneentred);

                        if(nameEntred.getText().toString().equals("") || phoneEntred.getText().toString().equals("")){
                            AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                            alert.setMessage("Name or PhoneNumber is empty!");
                            alert.show();
                        }
                        else{

                        usersList.add(new User(nameEntred.getText().toString(),phoneEntred.getText().toString()));
                    }}
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                builder.show();
            }
        });



    }
    private void setUserInfo(){
        usersList.add(new User("Aymen","44648503"));
        usersList.add(new User("Jihen","44200611"));
        usersList.add(new User("Rihab","54871117"));
    }
    private void setAdapter(){
        recyclerAdapter adapter=new recyclerAdapter(this,usersList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setAdapter(adapter);
    }
}