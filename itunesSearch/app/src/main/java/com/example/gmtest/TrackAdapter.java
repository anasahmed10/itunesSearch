package com.example.gmtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmtest.model.Track;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private final List<Track> mTrackList;

    public TrackAdapter(List<Track> TrackList) { mTrackList = TrackList;}

    public void setmCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                Context context = parent.getContext();
                View trackView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

                Viewholder viewHolder = new Viewholder(trackView);
                return viewHolder;

            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);

        View view = holder.itemView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTrackList != null && mTrackList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mTrackList != null && mTrackList.size() > 0) {
            return mTrackList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Track> TrackList) {
        mTrackList.addAll(TrackList);
        notifyDataSetChanged();
    }

    // Overloaded addItems function to also support adding a single Listing variable instead of a List of Listings
    public void addItems(Track track) {
        mTrackList.add(track);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onEmptyViewRetryClick();
    }

    public class Viewholder extends BaseViewHolder {

        @BindView(R.id.trackTitle)
        TextView trackNameView;

        @BindView(R.id.trackPrice)
        TextView trackPriceView;

        @BindView(R.id.trackArtist)
        TextView artistNameView;

        @BindView(R.id.trackGenreName)
        TextView genreNameView;

        @BindView(R.id.trackReleaseDate)
        TextView releaseDateView;

        public Viewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }


        protected void clear() {
            trackNameView.setText("");
            trackPriceView.setText("");
            artistNameView.setText("");
            genreNameView.setText("");
            releaseDateView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final Track mTrack = mTrackList.get(position);

            if(mTrack.getTrackName() != null) {
                trackNameView.setText(mTrack.getTrackName());
            }

            if(mTrack.getTrackPrice() != null) {
                trackPriceView.setText(String.format("$%s", mTrack.getTrackPrice()));
            }

            if(mTrack.getArtistName() != null) {
                artistNameView.setText(mTrack.getArtistName());
            }

            if(mTrack.getPrimaryGenreName() != null) {
                genreNameView.setText(mTrack.getPrimaryGenreName());
            }

            if(mTrack.getReleaseDate() != null) {
                releaseDateView.setText(mTrack.getReleaseDate());
            }

        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_message)
        TextView messageTextView;
        @BindView(R.id.buttonRetry)
        TextView buttonRetry;

        EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            buttonRetry.setOnClickListener(v -> mCallback.onEmptyViewRetryClick());
        }

        @Override
        protected void clear() {

        }
    }
}
