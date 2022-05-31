package com.example.projekakhir;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterTugas extends RecyclerView.Adapter<AdapterTugas.MyViewHolder> {

    private List<Tugas> tList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterTugas(List<Tugas> tList){
        this.tList = tList;
    }

    public AdapterTugas(List<Tugas> tList, Activity activity) {
        this.tList = tList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterTugas.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTugas.MyViewHolder holder, int position) {
        Tugas tugas = tList.get(position);
        holder.judul.setText("Judul : " + tugas.getJudul());
        holder.desc.setText("Deskripsi : " + tugas.getDesc());
        holder.deadline.setText("Deadline : " + tugas.getDeadline());

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DialogForm dialogForm = new DialogForm(tugas.getJudul(), tugas.getDesc(), tugas.getDeadline(), tugas.getKey());
                dialogForm.show(((AppCompatActivity) activity).getSupportFragmentManager(), "form");
                return true;
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.child("Tugas").child(tugas.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Data berhasil dihapus",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Gagal menghapus data",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setMessage("Apakah yakin ingin menghapus data ini ?");
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul, desc, deadline;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.firstLine);
            desc = itemView.findViewById(R.id.secondLine);
            deadline = itemView.findViewById(R.id.thirdline);

            cardView = itemView.findViewById(R.id.card);

        }
    }
}
