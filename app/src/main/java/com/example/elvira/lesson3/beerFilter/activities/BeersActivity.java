package com.example.elvira.lesson3.beerFilter.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elvira.lesson3.MainActivity;
import com.example.elvira.lesson3.beerFilter.api.ApiFactory;
import com.example.elvira.lesson3.beerFilter.models.BeerAdapter;
import com.squareup.picasso.Picasso;

import com.example.elvira.lesson3.R;
import com.example.elvira.lesson3.beerFilter.api.BeerService;
import com.example.elvira.lesson3.beerFilter.models.Beer;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BeersActivity extends AppCompatActivity {

    private ImageView beerImage;

    //   private TextView beerName;

    private TextView beerTagLine;

    private TextView beerAbv;

    private TextView beerIbu;

    private TextView beerSrm;

    private TextView beerDescription;

    private BeerService beerService;

    private ProgressDialog loadingDialog;
    private Map<String, String> params;
    private Button sendButton;
    private TextView result;

    private EditText abv_gt;
    private EditText abv_lt;
    private EditText ibu_gt;
    private EditText ibu_lt;
    private EditText ebc_gt;
    private EditText ebc_lt;
    private EditText yeast;
    private EditText beerName;
    private EditText brewed_before;
    private EditText brewed_after;
    private EditText hops;
    private EditText malt;
    private EditText food;
    private EditText ids;
    private RecyclerView recyclerView;

    Calendar dateAndTime = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beers);

        initViews();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_result);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Создаём и задаём класс, отвечающий за "декорирование" элементов (добавляем разделители)
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        // Создаём и задаём класс, отвечающий за анимации
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        // Создаем сервис через который будет выполняться запрос
        beerService = ApiFactory.getRetrofitInstance().create(BeerService.class);

        OnClickListener lst = new OnClickListener() {
            @Override
            public void onClick(View v) {
                params = new HashMap<>();
                addParams();
                if (params.size() == 0)
                    Toast.makeText(BeersActivity.this, "Заполните параметры поиска", Toast.LENGTH_SHORT).show();
                else {
                    loadingDialog = ProgressDialog.show(BeersActivity.this, "", getString(R.string.network_loading), true);
                    fetchRandomBeer();
                }
            }
        };
        sendButton.setOnClickListener(lst);

        OnClickListener datePickerLstn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(BeersActivity.this, d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        };
    }


    private void addParams() {
        checkAndAddParam("abv_gt", this.abv_gt.getText().toString());
        checkAndAddParam("abv_lt", this.abv_lt.getText().toString());
        checkAndAddParam("ibu_gt", this.ibu_gt.getText().toString());
        checkAndAddParam("ibu_lt", this.ibu_lt.getText().toString());
        checkAndAddParam("ebc_gt", this.ebc_gt.getText().toString());
        checkAndAddParam("ebc_lt", this.ebc_lt.getText().toString());
        checkAndAddParam("yeast", this.yeast.getText().toString());
        checkAndAddParam("beer_name", this.beerName.getText().toString());
        checkAndAddParam("brewed_before", this.brewed_before.getText().toString());
        checkAndAddParam("brewed_after", this.brewed_after.getText().toString());
        checkAndAddParam("hops", this.hops.getText().toString());
        checkAndAddParam("malt", this.malt.getText().toString());
        checkAndAddParam("food", this.food.getText().toString());
        checkAndAddParam("ids", this.ids.getText().toString());
    }

    private void checkAndAddParam(String paramKey, String paramValue) {
        if (!TextUtils.isEmpty(paramValue))
            params.put(paramKey, paramValue);
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime(brewed_before);

        }
    };

    // установка начальных даты и времени
    private void setInitialDateTime(EditText v) {
        v.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    private void initViews() {
        sendButton = (Button) findViewById(R.id.send_btn);
        beerName = (EditText) findViewById(R.id.beer_name);
        brewed_before = (EditText) findViewById(R.id.brewed_before);
        brewed_after = (EditText) findViewById(R.id.brewed_after);
        abv_gt = (EditText) findViewById(R.id.abv_gt);
        abv_lt = (EditText) findViewById(R.id.abv_lt);
        ibu_gt = (EditText) findViewById(R.id.ibu_gt);
        ibu_lt = (EditText) findViewById(R.id.ibu_lt);
        ebc_gt = (EditText) findViewById(R.id.ebc_gt);
        ebc_lt = (EditText) findViewById(R.id.ebc_lt);
        yeast = (EditText) findViewById(R.id.yeast);
        hops = (EditText) findViewById(R.id.hops);
        malt = (EditText) findViewById(R.id.malt);
        food = (EditText) findViewById(R.id.food);
        ids = (EditText) findViewById(R.id.ids);

    }

    private void fetchRandomBeer() {
        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<List<Beer>> call = beerService.getBeer(params);

        // Отображаем progress bar
        loadingDialog.show();

        // Выполняем запрос асинхронно
        call.enqueue(new Callback<List<Beer>>() {

            // В случае если запрос выполнился успешно, то мы переходим в метод onResponse(...)
            @Override
            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                if (response.isSuccessful()) {
                    // Если в ответ нам пришел код 2xx, то отображаем содержимое запроса
                    fillBeerInfo(response.body());


                } else {
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(BeersActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }

                // Скрываем progress bar
                loadingDialog.dismiss();
            }

            // Если запрос не удалось выполнить, например, на телефоне отсутствует подключение к интернету
            @Override
            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                // Скрываем progress bar
                loadingDialog.dismiss();

                Toast.makeText(BeersActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void fillBeerInfo(@NonNull List<Beer> beer) {
        final BeerAdapter beerAdapter = new BeerAdapter(beer);
        recyclerView.setAdapter(beerAdapter);
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
                startActivity(new Intent(BeersActivity.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
