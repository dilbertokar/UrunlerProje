package com.example.urunlerproje;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UrunlerAdapter extends RecyclerView.Adapter<UrunlerAdapter.ViewHolder> {

    public static ArrayList<Urun> urunList;
    static Context context;
    LayoutInflater inf;

    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    static DatabaseReference databaseReference2 = firebaseDatabase.getReference("urunler");
    static DatabaseReference databaseReference3 = firebaseDatabase.getReference("favs");

    public UrunlerAdapter(Context context, ArrayList<Urun> urunList) {
        this.urunList = urunList;
        this.context=context;
        inf = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inf.inflate(R.layout.urunlayout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Urun urun = urunList.get(position);
        holder.urunAdı.setText(urun.getUrunAdı());
        holder.urunFiyati.setText(String.valueOf(urun.getFiyat()));
        holder.begenisayısı.setText(String.valueOf(urun.getLike()));
        String url = urun.getImgurl1();
        Picasso.get()
                .load(url)
                .into(holder.urunImage);

        if(urun.getLike() >0){
            holder.kalp.setImageResource(R.drawable.ic_like_24dp);
        }

    }

    @Override
    public int getItemCount() {


        return urunList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView urunAdı;
        public TextView urunFiyati;
        public ImageView urunImage;
        public CardView card_view;
        public ImageView kalp;
        public TextView begenisayısı;
        int like;
        int counter = 0;


        public ViewHolder(final View view) {
            super(view);

            card_view = (CardView)view.findViewById(R.id.cardview);
            urunAdı = (TextView)view.findViewById(R.id.urunadı);
            urunFiyati = (TextView)view.findViewById(R.id.urunfiyatı);
            urunImage = (ImageView)view.findViewById(R.id.mainimage);
            begenisayısı = view.findViewById(R.id.begeni);
            kalp = view.findViewById(R.id.kalp);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context , UrunDetayActivity.class);
                    i.putExtra("Urunadı",urunList.get(getAdapterPosition()).getUrunAdı());
                    i.putExtra("Urunfiyatı",urunList.get(getAdapterPosition()).getFiyat());
                    i.putExtra("Urunacıklama",urunList.get(getAdapterPosition()).getAcıklama());
                    i.putExtra("Url1",urunList.get(getAdapterPosition()).getImgurl1());
                    i.putExtra("Url2",urunList.get(getAdapterPosition()).getImgurl2());
                    i.putExtra("Url3",urunList.get(getAdapterPosition()).getImgurl3());
                    i.putExtra("Tarih",urunList.get(getAdapterPosition()).getTarih());

                    context.startActivity(i);
                }
            });


            kalp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kalp.setImageResource(R.drawable.ic_like_24dp);
                    like = urunList.get(getAdapterPosition()).getLike();
                    urunList.get(getAdapterPosition()).setLike(++like);
                    begenisayısı.setText(String.valueOf(urunList.get(getAdapterPosition()).getLike()));

                    String key = UrunListesiActivity.keys.get(getAdapterPosition());
                    databaseReference2.child(key).child("like").setValue(like);

                    //databaseReference3.child(firebaseAuth.getUid()).child(key);


                }
            });



        }




    }

}

