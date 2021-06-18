package com.atik.mystlyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Productfragment extends Fragment {

    public Productfragment() {
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
        View v = inflater.inflate(R.layout.productfragment, container, false);

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
