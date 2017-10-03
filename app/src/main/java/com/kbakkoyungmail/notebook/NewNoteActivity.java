package com.kbakkoyungmail.notebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            Toast.makeText(getApplicationContext(),R.string.auth_failed,Toast.LENGTH_SHORT);
            finish();
        }

        ((Button) findViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("notes");
                Note note=new Note();//buraya da yazabilrsm
                note.setTitle(((EditText) findViewById(R.id.text_title)).getText().toString());
                note.setNote(((EditText) findViewById(R.id.text_note)).getText().toString());
                String date= java.text.DateFormat.getDateTimeInstance().format(new Date());
                note.setDate(date);
                note.setUser(user.getEmail());
                String key=myRef.push().getKey();
               // myRef.child(key).setValue(note);
                myRef.child(user.getUid()).child("user notes").child(key).setValue(note);

                //myRef.setValue(note);
                Toast.makeText(getApplicationContext(),R.string.note_success,Toast.LENGTH_SHORT).show();
                ((EditText) findViewById(R.id.text_title)).setText("");
                ((EditText) findViewById(R.id.text_note)).setText("");

            }
        });

    }
}
