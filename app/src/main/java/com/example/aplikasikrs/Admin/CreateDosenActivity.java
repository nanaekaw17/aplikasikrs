package com.example.aplikasikrs.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplikasikrs.Admin.Model.DefaultResult;
import com.example.aplikasikrs.Admin.Model.Dosen;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;
import com.example.aplikasikrs.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CreateDosenActivity extends AppCompatActivity {
    private EditText nama_dosen;
    private EditText alamat_dosen;
    private EditText email_dosen;
    private EditText gelar_dosen;
    private EditText nidn_dosen;
    private EditText foto_dosen;
    ProgressDialog progressDialog;
    boolean isUpdate = false;
    private String idDosen = "";
    final int Request_Gallery = 9544;
    Bitmap bitmap;
    ImageView part_image;
    String Image;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Request_Gallery) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    Image = cursor.getString(indexImage);
                    if (Image != null) {
                        File gambar = new File(Image);
                        part_image.setImageBitmap(BitmapFactory.decodeFile(gambar.getAbsolutePath()));
                    }
                }

            }

        }

    }

    public static String ConvertingBitmapToString(Bitmap bitmap) {
        String EncodingImage = " ";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        EncodingImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        return EncodingImage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dosen);

        nama_dosen = (EditText) findViewById(R.id.editText);
        alamat_dosen = (EditText) findViewById(R.id.editText6);
        email_dosen = (EditText) findViewById(R.id.editText5);
        gelar_dosen = (EditText) findViewById(R.id.editText7);
        nidn_dosen = (EditText) findViewById(R.id.editText2);
        foto_dosen = (EditText) findViewById(R.id.editText8);

        this.setTitle("SI KRS - Hai Admin");

        part_image = (ImageView) findViewById(R.id.img_pilih);

        Button btn_carifoto = findViewById(R.id.buttonCariFoto);


        btn_carifoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Open_gallery"), Request_Gallery);
            }
        });

        checkUpdate();
        Button edit_dosen = findViewById(R.id.btnSimpanDosen);
        if (isUpdate) {
            edit_dosen.setText("update");
        }
        edit_dosen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!isUpdate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateDosenActivity.this);
                    builder.setMessage("Apakahh anda yakin untuk menyimpan ??")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(CreateDosenActivity.this, "Tidak Menyimpan", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    addData();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateDosenActivity.this);
                    builder.setMessage("Apakahh anda yakin untuk menyimpan ??")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(CreateDosenActivity.this, "Tidak Menyimpan", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    update_dosen();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        }
        );

    }

    private void addData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("masih loading sabar ...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DefaultResult> call = service.Insert_foto(
                nama_dosen.getText().toString(),
                alamat_dosen.getText().toString(),
                nidn_dosen.getText().toString(),
                gelar_dosen.getText().toString(),
                email_dosen.getText().toString(),
                "https://picsum.photos/200",
                "72170090");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this, "Behasil Menyimpan !!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CreateDosenActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.
                        dismiss();
                Toast.makeText(CreateDosenActivity.this, "Login gagal,Coba Lagi", Toast.LENGTH_LONG);
            }
        });

    }

    private void update_dosen() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("masih loading sabar ...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DefaultResult> call = service.update_foto_dosen(

                idDosen,
                nama_dosen.getText().toString(),
                alamat_dosen.getText().toString(),
                nidn_dosen.getText().toString(),
                gelar_dosen.getText().toString(),
                email_dosen.getText().toString(),
                foto_dosen.getText().toString(),
                "72170090");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateDosenActivity.this, "Behasil Update !!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CreateDosenActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.
                        dismiss();
                Toast.makeText(CreateDosenActivity.this, "Gagal update", Toast.LENGTH_LONG);
            }
        });
    }

    private void checkUpdate() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;

        }
        //get data via the key
        isUpdate = extras.getBoolean("is_update");
        idDosen = extras.getString("id_dosen");
        nidn_dosen.setText(extras.getString("nidn"));
        alamat_dosen.setText(extras.getString("alamat"));
        gelar_dosen.setText(extras.getString("gelar"));
        email_dosen.setText(extras.getString("email"));
        foto_dosen.setText(extras.getString("foto"));

    }
}