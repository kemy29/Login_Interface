package com.example.logininterface;

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

public class login extends AppCompatActivity {

    EditText password2,email2;
    Button button_login;
    TextView reg2;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email2 = findViewById(R.id.email1);
        password2 = findViewById(R.id.password1);
        button_login= findViewById(R.id.signUp);
        reg2 = findViewById(R.id.reg);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

          FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //check user credentials exist
                if(mFirebaseUser != null){

                    Toast.makeText(login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(login.this, "Please Login", Toast.LENGTH_SHORT).show();




                }

            }
        };

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = email2.getText().toString();
                String password = password2.getText().toString();

                if (email.isEmpty()){

                    email2.setError("Please enter email id");
                    email2.requestFocus();
                }
                else if(password.isEmpty()) {

                    password2.setError("Please Enter Password id");
                    password2.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()){

                    Toast.makeText(login.this,"Fields are Empty",Toast.LENGTH_SHORT).show();

                }
                else if(!(email.isEmpty()&&password.isEmpty())){

                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if(!task.isSuccessful()){

                                Toast.makeText(login.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();



                            }else {

                                Intent inToHome = new Intent(login.this, HomeActivity.class);
                                startActivity(inToHome);

                            }



                        }
                    });

                }
                else {

                    Toast.makeText(login.this,"Error Occurred!",Toast.LENGTH_SHORT).show();



                }




            }
        });

        reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(login.this, MainActivity.class);
                startActivity(signUp);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
