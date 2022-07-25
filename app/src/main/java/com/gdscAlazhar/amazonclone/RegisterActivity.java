package com.gdscAlazhar.amazonclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText phone;
    EditText password;
    Button register;
    ImageButton back;
    InitFirebase initFirebase;
    String name_str,  email_str,  phone_str,  password_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        setListener();
    }
    private void init(){
        name =findViewById(R.id.user_name);
        email=findViewById(R.id.email_address);
        password=findViewById(R.id.password_r);
        phone=findViewById(R.id.phone_et);
        register=findViewById(R.id.register);
        back=findViewById(R.id.back_home);
    }
    public void setListener(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFirebase=new InitFirebase();
                getDataFromEditText();
            }
        });
    }
    public void getDataFromEditText() {
        name_str =name.getText().toString();
        email_str =email.getText().toString();
        phone_str =phone.getText().toString();
        password_str =password.getText().toString();
        checkString(name_str,email_str,phone_str,password_str);
    }
    public void checkString(String name_str, String email_str, String phone_str, String password_str){
        if(name_str.isEmpty()||email_str.isEmpty()||phone_str.isEmpty()|| password_str.isEmpty()){
            Toast.makeText(RegisterActivity.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }
        else{
             initFirebase.register(name_str,email_str,phone_str,password_str,RegisterActivity.this);
        }
    }

}