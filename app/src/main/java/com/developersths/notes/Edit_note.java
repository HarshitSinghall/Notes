package com.developersths.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developersths.notes.databinding.ActivityEditNoteBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Edit_note extends AppCompatActivity {

    ActivityEditNoteBinding binding;
    EditText title_edit, content_edit;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title_edit = binding.titleText;
        content_edit = binding.contentText;
        fab = binding.saveBtn;

        Intent i = getIntent();
        title_edit.setText(i.getStringExtra(params.KEY_TITLE));
        content_edit.setText(i.getStringExtra(params.KEY_CONTENT));
        String id = i.getStringExtra(params.KEY_ID);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_edit.getText().toString().trim();
                String content = content_edit.getText().toString().trim();
                if (!title.isEmpty() && !content.isEmpty()){

                    dbHandler db = new dbHandler(Edit_note.this);
                    db.update_note(id, title, content);

                    startActivity(new Intent(Edit_note.this, MainActivity.class));
                    Toast.makeText(Edit_note.this, "Note updated!", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    vibrator.vibrate(200);
                    Toast.makeText(Edit_note.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}