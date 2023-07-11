package com.example.journalapp.ui;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.journalapp.R;
import com.example.journalapp.model.Journal;

import java.util.List;

public class JournalRecyclerAdapter extends RecyclerView.Adapter<JournalRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<Journal> journalList;

    public JournalRecyclerAdapter(Context context, List<Journal> journalList) {
        this.context = context;
        this.journalList = journalList;
    }

    @NonNull
    @Override
    public JournalRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.journal_row, viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalRecyclerAdapter.ViewHolder holder, int position) {
        Journal journal = journalList.get(position);
        String imageURI;

        holder.title.setText(journal.getTitle());
        holder.description.setText(journal.getDescription());
        holder.name.setText(journal.getUsername());
        imageURI = journal.getImageURI();

        String timeAgo = (String) DateUtils.getRelativeTimeSpanString(
                journal.getTimestamp()
                        .getSeconds()*1000);
        holder.dateAdded.setText(timeAgo);

        Glide.with(context)
                .load(imageURI)
                .fitCenter()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title, description, dateAdded, name;
        public ImageView image, shareButton;
        String userID, username;

        public ViewHolder(@NonNull View itemView, Context c) {
            super(itemView);
            context = c;

            title = itemView.findViewById(R.id.journal_title_list);
            description = itemView.findViewById(R.id.journal_description_list);
            dateAdded = itemView.findViewById(R.id.journalTimestamp);
            image = itemView.findViewById(R.id.journal_image_list);
            name = itemView.findViewById(R.id.journal_row_username);

            shareButton = itemView.findViewById(R.id.journalItemShareButton);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
