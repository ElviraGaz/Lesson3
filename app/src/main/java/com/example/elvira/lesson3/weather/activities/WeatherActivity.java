package com.example.elvira.lesson3.weather.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.example.elvira.lesson3.MainActivity;
import com.example.elvira.lesson3.R;
import com.example.elvira.lesson3.beerFilter.activities.BeersActivity;
import com.example.elvira.lesson3.weather.api.ApiFactory;
import com.example.elvira.lesson3.weather.api.WeatherService;
import com.example.elvira.lesson3.weather.models.Forecast;
import com.example.elvira.lesson3.weather.models.ForecastAdapter;
import com.example.elvira.lesson3.weather.models.ForecastByDay;
import com.example.elvira.lesson3.weather.models.Weather;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {
    private ProgressDialog loadingDialog;
    private WeatherService weatherService;
    private TextView text;
    private RecyclerView recyclerView;
    private List<ForecastByDay> forecastList;
    private BarChart mBarChart;
    private List<Float> listDayTemp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        text = (TextView) findViewById(R.id.temp);
        loadingDialog = ProgressDialog.show(WeatherActivity.this, "", getString(R.string.network_loading), true);
        // Создаем сервис через который будет выполняться запрос
        weatherService = ApiFactory.getRetrofitInstance().create(WeatherService.class);
        getWeather1();
        getForecast();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Создаём и задаём класс, отвечающий за "декорирование" элементов (добавляем разделители)
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        // Создаём и задаём класс, отвечающий за анимации
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);


        mBarChart = (BarChart) findViewById(R.id.barChart);
    }

    private void getWeather1() {
        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<Weather> call = weatherService.getWeather("Kazan", "metric");
        // Отображаем progress bar
        loadingDialog.show();

        // Выполняем запрос асинхронно
        call.enqueue(new Callback<Weather>() {

            // В случае если запрос выполнился успешно, то мы переходим в метод onResponse(...)
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    // Если в ответ нам пришел код 2xx, то отображаем содержимое запроса
                    fillInfo(response.body());

                } else {
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(WeatherActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }

                // Скрываем progress bar
                loadingDialog.dismiss();
            }

            // Если запрос не удалось выполнить, например, на телефоне отсутствует подключение к интернету
            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {
                // Скрываем progress bar
                loadingDialog.dismiss();

                Toast.makeText(WeatherActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }


    private void getForecast() {
        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<Forecast> call = weatherService.getForecast("Kazan", "metric", 7);
        // Отображаем progress bar
        loadingDialog.show();

        // Выполняем запрос асинхронно
        call.enqueue(new Callback<Forecast>() {

            // В случае если запрос выполнился успешно, то мы переходим в метод onResponse(...)
            @Override
            public void onResponse(@NonNull Call<Forecast> call, @NonNull Response<Forecast> response) {
                if (response.isSuccessful()) {
                    // Если в ответ нам пришел код 2xx, то отображаем содержимое запроса
                    fillForecast(response.body());

                } else {
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(WeatherActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }

                // Скрываем progress bar
                loadingDialog.dismiss();
            }

            // Если запрос не удалось выполнить, например, на телефоне отсутствует подключение к интернету
            @Override
            public void onFailure(@NonNull Call<Forecast> call, @NonNull Throwable t) {
                // Скрываем progress bar
                loadingDialog.dismiss();

                Toast.makeText(WeatherActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void fillInfo(Weather w) {
        text.setText("Сейчас в Казани: " + w.getMain().getTemp() + (char) 0x00B0 + "C");
    }

    private void fillForecast(Forecast forecastResult) {
        forecastList = forecastResult.getList();
        final ForecastAdapter forecastAdapter = new ForecastAdapter(forecastList);
        recyclerView.setAdapter(forecastAdapter);
        for (ForecastByDay f : forecastList) {
            listDayTemp.add(f.getTemp().getFloatDay());
        }
        setupChart();
        addDataToChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId()) {
            case R.id.chuck_norris_item:
                startActivity(new Intent(WeatherActivity.this, MainActivity.class));
                return true;
            case R.id.beer_item:
                startActivity(new Intent(WeatherActivity.this, BeersActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void addDataToChart() {
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < listDayTemp.size(); i++)
            entries.add(new BarEntry((float) i+1, listDayTemp.get(i)));

        BarDataSet set = new BarDataSet(entries, "Прогноз погоды на неделю");

        BarData data = new BarData(set);
        // устанавливаем ширину полосок графика
        data.setBarWidth(0.9f);
        mBarChart.setData(data);
        mBarChart.invalidate(); // refresh
    }

    private void setupChart() {
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);

        mBarChart.getDescription().setEnabled(false);

        // Если на экране отображается больше 60 значение, то подписи будут скрываться
        mBarChart.setMaxVisibleValueCount(60);

        mBarChart.setDrawGridBackground(false);

        // Устанавливаем что ось x будет подстраиваться под максимальное значение
        mBarChart.setFitBars(true);
    }

}
