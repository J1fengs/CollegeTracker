package com.example.projekakhir;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private EditText registEmail, registPassword;
    private ImageButton createAcc, loginNow;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        registEmail = findViewById(R.id.etRegisterEmail);
        registPassword = findViewById(R.id.etRegisterPassword);
        createAcc = findViewById(R.id.imageButtonCreateAccount);
        loginNow = findViewById(R.id.imageButtonLoginNow);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(registEmail.getText().toString(), registPassword.getText().toString());
            }
        });

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    public void signUp(String email,String password){
        if (!validateForm()){
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(RegisterActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG,"createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(RegisterActivity.this, MenuUtama.class);
            startActivity(intent);
        } else {
            Toast.makeText(RegisterActivity.this,"Log In First", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(registEmail.getText().toString())) {
            registEmail.setError("Required");
            result = false;
        } else {
            registEmail.setError(null);
        }
        if (TextUtils.isEmpty(registPassword.getText().toString())) {
            registPassword.setError("Required");
            result = false;
        } else {
            registPassword.setError(null);
        }
        return result;
    }

}
