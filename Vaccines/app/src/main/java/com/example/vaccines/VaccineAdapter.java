package com.example.vaccines;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.MyViewHolder> {

    //1. datasource
    private VaccineModel[] dataSource;

    public VaccineAdapter(VaccineModel[] dataSource) {
        this.dataSource = dataSource;
    }

    //2. describes the item, view and the meta-data about its place within the recycler view.
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = (itemView).findViewById(R.id.vaccineImage);
            this.textView = (itemView).findViewById(R.id.vaccineText);
        }
    }

    //3. implementing the methods
    //onCreateViewHolder is called when we need a new view
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.vaccine_recyclerview_item, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    //once the new view is created, the onBindViewHolder is
    // responsible for handling and showing all the new data together
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final VaccineModel myListData = dataSource[position];

        holder.textView.setText(dataSource[position].getName());
        holder.imageView.setImageResource(dataSource[position].getImage());
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }
}
