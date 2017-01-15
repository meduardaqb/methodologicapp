package inlocomedia.com.methodologicapp.api;

import android.content.Context;
import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import inlocomedia.com.methodologicapp.R;
import inlocomedia.com.methodologicapp.eventbus.RequestErrorEvent;
import inlocomedia.com.methodologicapp.eventbus.ShowProgressEvent;
import inlocomedia.com.methodologicapp.eventbus.UpdateCityListEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class OpenWeatherMap
{
    public static void request(Context context, double latitude, double longitude)
    {
        new RequestApiAsyncTask().execute(getUrl(context, latitude, longitude));
    }

    private static String getUrl(Context context,double latitude, double longitude)
    {
        return Route.API_REQUEST_URL
                .replace("{LAT}", latitude+"")
                .replace("{LON}", longitude+"")
                + context.getString(R.string.open_weather_map_appid);
    }


    private static class RequestApiAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            EventBus.getDefault().post(new ShowProgressEvent());
        }

        @Override
        protected String doInBackground(String... strings)
        {
            String url = strings[0];

            try
            {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = null;

                response = client.newCall(request).execute();
                return response.body().string();
            }
            catch (IOException e)
            {
                cancel(true);
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            EventBus.getDefault().post(new UpdateCityListEvent(result));
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            EventBus.getDefault().post(new RequestErrorEvent());
        }
    }
}
