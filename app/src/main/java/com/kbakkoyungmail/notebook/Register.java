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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ((Button) findViewById(R.id.btnSign)).setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                EditText textmail=(EditText)findViewById(R.id.txtmail);
                EditText textsifre=(EditText)findViewById(R.id.txtsifre);
                EditText textsifre2=(EditText)findViewById(R.id.txtsifre2);
                if(validate(textmail.getText().toString().trim(),
                            textsifre.getText().toString().trim(),
                            textsifre2.getText().toString().trim()))
                {

                    register(textmail.getText().toString().trim(),
                            textsifre.getText().toString().trim());
                }
                else
                {
                    //Toast.makeText(getApplicationContext(),"Check your information!",Toast.LENGTH_SHORT).show();
                    //snackbar ile da yapabilir bu hata vermeyi

                    Snackbar.make(v,"Check your info",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }

            }
        });



    }
    private boolean validate(String mail,String sifre,String sifre2)
    {
        boolean status=true;
        if(!mail.contains("@"))
            return false;
        if (!mail.contains(".com"))
            return false;
        if(!sifre.equals(sifre2))
            return  false;
        if(sifre.length()<= 6)
            return false;
        return status;
    }
    private void register(String email,String password)
    { mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();



                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),R.string.auth_success,
                                    Toast.LENGTH_SHORT).show();
                            //logine geri dön
                            startActivity(new Intent(Register.this,Login.class));
                            finish();
                        }

                        // ...
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
       // mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
