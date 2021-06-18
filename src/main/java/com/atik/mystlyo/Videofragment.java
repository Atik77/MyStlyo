package com.atik.mystlyo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Videofragment extends Fragment {

    Activity siblingContext ;

    public Videofragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.videofragment, container, false);

        GridView gv = (GridView) v.findViewById(R.id.gridView1);

        ArrayList<Integer> ex1 = new ArrayList<Integer>();
        ex1.add(R.drawable.vid2);
        ex1.add(R.drawable.vid2);
        ex1.add(R.drawable.vid2);
        ex1.add(R.drawable.vid2);
        gv.setAdapter(new CustomAdapter(getContext(), ex1));

        return v;

    }

}
