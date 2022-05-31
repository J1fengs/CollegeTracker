package com.example.projekakhir;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    String judul, desc, deadline, key;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String judul, String desc, String deadline, String key) {
        this.judul = judul;
        this.desc = desc;
        this.deadline = deadline;
        this.key = key;
    }

    TextView etJudul, etDesc, etDeadline;
    Button btnSimpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.input_form, container, false);

       etJudul = view.findViewById(R.id.etJudul);
        etDesc = view.findViewById(R.id.etDesc);
        etDeadline = view.findViewById(R.id.etDeadline);
        btnSimpan = view.findViewById(R.id.btnSimpan);

        etJudul.setText(judul);
        etDesc.setText(desc);
        etDeadline.setText(deadline);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judul = etJudul.getText().toString();
                String desc = etDesc.getText().toString();
                String deadline = etDeadline.getText().toString();

                database.child("Tugas").child(key).setValue(new Tugas(judul,desc,deadline)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(view.getContext(), "Update data", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view.getContext(), "Failed to Update data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
