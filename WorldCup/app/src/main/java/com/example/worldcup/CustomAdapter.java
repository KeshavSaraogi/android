package com.example.worldcup;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CountryModel> {

    private ArrayList<CountryModel> countryArrayList;
    Context context;

    public CustomAdapter(ArrayList<CountryModel> data, Context context){
        super(context, R.layout.world_cup_layout, data);
        this.countryArrayList = data;
        this.context = context;
    }

    private static class ViewHolder {
        TextView country;
        TextView cupWins;
        ImageView flag;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get data item for this position
        CountryModel dataModel = getItem(position);

        //check if the existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;

        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate
                    (R.layout.world_cup_layout, parent,false);

            viewHolder.country = (TextView) convertView.findViewById(R.id.countryText);
            viewHolder.cupWins = (TextView) convertView.findViewById(R.id.numberOfWins);
            viewHolder.flag = (ImageView) convertView.findViewById(R.id.countryImage);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.country.setText(dataModel.getCountryName());
        viewHolder.cupWins.setText(dataModel.getCupWins());
        viewHolder.flag.setImageResource(dataModel.getFlagImage());

        return convertView;
    }
}
