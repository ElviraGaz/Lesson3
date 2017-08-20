package com.example.elvira.lesson3.chuckNorris;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.elvira.lesson3.R;
import com.example.elvira.lesson3.chuckNorris.models.Joke;
import com.example.elvira.lesson3.chuckNorris.models.JokeText;

import java.util.List;

/**
 * Created by bibi1 on 18.08.2017.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeViewHolder> {
    private List<JokeText> joke;

    public JokeAdapter(List<JokeText> joke) {
        this.joke = joke;
    }


    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rowView = layoutInflater.inflate(R.layout.joke, parent, false);
        return new JokeViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(JokeViewHolder holder, int position) {
        holder.jokeText.setText(joke.get(position).getJoke());
    }

    @Override
    public int getItemCount() {
        return joke.size();
    }
}
