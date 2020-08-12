package com.abc.atomstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailtv;
    EditText passwordtv;
    Button go;
    //Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailtv=findViewById(R.id.emailtv);
        passwordtv=findViewById(R.id.passwordtv);
        go=findViewById(R.id.go);
        //SignUp=findViewById(R.id.SignUp);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(mAuth.getCurrentUser()!=null)
        {
            logIn();
        }
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(emailtv.toString(), passwordtv.toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    logIn();

                                } else {
                                    mAuth.createUserWithEmailAndPassword(emailtv.toString(), passwordtv.toString())
                                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        // Sign in success, update UI with the signed-in user's information
                                                        //database
                                                        logIn();
                                                        FirebaseUser user = mAuth.getCurrentUser();

                                                    } else {
                                                        // If sign in fails, display a message to the user.

                                                        Toast.makeText(this,"Login Failed! Try Again.",Toast.LENGTH_SHORT).show();

                                                    }

                                                    // ...
                                                }
                                            });
                                }


                            }
                        });
            }
        });

    }
    public void logIn()
    {
        Intent intent= new Intent(MainActivity.this,FirstPage.class);
        startActivity(intent);
    }

}
