package inlocomedia.com.methodologicapp;

import android.app.Application;

import com.inlocomedia.android.InLocoMedia;
import com.inlocomedia.android.InLocoMediaOptions;


public class MethodologicApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        InLocoMediaOptions options = InLocoMediaOptions.getInstance(this);
        options.setAdsKey(getString(R.string.inlocomedia_app_id));
        options.setLogEnabled(true);
        options.setDevelopmentDevices("");

        InLocoMedia.init(this, options);
    }
}
