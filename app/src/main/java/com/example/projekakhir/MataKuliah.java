package com.example.projekakhir;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MataKuliah extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Matkul> matkuls;
    private static String JSON_URL = "https://api.npoint.io/e2b63eb2531c8817a818";
    Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mata_kuliah);

        recyclerView = findViewById(R.id.recycler_view1);
        matkuls = new ArrayList<>();
        extractMatkul();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new Adapter(getApplicationContext(), matkuls);
        recyclerView.setAdapter(adapter);

    }

    private void extractMatkul() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject matkulObject = response.getJSONObject(i);

                        Matkul matkul = new Matkul();
                        matkul.setNama("Mata Kuliah : " + matkulObject.getString("nama"));
                        matkul.setJadwal("Jadwal : " + matkulObject.getString("jadwal"));
                        matkul.setWaktu("Waktu : " + matkulObject.getString("waktu"));

                        matkuls.add(matkul);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), matkuls);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }
}
