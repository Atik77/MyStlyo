package com.atik.mystlyo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.atik.mystlyo.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<Integer> Image;
    private Context context;

    private static LayoutInflater inflater=null;
    public CustomAdapter(Context context, ArrayList<Integer> mainImages) {

        this.context = context;
        this.Image = mainImages;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    // Get number of items in list
    public int getCount() {
        return Image.size()
                ;
    }

    @Override
    // Get position of item in list
    public Object getItem(int position) {
        return position;
    }

    @Override
    // ???
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.image_list, null);

        /** Get relevant view
         * Set image/text resource, based on list name, based on list position
         */
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.img.setImageResource(Image.get(position));

        return rowView;
    }

}