package inlocomedia.com.methodologicapp.model;


public class City
{

    Temperature temperature;
    String name;
    Weather weather;

    public String getName()
    {
        return name;
    }

    public void setTemperature(Temperature temperature)
    {
        this.temperature = temperature;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getDescription()
    {
        return weather.getDescription();
    }

    public String getTempMin()
    {
        return temperature.getTempMin();
    }

    public String getTempMax()
    {
        return temperature.getTempMax();
    }

}
