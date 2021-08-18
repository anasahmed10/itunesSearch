package com.example.gmtest.presenter;

import com.example.gmtest.api.APIService;
import com.example.gmtest.api.ServiceFactory;
import com.example.gmtest.model.TrackModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Presenter class for back-end interaction
public class TrackModelPresenter implements TrackModelContract.Presenter {

    private TrackModelContract.View trackModelView;

    public TrackModelPresenter(TrackModelContract.View trackModelViewInput) {
        trackModelView = trackModelViewInput;
    }

    // Queries the database and adds the values to the TrackModel List
    @Override
    public void getTracks(String term) {
        APIService service = ServiceFactory.getInstance();
        Call<TrackModel> trackModelCall = service.getTracks(term);
        trackModelCall.enqueue(new Callback<TrackModel>() {
            @Override
            public void onResponse(Call<TrackModel> call, Response<TrackModel> response) {
                TrackModel trackModel = response.body();
                // If there are no tracks then display message
                if (trackModel.getResultCount() > 0) {
                    trackModelView.displayTracks(trackModel.getTracks());
                } else {
                    trackModelView.displayMessage("No songs found, Try again.");
                }
            }

            @Override
            public void onFailure(Call<TrackModel> call, Throwable t) {
                trackModelView.displayMessage("No songs found, Try again.");
            }
        });
    }

}
