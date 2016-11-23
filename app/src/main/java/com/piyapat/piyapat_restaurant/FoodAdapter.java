package com.piyapat.piyapat_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by scOnTz on 23/11/2559.
 */
public class FoodAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] iconStrings, titleStrings, priceStrings;

    public FoodAdapter(Context context,
                       String[] iconStrings,
                       String[] titleStrings,
                       String[] priceStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.titleStrings = titleStrings;
        this.priceStrings = priceStrings;

    }// Constructor

    @Override
    public int getCount() {
        return iconStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listview_food, parent, false);

        TextView titleTextview = (TextView) view.findViewById(R.id.foodname_tv);
        titleTextview.setText(titleStrings[position]);

        TextView priceTextview = (TextView) view.findViewById(R.id.price_tv);
        priceTextview.setText(priceStrings[position] + " บาท");

        ImageView foodImage = (ImageView) view.findViewById(R.id.imageView2);
        Picasso.with(context).load(iconStrings[position]).into(foodImage);

        return view;
    }
}// Main Task
