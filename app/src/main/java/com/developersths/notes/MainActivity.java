package com.developersths.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.developersths.notes.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FloatingActionButton new_fab;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new_fab = binding.addFab;
        recyclerView = binding.recyclerView;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        new_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, add_notes.class));
            }
        });

        dbHandler db = new dbHandler(MainActivity.this);

        List<Notes> notesList = db.getNotes();

        ArrayList<Notes> notes_array_list = new ArrayList<>();

        for (Notes note:notesList){
            notes_array_list.add(note);
        }

        adapter = new RecyclerViewAdapter(MainActivity.this,notes_array_list);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }

    public void refresh(){
        dbHandler db = new dbHandler(MainActivity.this);

        List<Notes> notesList = db.getNotes();

        ArrayList<Notes> notes_array_list = new ArrayList<>();

        for (Notes note:notesList){
            notes_array_list.add(note);
        }

        adapter = new RecyclerViewAdapter(MainActivity.this,notes_array_list);
        recyclerView.setAdapter(adapter);
    }

}