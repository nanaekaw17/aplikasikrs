package com.example.aplikasikrs.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.aplikasikrs.Admin.Adapter.DosenAdapter;
import com.example.aplikasikrs.Admin.Model.Dosen;
import com.example.aplikasikrs.MainActivity;
import com.example.aplikasikrs.R;

import java.util.ArrayList;

public class RecyclerViewDaftarDosen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent intent = new Intent(RecyclerViewDaftarDosen.this,CreateDosenActivity.class);
            startActivity(intent);
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_daftar_dosen);
        this.setTitle("SI KRS - Hai Admin");
        tambahData();

        recyclerView = findViewById(R.id.rvDosen);
        dosenAdapter = new DosenAdapter(dosenList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDaftarDosen.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dosenAdapter);
    }

    private void tambahData(){
        dosenList = new ArrayList<>();
        dosenList.add(new Dosen("001","Argo Wibowo", "Proffesor","argo@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));
        dosenList.add(new Dosen("001","Argo Wibowo", "Proffesor","argo@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));
        dosenList.add(new Dosen("001","Argo Wibowo", "Proffesor","argo@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));
    }
}
