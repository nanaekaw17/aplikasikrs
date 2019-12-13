package com.example.aplikasikrs.Admin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasikrs.Network.DefaultResult;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;
import com.example.aplikasikrs.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMhsActivity extends AppCompatActivity {

    EditText edtNama, edtNim,edtAlamat,edtEmail;
    Button btnSave, btnBrowse;
    ImageView imgFoto;
    private Boolean isUpdate = false;
    String idMhs ="", ImgMhs;
    GetDataService service;
    ProgressDialog progressDialog;
    Bitmap bitmap;
    static final int IMG_REQ = 777;
    byte[] imagByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mhs);
        this.setTitle("SI KRS - Hai Admin");
        edtNama = (EditText)findViewById(R.id.edtNamaMhs);
        edtNim = (EditText)findViewById(R.id.edtNim);
        edtAlamat = (EditText)findViewById(R.id.edtAlamatMhs);
        edtEmail = (EditText)findViewById(R.id.edtEmailMhs);
        imgFoto = (ImageView)findViewById(R.id.imgFotoMhs);
        btnBrowse = (Button)findViewById(R.id.btnBrowseFotoMhs);

        checkUpdate();
//        Button btnDaftarKrs = (Button)findViewById(R.id.btnSimpanDosen);
//        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CreateDosenActivity.this, HomeAdmin.class);
//                startActivity(intent);
//            }
//        });

        btnSave = (Button)findViewById(R.id.btnSimpanDataMhs);
        if(isUpdate){
            btnSave.setText("Update");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValid = true;

                //Validation

                if(edtNama.getText().toString().matches("")){
                    edtNama.setError("Silahkan mengisi Nama Mhs");
                    isValid = false;
                }

                if(edtNim.getText().toString().matches("")){
                    edtNim.setError("Silahkan mengisi NIM Mhs");
                    isValid = false;
                }

                if(edtAlamat.getText().toString().matches("")){
                    edtAlamat.setError("Silahkan mengisi Alamat Mhs");
                    isValid = false;
                }

                if(edtEmail.getText().toString().matches("")){
                    edtEmail.setError("Silahkan mengisi Email Mhs");
                    isValid = false;
                }


                if(!isUpdate){
                    if(isValid){
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateMhsActivity.this);

                        builder.setMessage("Apakah anda yakin untuk menyimpan?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(CreateMhsActivity.this, "Batal Simpan", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestInsertDosen();
                                    }
                                });

                        AlertDialog dialog = builder.create(); dialog.show();
                    } else {
                        Toast.makeText(CreateMhsActivity.this,"Silahkan Isi Data",Toast.LENGTH_LONG).show();
                    }
                }else if(isUpdate){
                    if (isValid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateMhsActivity.this);

                        builder.setMessage("Apakah anda yakin untuk menyimpan?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(CreateMhsActivity.this, "Batal Simpan", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestUpdateDosen();

                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else {
                        Toast.makeText(CreateMhsActivity.this,"Silahkan Isi Data",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void requestInsertDosen(){
        String gambar = imageToString();
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog =  ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        Call<DefaultResult> call =  service.insert_mahasiswa_foto(edtNama.getText().toString(),edtNim.getText().toString(),
                edtAlamat.getText().toString(),edtEmail.getText().toString(),gambar,
                "72170090");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateMhsActivity.this,"Berhasil Insert",Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(CreateMhsActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(refresh);
                finish();

            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateMhsActivity.this,"Error",Toast.LENGTH_SHORT);
            }
        });
    }

    private void requestUpdateDosen(){
        String gambar = imageToString();
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog = ProgressDialog.show(CreateMhsActivity.this, null, "Harap Tunggu...", true, false);

        Call<DefaultResult> call = service.update_mahasiswa(idMhs, edtNama.getText().toString(), edtNim.getText().toString(),
                edtAlamat.getText().toString(), edtEmail.getText().toString(),  gambar,
                "72170090");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateMhsActivity.this, "Berhasil Update", Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(CreateMhsActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(refresh);
                finish();

            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateMhsActivity.this, "Error cuy", Toast.LENGTH_SHORT);
            }
        });
    }

    void checkUpdate(){
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }

        isUpdate = extras.getBoolean("is_update");
        idMhs = extras.getString("id_dosen");
        edtNama.setText(extras.getString("nama"));
        edtNim.setText(extras.getString("nidn"));
        edtAlamat.setText(extras.getString("alamat"));
        edtEmail.setText(extras.getString("email"));
        //edtFoto.setText(extras.getString("foto"));

        imagByte = Base64.decode(extras.getString("foto"), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imagByte, 0, imagByte.length);
        imgFoto.setImageBitmap(decodedImage);



    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQ && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgFoto.setImageBitmap(bitmap);
                imgFoto.setVisibility(View.VISIBLE);
                btnBrowse.setEnabled(true);
                btnSave.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imagByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imagByte, Base64.DEFAULT);
    }
}
