package com.mobilprog.bgergely_crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRVAdapter extends ListAdapter<MovieModal, MovieRVAdapter.ViewHolder> {
    private OnItemClickListener listener;

    MovieRVAdapter() {
        super(DIFF_CALLBACK);
    }
    
    private static final DiffUtil.ItemCallback<MovieModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieModal>() {
        @Override
        public boolean areItemsTheSame(MovieModal oldItem, MovieModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(MovieModal oldItem, MovieModal newItem) {
            return oldItem.getMovieName().equals(newItem.getMovieName()) &&
                    oldItem.getMovieDescription().equals(newItem.getMovieDescription()) &&
                    oldItem.getMovieDuration().equals(newItem.getMovieDuration());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModal model = getMovieAt(position);
        holder.movieNameTV.setText(model.getMovieName());
        holder.movieDescTV.setText(model.getMovieDescription());
        holder.movieRecommendationTV.setText(model.getMovieDuration());
    }
    
    public MovieModal getMovieAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieNameTV, movieDescTV, movieRecommendationTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTV = itemView.findViewById(R.id.idTVMovieName);
            movieDescTV = itemView.findViewById(R.id.idTVMovieDescription);
            movieRecommendationTV = itemView.findViewById(R.id.idTVMovieRecommendation);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(MovieModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}


