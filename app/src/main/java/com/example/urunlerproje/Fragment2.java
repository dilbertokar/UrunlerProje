package com.example.urunlerproje;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private int page;
    public static Fragment2 newInstance(int page) {
        Fragment2 fragmentFirst = new Fragment2();
        Bundle args = new Bundle();
        args.putInt("someInt", page);

        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image2fr, container, false);

        ImageView imageView = view.findViewById(R.id.fr_image2);
        UrunDetayActivity activity = (UrunDetayActivity) getActivity();
        String url = activity.getUrl2();
        Picasso.get()
                .load(url)
                .into(imageView);
        return view;
    }
}
