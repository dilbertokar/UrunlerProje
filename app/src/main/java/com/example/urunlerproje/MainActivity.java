package com.example.urunlerproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    static FirebaseAuth firebaseAuth;
    EditText etxtmail;
    EditText etxtsifre;
    Button btngiris;
    Button btnuyeol;
    String mail;
    String sifre;

    public static String kullanıcımaili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Firebase Ürünler Uygulaması");
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        etxtmail = findViewById(R.id.etxt_girismail);
        etxtsifre = findViewById(R.id.etxt_girissifre);
        btngiris = findViewById(R.id.btngiris);
        btnuyeol =findViewById(R.id.btnuyeol);

        btngiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = etxtmail.getText().toString();
                sifre = etxtsifre.getText().toString();

                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Lütfen emailinizi giriniz",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sifre)){
                    Toast.makeText(getApplicationContext(),"Lütfen parolanızı giriniz",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(mail,sifre).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            kullanıcımaili = mail;
                            Intent i =new Intent(MainActivity.this,UrunListesiActivity.class);
                            i.putExtra("userMail",mail);


                            startActivity(i);
                        }
                        else {
                            Log.e("Giriş Hatası",task.getException().getMessage());
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        btnuyeol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UyeOlActivity.class);
                startActivity(i);
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();

        this.finishAffinity();

    }




}