package com.gdsc_alazhar.amazonclone;

import android.app.Activity;
import android.widget.Toast;

import com.google.firebase.database.*;

public class InitFirebase {
    DatabaseReference databaseReference;
    String getPassword;
    //send data to firebase
    public void register(String name_str, String email_str, String phone_str, String password_str, Activity activity) {
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cloneee-3aa8b-default-rtdb.firebaseio.com/");
        databaseReference.child("users").child(email_str).child("name").setValue(name_str);
        databaseReference.child("users").child(email_str).child("phone").setValue(phone_str);
        databaseReference.child("users").child(email_str).child("password").setValue(password_str);
        Toast.makeText(activity, "Registered Successfully", Toast.LENGTH_LONG).show();
    }
}


