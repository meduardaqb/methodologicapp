package inlocomedia.com.methodologicapp.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import inlocomedia.com.methodologicapp.model.City;
import inlocomedia.com.methodologicapp.model.Temperature;
import inlocomedia.com.methodologicapp.model.Weather;
import inlocomedia.com.methodologicapp.util.Constants;

public abstract class JsonParser
{
    public static ArrayList<City> getCityListFrom(String json)
    {
        ArrayList<City> cityList = new ArrayList<City>();

        try
        {
            JSONObject jsonResult = new JSONObject(json);
            JSONArray jsonArray = jsonResult.getJSONArray(Constants.LIST);

            int jsonArrayLength = jsonArray.length();

            for (int i = 0; i < jsonArrayLength; i++)
            {
                City city = new City();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                city.setName(jsonObject.getString(Constants.NAME));

                JSONArray weatherArray = jsonObject.getJSONArray(Constants.WEATHER);
                city.setWeather(new Weather(weatherArray.getJSONObject(0).getString(Constants.DESCRIPTION)));


                JSONObject mainObject = jsonObject.getJSONObject(Constants.MAIN);
                Temperature temperature = new Temperature();
                temperature.setTempMax(mainObject.getString(Constants.TEMP_MAX));
                temperature.setTempMin(mainObject.getString(Constants.TEMP_MIN));

                city.setTemperature(temperature);

                cityList.add(city);
            }

            return cityList;
        }
        catch (JSONException e)
        {
             return cityList;
        }


    }

}
