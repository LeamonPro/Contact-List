package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> usersList;
    private ImageButton add;

    private RecyclerView view;
    recyclerAdapter adapter;
    DatabaseHelper h;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h=new DatabaseHelper(this);
        usersList=h.fetchAll();

        view=findViewById(R.id.list);
        add=findViewById(R.id.addButton);

        view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter=new recyclerAdapter(this,usersList);
        view.setAdapter(adapter);
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
                            User user=new User(nameEntred.getText().toString(),phoneEntred.getText().toString());
                            usersList.add(user);
                            h.addUser(user);
                            adapter.notifyItemInserted(usersList.size()-1);
                        //usersList.add(new User(nameEntred.getText().toString(),phoneEntred.getText().toString()));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem mi = menu.findItem(R.id.app_bar_search);
        SearchView sv = (SearchView) mi.getActionView();
        sv.setQueryHint("Contact name");
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                usersList=h.getAll(s);
                adapter = new recyclerAdapter(MainActivity.this,usersList);
                view.setAdapter(adapter);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}