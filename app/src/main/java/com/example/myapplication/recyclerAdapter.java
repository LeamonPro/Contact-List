package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<User> usersList;
    Context context;

    public recyclerAdapter(Context context,ArrayList<User> usersList) {
        this.usersList = usersList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position) {
        int i1=position;
        holder.name.setText(usersList.get(position).getUsername());
        holder.phoneNumber.setText(usersList.get(position).getPhonenumber());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                // Inflate the custom layout

                View customView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.addlayout, (ViewGroup) holder.itemView, false);
                EditText editText1 = customView.findViewById(R.id.nameentred);
                EditText editText2 = customView.findViewById(R.id.phoneentred);

                alert.setTitle("Update Your Informations");
                alert.setView(customView);
                alert.setPositiveButton("submit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(!editText1.getText().toString().equals(""))
                            holder.name.setText(editText1.getText().toString());
                        if(!editText2.getText().toString().equals(""))
                            holder.phoneNumber.setText(editText2.getText().toString());

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert2=new AlertDialog.Builder(context);
                alert2.setTitle("Delete "+usersList.get(i1).getUsername());
                alert2.setMessage("Are you sure");
                alert2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        usersList.remove(usersList.get(i1));
                        notifyDataSetChanged();
                    }
                });
                alert2.show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
