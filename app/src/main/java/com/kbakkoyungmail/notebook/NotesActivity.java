package com.kbakkoyungmail.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {
      private static  final long REFRESH_DELAY =3000;
      private PullToRefreshView mPullToRefreshView;
      private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      listView=(ListView) findViewById(R.id.list);
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        refresh();
                    }
                }, REFRESH_DELAY);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(NotesActivity.this,NewNoteActivity.class));
            }
        });
    }
    private void refresh()
    {  FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = database.getReference("notes").child(user.getUid()).child("user notes");
       final ArrayList<Note> arrayList=new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                {
                    Note model=postSnapShot.getValue(Note.class);
                    arrayList.add(model);
                }
                NoteAdapter adapter=new NoteAdapter(getApplicationContext(),R.layout.listview_row,arrayList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
              ;
            }
        });

    }

}
