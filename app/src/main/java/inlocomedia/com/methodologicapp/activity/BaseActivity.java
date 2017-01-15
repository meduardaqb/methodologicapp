package inlocomedia.com.methodologicapp.activity;


import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onStop()
    {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
