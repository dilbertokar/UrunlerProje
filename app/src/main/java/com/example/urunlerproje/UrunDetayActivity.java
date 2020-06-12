package com.example.urunlerproje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

public class UrunDetayActivity extends AppCompatActivity {

    String urunadı;
    String urunfiyatı;
    String urunacıklama;
    String url1;
    String url2;
    String url3;
    String  uruntarih;

    TextView ad;
    TextView fiyat;
    TextView acıklama;
    TextView tarih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Firebase Ürünler Uygulaması");
        setSupportActionBar(toolbar);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vievpage);
        ViewPagerAdapterr adapterViewPager = new ViewPagerAdapterr(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        ad = findViewById(R.id.adtext);
        fiyat = findViewById(R.id.fiyattext);
        acıklama = findViewById(R.id.acıklamatext);
        tarih = findViewById(R.id.tarihtext);

        urunadı = getIntent().getExtras().get("Urunadı").toString();
        urunfiyatı = getIntent().getExtras().get("Urunfiyatı").toString();
        urunacıklama = getIntent().getExtras().get("Urunacıklama").toString();
        url1 = getIntent().getExtras().get("Url1").toString();
        url2 = getIntent().getExtras().get("Url2").toString();
        url3 = getIntent().getExtras().get("Url3").toString();
        uruntarih = getIntent().getExtras().get("Tarih").toString();

        ad.setText(urunadı);
        fiyat.setText(urunfiyatı);
        acıklama.setText(urunacıklama);
        tarih.setText(uruntarih);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(UrunDetayActivity.this,UrunListesiActivity.class));

    }

    public String getUrl1() {
        return url1;
    }

    public String getUrl2() {
        return url2;
    }

    public String getUrl3() {
        return url3;
    }

}
