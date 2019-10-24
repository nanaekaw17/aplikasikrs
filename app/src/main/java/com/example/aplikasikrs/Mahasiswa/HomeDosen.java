package com.example.aplikasikrs.Mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.aplikasikrs.Admin.RecyclerViewDaftarKrs;
import com.example.aplikasikrs.R;

public class HomeDosen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dosen);
        this.setTitle("SI KRS - Hai Mahasiswa");

        ImageButton btnDaftarKelas = (ImageButton)findViewById(R.id.btnLihatKelas);
        btnDaftarKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDosen.this, RecyclerViewKelas.class);
                startActivity(intent);
            }
        });

        ImageButton btnDaftarKrs = (ImageButton)findViewById(R.id.btnDaftarKrs);
        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDosen.this, RecyclerViewDaftarKrs.class);
                startActivity(intent);
            }
        });

        ImageButton btnDataDiri = (ImageButton)findViewById(R.id.btnDataDiriDosen);
        btnDataDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDosen.this, DataDiriMhsActivity.class);
                startActivity(intent);
            }
        });
    }
}
