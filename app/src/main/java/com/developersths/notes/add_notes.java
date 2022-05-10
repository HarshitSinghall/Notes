package com.developersths.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developersths.notes.databinding.ActivityAddNotesBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class add_notes extends AppCompatActivity {

    private EditText title_edit, content_edit;
    private FloatingActionButton save_btn;
    private ActivityAddNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title_edit = binding.titleText;
        content_edit = binding.contentText;
        save_btn = binding.saveBtn;

        dbHandler mydb = new dbHandler(add_notes.this);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String title = title_edit.getText().toString().trim();
                String content = content_edit.getText().toString().trim();
                if (!title.isEmpty() && !content.isEmpty()){
                    String date = String.valueOf(java.time.LocalDate.now());
                    String time = java.time.LocalTime.now().toString();
                    mydb.addNote(new Notes(title, content, date, time));
                    Toast.makeText(add_notes.this, "Note saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    vibrator.vibrate(200);
                    Toast.makeText(add_notes.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}