package inlocomedia.com.methodologicapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import inlocomedia.com.methodologicapp.R;
import inlocomedia.com.methodologicapp.activity.CityDetailActivity;
import inlocomedia.com.methodologicapp.model.City;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder>
{

    private ArrayList<City> cityList;
    private Context context;

    public CarAdapter(Context context, ArrayList<City> cityList)
    {
        this.cityList = cityList;
        this.context = context;
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView cityTextView;

        MyViewHolder(View view)
        {
            super(view);
            cityTextView = (TextView) view.findViewById(R.id.city_textview);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        holder.cityTextView.setText(cityList.get(position).getName());
        holder.cityTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, CityDetailActivity.class);
                EventBus.getDefault().postSticky(cityList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return cityList.size();
    }
}
