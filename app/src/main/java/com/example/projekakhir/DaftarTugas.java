package com.example.projekakhir;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DaftarTugas extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Tugas tugas;
    private FirebaseAuth mAuth;


    private RecyclerView recyclerView;
    AdapterTugas adapterTugas;
    ArrayList<Tugas> listTugas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_tugas);

        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recycler_view2);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        showData();
    }
    private void showData(){
        databaseReference.child("Tugas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTugas = new ArrayList<>();
                for(DataSnapshot item : snapshot.getChildren()){
                    Tugas tugas = item.getValue(Tugas.class);
                    tugas.setKey(item.getKey());
                    listTugas.add(tugas);
                }
                adapterTugas = new AdapterTugas(listTugas, DaftarTugas.this);
                recyclerView.setAdapter(adapterTugas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
