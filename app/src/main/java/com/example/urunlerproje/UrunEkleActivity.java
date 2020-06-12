package com.example.urunlerproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class UrunEkleActivity extends AppCompatActivity {

    static int counter = 0;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image1onay;
    ImageView image2onay;
    ImageView image3onay;
    EditText ad;
    EditText fiyat;
    EditText acıklama;
    Button ekle;
    String urunad;
    String urunacıklama;
    Date date;
    String formattedDate;

    int urunfiyat;
    int like;
    public  Uri image1uri ;
    public  Uri image2uri ;
    public  Uri image3uri ;

    String imageurl1;
    String imageurl3;
    String imageurl2;

    FirebaseDatabase firebaseDatabase ;
    FirebaseStorage firebaseStorage;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Firebase Ürünler Uygulaması");
        setSupportActionBar(toolbar);


        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        final DatabaseReference dbRef = firebaseDatabase.getReference().child("urunler");
        final StorageReference ref = firebaseStorage.getReference();

        progressBar = findViewById(R.id.progress);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image1onay = findViewById(R.id.image1onay);
        image2onay = findViewById(R.id.image2onay);
        image3onay = findViewById(R.id.image3onay);
        ad = findViewById(R.id.etxt_ad);
        fiyat = findViewById(R.id.etxt_fiyat);
        acıklama = findViewById(R.id.etxt_acıklama);
        ekle = findViewById(R.id.btn_urunekle);

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urunad = ad.getText().toString();
                urunfiyat = Integer.parseInt(fiyat.getText().toString());
                urunacıklama = acıklama.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

                date = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                formattedDate = df.format(date);
/*
                if(TextUtils.isEmpty(urunad)){
                    Toast.makeText(getApplicationContext(),"Lütfen ürün adı giriniz",Toast.LENGTH_SHORT).show();
                    if(urunfiyat == 0 || urunfiyat == -1){
                        Toast.makeText(getApplicationContext(),"Lütfen ürün fiyatı giriniz",Toast.LENGTH_SHORT).show();
                        if(TextUtils.isEmpty(urunacıklama)){
                            Toast.makeText(getApplicationContext(),"Lütfen ürün açıklaması giriniz",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        return;
                    }
                    return;
                }*/

                final StorageReference storageReference1 = ref.child("images/"+UUID.randomUUID());
                storageReference1.putFile(image1uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.e("1","Eklendi");
                        image1onay.setVisibility(View.VISIBLE);
                        storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageurl1 = uri.toString();

                                Toast.makeText(getApplicationContext(),"Resim 1 eklendi.",Toast.LENGTH_SHORT).show();

                            }
                        });


                        final StorageReference storageReference2 = ref.child("images/"+UUID.randomUUID());
                        storageReference2.putFile(image2uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Log.e("2","Eklendi");
                                image2onay.setVisibility(View.VISIBLE);
                                storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        imageurl2 = uri.toString();
                                        Toast.makeText(getApplicationContext(),"Resim 2 eklendi.",Toast.LENGTH_SHORT).show();
                                    }
                                });


                                final StorageReference storageReference3 = ref.child("images/"+UUID.randomUUID());
                                storageReference3.putFile(image3uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Log.e("3","Eklendi");
                                        image3onay.setVisibility(View.VISIBLE);
                                        storageReference3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                imageurl3 = uri.toString();
                                                like = 0;
                                                dbRef.push().setValue(new Urun(urunad,urunacıklama,urunfiyat,imageurl1,imageurl2,imageurl3,formattedDate,like)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        Intent i= new Intent(UrunEkleActivity.this,UrunListesiActivity.class);
                                                        startActivity(i);
                                                        Toast.makeText(getApplicationContext(),"Resim 3 eklendi.",Toast.LENGTH_SHORT).show();
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        finish();
                                                    }
                                                });
                                            }

                                        });
                                    }
                                });

                            }
                        });

                    }
                });

            }

        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.putExtra("id",v.getId());
                photoPickerIntent.setType("image/*");
                UrunEkleActivity.this.startActivityForResult(photoPickerIntent, 1);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                UrunEkleActivity.this.startActivityForResult(photoPickerIntent, 1);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                UrunEkleActivity.this.startActivityForResult(photoPickerIntent, 1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(UrunEkleActivity.this,UrunListesiActivity.class));

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                counter++;
                if(counter ==1){
                    image1.setImageBitmap(selectedImage);
                    image1uri = imageUri;

                }
                if(counter ==2){
                    image2.setImageBitmap(selectedImage);
                    image2uri = imageUri;

                }
                if(counter ==3){
                    image3.setImageBitmap(selectedImage);
                    image3uri = imageUri;
                    counter=0;

                }



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(UrunEkleActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(UrunEkleActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }


}

