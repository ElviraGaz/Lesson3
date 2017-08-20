package com.example.elvira.lesson3.beerFilter.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elvira.lesson3.R;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by bibi1 on 20.08.2017.
 */

public class BeerAdapter extends RecyclerView.Adapter<BeerViewHolder> {
    private List<Beer> beer;

    public BeerAdapter(List<Beer> beer) {
        this.beer = beer;
    }


    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rowView = layoutInflater.inflate(R.layout.beer_item, parent, false);
        return new BeerViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        Context context = holder.beerImage.getContext();
        Picasso.with(context)
                .load(beer.get(position).getImageUrl())
                .into(holder.beerImage);
        holder.beerName.setText(beer.get(position).getName());
        holder.beerTagLine.setText(beer.get(position).getTagLine());
        holder.beerAbv.setText(String.valueOf(beer.get(position).getAbv()));
        holder.beerIbu.setText(String.valueOf(beer.get(position).getIbu()));
        holder.beerSrm.setText(String.valueOf(beer.get(position).getSrm()));
        holder.beerDescription.setText(beer.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return beer.size();
    }
}
