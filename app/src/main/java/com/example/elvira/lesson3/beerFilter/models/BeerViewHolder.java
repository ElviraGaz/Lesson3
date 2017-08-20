package com.example.elvira.lesson3.beerFilter.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elvira.lesson3.R;

/**
 * Created by bibi1 on 20.08.2017.
 */

public class BeerViewHolder extends RecyclerView.ViewHolder {
    public ImageView beerImage;
    public TextView beerName;

    public TextView beerTagLine;

    public TextView beerAbv;

    public TextView beerIbu;

    public TextView beerSrm;

    public TextView beerDescription;


    public BeerViewHolder(View itemView) {
        super(itemView);
        beerImage = (ImageView) itemView.findViewById(R.id.ivBeer);
        beerName = (TextView) itemView.findViewById(R.id.tvBeerName);
        beerTagLine = (TextView) itemView.findViewById(R.id.tvBeerTagLine);
        beerAbv = (TextView) itemView.findViewById(R.id.tvBeerAbv);
        beerIbu = (TextView) itemView.findViewById(R.id.tvBeerIbu);
        beerSrm = (TextView) itemView.findViewById(R.id.tvBeerSrm);
        beerDescription = (TextView) itemView.findViewById(R.id.tvBeerDescription);
    }
}
