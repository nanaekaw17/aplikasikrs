package com.example.aplikasikrs.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.aplikasikrs.R;

public class HomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        this.setTitle("SI KRS - Hai Admin");

        ImageButton btnDaftarDosen = (ImageButton)findViewById(R.id.btnDaftarDosen);
        btnDaftarDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, RecyclerViewDaftarDosen.class);
                startActivity(intent);
            }
        });

        ImageButton btnDaftarMhs = (ImageButton)findViewById(R.id.btnDaftarMhs);
        btnDaftarMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, RecyclerViewDaftarMhs.class);
                startActivity(intent);
            }
        });

        ImageButton btnDaftarMatkul = (ImageButton)findViewById(R.id.btnDaftarMatkul);
        btnDaftarMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, RecyclerViewDaftarMatkul.class);
                startActivity(intent);
            }
        });

        ImageButton btnDaftarKrs = (ImageButton)findViewById(R.id.btnKelolaKrs);
        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, RecyclerViewDaftarKrs.class);
                startActivity(intent);
            }
        });

        ImageButton btnDataDiri = (ImageButton)findViewById(R.id.iBtnDataDiriAdmin);
        btnDataDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, CreateDosenActivity.class);
                startActivity(intent);
            }
        });
    }
}
