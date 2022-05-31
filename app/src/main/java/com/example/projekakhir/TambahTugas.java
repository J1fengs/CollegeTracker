package com.example.projekakhir;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahTugas extends AppCompatActivity {

    private EditText etjudul, etdesc, etdeadline;
    private ImageButton btnSubmit;
    private FirebaseAuth mAuth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        btnSubmit = findViewById(R.id.btnSubmitData);
        etjudul = findViewById(R.id.judulTugas);
        etdesc = findViewById(R.id.descTugas);
        etdeadline = findViewById(R.id.deadlineTugas);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
// Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
//            tvEmail.setText(currentUser.getEmail());
//            tvUid.setText(currentUser.getUid());
        }
    }

    public void submitData(){
        if (!validateForm()){
            return;
        }
        String judul = etjudul.getText().toString();
        String desc = etdesc.getText().toString();
        String deadline = etdeadline.getText().toString();
        Tugas baru = new Tugas(judul, desc, deadline);
        databaseReference.child("Tugas").push().setValue(baru).addOnSuccessListener(this, new OnSuccessListener<Void>()
        { @Override
        public void onSuccess(Void unused) {
            Toast.makeText(TambahTugas.this, "Add data",
                    Toast.LENGTH_SHORT).show();
        }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TambahTugas.this, "Failed to Add data",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(etjudul.getText().toString())) {
            etjudul.setError("Required");
            result = false;
        } else {
            etjudul.setError(null);
        }
        if (TextUtils.isEmpty(etdesc.getText().toString())) {
            etdesc.setError("Required");
            result = false;
        } else {
            etdesc.setError(null);
        }
        if (TextUtils.isEmpty(etdeadline.getText().toString())) {
            etdeadline.setError("Required");
            result = false;
        } else {
            etdeadline.setError(null);
        }
        return result;
    }
}
