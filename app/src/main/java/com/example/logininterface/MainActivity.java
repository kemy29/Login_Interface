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
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

   EditText password1,email1;
   Button signUp;
   TextView reg;
   FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email1 = findViewById(R.id.email1);
        password1 = findViewById(R.id.password1);
        signUp= findViewById(R.id.signUp);
        reg = findViewById(R.id.reg);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email1.getText().toString();
                String password = password1.getText().toString();

                if (email.isEmpty()){

                    email1.setError("Please enter email id");
                    email1.requestFocus();
                }
                    else if(password.isEmpty()) {

                        password1.setError("Please Enter Password id");
                        password1.requestFocus();
                }
                    else if(email.isEmpty() && password.isEmpty()){

                    Toast.makeText(MainActivity.this,"Fields are Empty",Toast.LENGTH_SHORT).show();

                }
                    else if(!(email.isEmpty()&&password.isEmpty())){

                        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                                if (!task.isSuccessful()){

                                    Toast.makeText(MainActivity.this,"SignUP Unsuccessful, Please try again",Toast.LENGTH_SHORT).show();

                                }else{

                                    startActivity (new Intent(MainActivity.this, HomeActivity.class));
                                }


                            }
                        });


                }
                    else {

                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();



                }
            }
        });



            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(MainActivity. this, login.class);
                    startActivity(i);


                }
            });


    }
}
