package com.example.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.databinding.LoadStateItemBinding;

import org.w3c.dom.Text;

public class MoviesLoadStateAdapter extends LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder> {

    private View.OnClickListener retryCallBack;

    public MoviesLoadStateAdapter(View.OnClickListener retryCallBack) {
        this.retryCallBack = retryCallBack;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder loadStateViewHolder, @NonNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        return new LoadStateViewHolder(viewGroup, retryCallBack);
    }

    public static class LoadStateViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        private TextView errorMessage;
        private Button retry;

        public LoadStateViewHolder(@NonNull ViewGroup parent,
                                   @NonNull View.OnClickListener retryCallback) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.load_state_item, parent, false));

            LoadStateItemBinding bind = LoadStateItemBinding.bind(itemView);
            progressBar = bind.progressBar;
            errorMessage = bind.errorMessage;
            retry = bind.retryButton;

            retry.setOnClickListener(retryCallback);
        }

        public void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                errorMessage.setText(loadStateError.getError().getLocalizedMessage());
            }
            retry.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
            progressBar.setVisibility(loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE);
            errorMessage.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
        }
    }
}
