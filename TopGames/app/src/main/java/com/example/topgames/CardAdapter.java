package com.example.topgames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    //1 datasource
    private Context context;
    private ArrayList<CardModel> gameList;

    //2 Constructor
    public CardAdapter(Context context, ArrayList<CardModel> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    //3 View Holder - class that will instantiate the widgets in the custom layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageGame);
            title = itemView.findViewById(R.id.textGame);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardModel model = gameList.get(position);
        holder.title.setText(model.getTitle());
        holder.image.setImageResource(model.getImage());
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }
}
