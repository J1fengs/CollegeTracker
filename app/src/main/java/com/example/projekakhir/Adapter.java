package com.example.projekakhir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private LayoutInflater mInflater;
    List<Matkul> matkuls;

    public Adapter(Context context, List<Matkul> matkuls){
        this.mInflater = LayoutInflater.from(context);
        this.matkuls = matkuls;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama.setText(matkuls.get(position).getNama());
        holder.jadwal.setText(matkuls.get(position).getJadwal());
        holder.waktu.setText(matkuls.get(position).getWaktu());
    }

    @Override
    public int getItemCount() {
        return matkuls.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama, jadwal, waktu;

        public ViewHolder(View view) {
            super(view);
            nama = view.findViewById(R.id.firstLine);
            jadwal = view.findViewById(R.id.secondLine);
            waktu = view.findViewById(R.id.thirdline);
        }
    }
}
