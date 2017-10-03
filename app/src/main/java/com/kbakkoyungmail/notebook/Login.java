package com.kbakkoyungmail.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
private static final String TAG="-----------------------";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        //mAuth.signOut();//kullanıcıyı uygulamdan atar
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(Login.this,NotesActivity.class));
                    finish();
                    //home page e devam et
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        ((Button) findViewById(R.id.buttonSign)).setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                sign(
                        ((EditText)findViewById(R.id.edit_user)).getText().toString().trim(),
                        ((EditText) findViewById(R.id.edit_pass)).getText().toString().trim()
                );
            }
        });


    }
    private void sign(String email,String pass)
    {
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //basarılı giriş
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),R.string.auth_success,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,NotesActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),R.string.auth_failed,Toast.LENGTH_SHORT).show();
                }

                }

        });
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

}
