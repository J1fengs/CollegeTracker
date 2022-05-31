package com.example.projekakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuUtama extends AppCompatActivity {

    private Button btnLogOut, btnMatkul, btnTambah, btnTugas;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        btnLogOut = findViewById(R.id.btn_logout);
        btnMatkul = findViewById(R.id.btn_matkul);
        btnTambah = findViewById(R.id.btn_tambah);
        btnTugas = findViewById(R.id.btn_tugas);


        mAuth = FirebaseAuth.getInstance();

        btnTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuUtama.this, DaftarTugas.class));
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuUtama.this, TambahTugas.class));
            }
        });

        btnMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuUtama.this, MataKuliah.class));
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    public void logOut(){
        mAuth.signOut();
        Intent intent = new Intent(MenuUtama.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //makesure user cant go back
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
// Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            tvEmail.setText(currentUser.getEmail());
//            tvUid.setText(currentUser.getUid());
//        }
    }
}
