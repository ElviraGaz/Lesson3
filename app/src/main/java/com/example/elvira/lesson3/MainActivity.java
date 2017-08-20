package com.example.elvira.lesson3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elvira.lesson3.chuckNorris.JokeAdapter;
import com.example.elvira.lesson3.beerFilter.activities.BeersActivity;
import com.example.elvira.lesson3.chuckNorris.api.*;
import com.example.elvira.lesson3.chuckNorris.models.Joke;
import com.example.elvira.lesson3.chuckNorris.models.JokeText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bibi1 on 19.08.2017.
 */



public class MainActivity extends AppCompatActivity {
    ChuckNorrisService norrisService;
    TextView beerName;
    ProgressDialog loadingDialog;
    final Integer count =20 ;
    String firstName = "Anton";
    String lastName = "Petrov";
    String escape = "javascript";
    List<JokeText> jokeResultList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        loadingDialog = ProgressDialog.show(this, "", getString(R.string.network_loading), true);

        norrisService = ApiFactory.getRetrofitInstance().create(ChuckNorrisService.class);
        fetchRandomJoke();



        // Создаём и задаём класс, отвечающий за "декорирование" элементов (добавляем разделители)
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        // Создаём и задаём класс, отвечающий за анимации
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fetchRandomJoke();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }


        });

    }




    private void fetchRandomJoke() {

        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<Joke> call = norrisService.getJokes(count, firstName, lastName, escape);
        // Отображаем progress bar

        loadingDialog.show();

        // Выполняем запрос асинхронно
        call.enqueue(new Callback<Joke>() {

            // В случае если запрос выполнился успешно, то мы переходим в метод onResponse(...)
            @Override
            public void onResponse(@NonNull Call<Joke> call, @NonNull Response<Joke> response) {
                if (response.isSuccessful()) {
                    // Если в ответ нам пришел код 2xx, то отображаем содержимое запроса
                    fillJokeInfo(response.body());

                } else {
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(MainActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }

                // Скрываем progress bar
               loadingDialog.dismiss();
            }

            // Если запрос не удалось выполнить, например, на телефоне отсутствует подключение к интернету
            @Override
            public void onFailure(@NonNull Call<Joke> call, @NonNull Throwable t) {
                // Скрываем progress bar
                loadingDialog.dismiss();

                Toast.makeText(MainActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }



    private void fillJokeInfo(@NonNull Joke joke) {
        jokeResultList = joke.getValue();
        final JokeAdapter countriesAdapter = new JokeAdapter(jokeResultList);
        recyclerView.setAdapter(countriesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
            case R.id.beer_item:
                startActivity(new Intent(MainActivity.this, BeersActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}