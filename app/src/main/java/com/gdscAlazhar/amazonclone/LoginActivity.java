package com.gdscAlazhar.amazonclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

public class LoginActivity extends AppCompatActivity {
    InitFirebase initFirebase;
    DatabaseReference databaseReference;
    Button login;
    Button reg;
    EditText email;
    EditText password;
    String email_str;
    String password_str;
    String getPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setListener();
    }

    private void init() {
        login=findViewById(R.id.SignIn_btn);
        reg=findViewById(R.id.register_button);
        email=findViewById(R.id.editTextTextEmail);
        password=findViewById(R.id.editTextTextPassword);

    }

    public void setListener(){
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFirebase=new InitFirebase();
                email_str=email.getText().toString();
                password_str=password.getText().toString();
                if (email_str.isEmpty()||password_str.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter your Email and pass", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkEmailPass(email_str,password_str);
                }
            }
        });
    }
    public void checkEmailPass(String email_str, String password_str) {
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://alazhar-22f43-default-rtdb.firebaseio.com/");
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(email_str)) {
                    getPassword = snapshot.child(email_str).child("password").getValue(String.class);
                    if (getPassword.equals(password_str)) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, NavigationMenu.class));
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong email", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
