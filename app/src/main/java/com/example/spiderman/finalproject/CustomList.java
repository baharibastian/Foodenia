package com.example.spiderman.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spiderman.finalproject.R;

/**
 * Created by Spiderman on 2016-12-02.
 */

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] namaPercetakan;
    private final String[] alamatPercetakan;
    private final Integer[] fotoTempat;

    public CustomList(Activity context, String[] namaPercetakan, String[] alamatPercetakan, Integer[] fotoTempat) {
        super(context, R.layout.content_main,namaPercetakan);
        this.context = context;
        this.namaPercetakan = namaPercetakan;
        this.alamatPercetakan = alamatPercetakan;
        this.fotoTempat = fotoTempat;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_list,null,true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.fotoTempat);
        TextView imageText = (TextView) rowView.findViewById(R.id.namaPercetakan);
        TextView extraText = (TextView) rowView.findViewById(R.id.alamatPercetakan);

        imageText.setText(namaPercetakan[+position]);
        imageView.setImageResource(fotoTempat[+position]);
        extraText.setText(alamatPercetakan[+position]);

        return rowView;
    }
}

