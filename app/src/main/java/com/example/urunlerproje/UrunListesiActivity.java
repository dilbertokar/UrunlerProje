package com.example.urunlerproje;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.AppBarLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

import static com.example.urunlerproje.UrunlerAdapter.urunList;


public class UrunListesiActivity extends AppCompatActivity {

    TextView kullanıcı;

    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    GridLayoutManager gm;
    RecyclerView rv ;
    UrunlerAdapter adapter;
    ArrayList<Urun> urunler ;
    static ArrayList<String> keys;
    ArrayList<String> favs ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_listesi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ürün Listesi");
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.topmenu);

        rv = findViewById(R.id.rv);


        gm=new GridLayoutManager(this,2);
        gm.setOrientation(GridLayoutManager.VERTICAL);
        rv.setLayoutManager(gm);

        kullanıcı = findViewById(R.id.kullanıcı);

        kullanıcı.setText("Hoşgeldin "+MainActivity.kullanıcımaili);

        urunler = new ArrayList<Urun>();
        keys = new ArrayList<String>();
        favs = new ArrayList<String>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        //DatabaseReference myRef2 = database.getReference();

        myRef.child("urunler").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                urunler.add(dataSnapshot.getValue(Urun.class));
                keys.add(dataSnapshot.getKey());
                generateList(urunler);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Urun urun = dataSnapshot.getValue(Urun.class);
                int like = dataSnapshot.getValue(Urun.class).getLike();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void generateList(final ArrayList<Urun> urunList){

        adapter = new UrunlerAdapter(this,urunList);
        rv.setAdapter(adapter);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(UrunListesiActivity.this,MainActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu,menu);
        MenuItem menuItem1 =menu.findItem(R.id.add);
        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(UrunListesiActivity.this,UrunEkleActivity.class);
                startActivity(i);
                return true;
            }
        });

        MenuItem menuItem2 =menu.findItem(R.id.logout);
        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                MainActivity.firebaseAuth.signOut();
                MainActivity.firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if(firebaseAuth.getCurrentUser() == null){
                            startActivity(new Intent(UrunListesiActivity.this,MainActivity.class));
                        }
                    }
                });
                return true;
            }
        });
        return true;
    }




}
