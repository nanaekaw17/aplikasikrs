package com.example.aplikasikrs.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplikasikrs.R;

public class CreateMhsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mhs);
        this.setTitle("SI KRS - Hai Admin");

        Button btnSimpanKrs = (Button)findViewById(R.id.btnCreateMhs);
        btnSimpanKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateMhsActivity.this, HomeAdmin.class);
                startActivity(intent);
            }
        });
    }
}
