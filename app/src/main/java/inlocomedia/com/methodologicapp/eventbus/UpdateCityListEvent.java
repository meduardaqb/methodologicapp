package inlocomedia.com.methodologicapp.eventbus;


public class UpdateCityListEvent
{
    String json;

    public UpdateCityListEvent(String json)
    {
        this.json = json;
    }

    public String getJson()
    {
        return json;
    }
}
