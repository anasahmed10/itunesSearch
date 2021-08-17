package com.example.gmtest.presenter;

import com.example.gmtest.model.Track;

import java.util.List;

public class TrackModelContract {

    interface View {
        void displayMessage(String message);

        void setLoadingIndicator(boolean isLoading);

        void displayTracks(List<Track> dataTracks);
    }
}
