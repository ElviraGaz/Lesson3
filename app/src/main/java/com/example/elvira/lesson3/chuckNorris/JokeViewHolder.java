package com.example.elvira.lesson3.chuckNorris;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.elvira.lesson3.R;


public class JokeViewHolder extends RecyclerView.ViewHolder{
    public TextView jokeText;

    public JokeViewHolder(View itemView){
        super(itemView);
        jokeText = (TextView) itemView.findViewById(R.id.item_joke);
    }

}
