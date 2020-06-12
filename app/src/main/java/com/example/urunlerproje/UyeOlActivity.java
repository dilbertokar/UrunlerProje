package com.example.urunlerproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UyeOlActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    EditText etxtmail;
    EditText etxtsifre;
    Button btnuyeol;
    String mail;
    String sifre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Firebase Ürünler Uygulaması");
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        etxtmail = findViewById(R.id.etxt_uyeolmail);
        etxtsifre = findViewById(R.id.etxt_uyeolsifre);
        btnuyeol = findViewById(R.id.btnuyeol2);

        btnuyeol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = etxtmail.getText().toString();
                sifre = etxtsifre.getText().toString();

                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Lütfen emailinizi giriniz.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sifre)){
                    Toast.makeText(getApplicationContext(),"Lütfen parolanızı giriniz.",Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.createUserWithEmailAndPassword(mail,sifre).addOnCompleteListener(UyeOlActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(UyeOlActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(UyeOlActivity.this, "Kayıt başarılı.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UyeOlActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(UyeOlActivity.this,MainActivity.class));

    }
}
