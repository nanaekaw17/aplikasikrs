package com.example.aplikasikrs.Mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.aplikasikrs.R;

public class DataDiriMhsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_diri_mhs);
        this.setTitle("SI KRS - Hai Mahasiswa");

        Button btnDaftarKrs = (Button)findViewById(R.id.btnSimpanDataMhs);
        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataDiriMhsActivity.this, HomeDosen.class);
                startActivity(intent);
            }
        });
    }
}
