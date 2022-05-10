package com.developersths.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.developersths.notes.databinding.ActivityNoteDetailsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Note_details extends AppCompatActivity {

    ActivityNoteDetailsBinding binding;
    TextView title_text,content_text;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        String id = i.getStringExtra(params.KEY_ID);
        String title = i.getStringExtra(params.KEY_TITLE);
        String content = i.getStringExtra(params.KEY_CONTENT);

        title_text = binding.titleText;
        content_text = binding.contentText;
        fab = binding.editBtn;

        title_text.setText(title);
        content_text.setText(content);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Note_details.this, Edit_note.class);
                intent.putExtra(params.KEY_ID,id);
                intent.putExtra(params.KEY_TITLE,title);
                intent.putExtra(params.KEY_CONTENT,content);
                startActivity(intent);
            }
        });

    }
}