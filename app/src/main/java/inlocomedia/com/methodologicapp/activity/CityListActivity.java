package inlocomedia.com.methodologicapp.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import inlocomedia.com.methodologicapp.R;
import inlocomedia.com.methodologicapp.adapter.CarAdapter;
import inlocomedia.com.methodologicapp.api.JsonParser;
import inlocomedia.com.methodologicapp.api.OpenWeatherMap;
import inlocomedia.com.methodologicapp.eventbus.RequestErrorEvent;
import inlocomedia.com.methodologicapp.eventbus.ShowProgressEvent;
import inlocomedia.com.methodologicapp.eventbus.UpdateCityListEvent;
import inlocomedia.com.methodologicapp.model.City;
import inlocomedia.com.methodologicapp.util.Constants;
import inlocomedia.com.methodologicapp.util.NetwokUtil;


public class CityListActivity extends BaseActivity
{

    @Bind(R.id.city_recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

        setUpUi();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onShowDialog(ShowProgressEvent event)
    {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Subscribe
    public void onUpdateList(UpdateCityListEvent event)
    {
        progressBar.setVisibility(View.INVISIBLE);
        ArrayList<City> cityArrayList = JsonParser.getCityListFrom(event.getJson());
        recyclerView.setAdapter(new CarAdapter(this,cityArrayList));
    }

    @Subscribe
    public void onRequestError(RequestErrorEvent event)
    {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, getString(R.string.request_error_message), Toast.LENGTH_LONG).show();
        finish();
    }

    private void setUpUi()
    {
        if(NetwokUtil.hasConnection(this))
        {
            OpenWeatherMap.request(this,
                    getIntent().getExtras().getDouble(Constants.LAT_TAG),
                    getIntent().getExtras().getDouble(Constants.LONG_TAG));
        }
    }
}
