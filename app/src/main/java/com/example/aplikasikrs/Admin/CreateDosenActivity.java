package com.example.aplikasikrs.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasikrs.Admin.Model.DefaultResult;
import com.example.aplikasikrs.Admin.Model.Dosen;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;
import com.example.aplikasikrs.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDosenActivity extends AppCompatActivity {
    private TextView nama_dosen;
    private EditText alamat_dosen;
    private EditText email_dosen;
    private EditText gelar_dosen;
    private EditText nidn_dosen;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dosen);
        this.setTitle("SI KRS - Hai Admin");

//
        Button edit_dosen = (Button)findViewById(R.id.btnSimpanDosen);
        edit_dosen.setOnClickListener(dosen);
    }

    private View.OnClickListener dosen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(CreateDosenActivity.this);
            builder.setMessage("Apakahh anda yakin untuk menyimpan ??")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog, int which)
                    { Toast.makeText(CreateDosenActivity.this, "Tidak Menyimpan", Toast.LENGTH_SHORT).show(); } })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            insert_data();
                        }});
            AlertDialog dialog = builder.create(); dialog.show();
        }
    };
    private  void insert_data() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Masih Loading...");
        progressDialog.show();

        nama_dosen = (EditText) findViewById(R.id.editText);
        nidn_dosen = (EditText) findViewById(R.id.editText2);
        gelar_dosen = (EditText) findViewById(R.id.editText7);
        alamat_dosen = (EditText)findViewById(R.id.editText6);
        email_dosen = (EditText) findViewById(R.id.editText5);


        GetDataService service = RetrofitClientInstance.getRetrofitInstance()
                    .create(GetDataService.class);
            Call<DefaultResult> call = service.insert_dosen(
                    nama_dosen.getText().toString(),
                    alamat_dosen.getText().toString(),
                    nidn_dosen.getText().toString(),
                    gelar_dosen.getText().toString(),
                    email_dosen.getText().toString(),
                    "https://picsum.photos/200/300/?blur=2",
                    "72170090"
            );
        call.enqueue(new Callback<DefaultResult>() {
                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(CreateDosenActivity.this, HomeAdmin.class);
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                        progressDialog.dismiss();
                        Toast.makeText(CreateDosenActivity.this, "Berhasil Menyimpan", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CreateDosenActivity.this, RecyclerViewDaftarDosen.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<DefaultResult> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(CreateDosenActivity.this, "Login gagal,coba lagi", Toast.LENGTH_LONG);

                    }

                });
            }
        }
