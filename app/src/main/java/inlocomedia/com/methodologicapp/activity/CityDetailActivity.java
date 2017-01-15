package inlocomedia.com.methodologicapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.inlocomedia.android.ads.AdError;
import com.inlocomedia.android.ads.AdRequest;
import com.inlocomedia.android.ads.interstitial.InterstitialAd;
import com.inlocomedia.android.ads.interstitial.InterstitialAdListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import inlocomedia.com.methodologicapp.R;
import inlocomedia.com.methodologicapp.model.City;

public class CityDetailActivity extends AppCompatActivity
{

    @Bind(R.id.name_textview)
    TextView nameTextview;

    @Bind(R.id.description_textview)
    TextView descriptionTextview;

    @Bind(R.id.temp_max_textview)
    TextView tempMaxTextview;

    @Bind(R.id.temp_min_textview)
    TextView tempMinTextview;

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setUpUi();
        setUpAd();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if(interstitialAd != null && interstitialAd.isLoaded())
        {
            interstitialAd.show();
        }
        else
        {
            super.onBackPressed();
        }
    }

    private void setUpUi()
    {
        City city = EventBus.getDefault().getStickyEvent(City.class);

        if(city != null)
        {
            nameTextview.setText(getString(R.string.name) +": "+ city.getName());
            descriptionTextview.setText(getString(R.string.description) +": "+ city.getDescription());
            tempMaxTextview.setText(getString(R.string.temp_max) +": "+ city.getTempMax());
            tempMinTextview.setText(getString(R.string.temp_min) +": "+ city.getTempMin());

            EventBus.getDefault().removeStickyEvent(city);
        }
    }

    private void setUpAd()
    {
        InterstitialAd ad = new InterstitialAd(this);
        ad.setInterstitialAdListener(new InterstitialAdListener()
        {

            @Override
            public void onAdReady(final InterstitialAd ad)
            {
                interstitialAd = ad;
            }

            @Override
            public void onAdError(InterstitialAd ad, AdError error)
            {

            }
        });

        AdRequest adRequest = new AdRequest();
        adRequest.setAdUnitId(getString(R.string.interstitial_ad_id));
        ad.loadAd(adRequest);

    }
}
