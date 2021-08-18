package com.example.gmtest.presenter;

import com.example.gmtest.model.Track;

import java.util.List;

// interface to be implemented in MainActivity
public class TrackModelContract {

    public interface View {
        void displayMessage(String message);

        void setLoadingIndicator(boolean isLoading);

        void displayTracks(List<Track> dataTracks);
    }

    interface Presenter {
        void getTracks(String term);
    }
}
