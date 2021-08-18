package com.example.gmtest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.gmtest.api.APIService;
import com.example.gmtest.api.ServiceFactory;
import com.example.gmtest.model.Track;
import com.example.gmtest.model.TrackModel;

import org.junit.Test;

import retrofit2.Call;

// Tests implemented using JUnit
public class ApplicationUnitTest {
    @Test
    public void isEmptyTracks() {
        TrackModel trackModel = new TrackModel();
        trackModel.setResultCount(0);
        assertTrue(trackModel.getResultCount() == 0);
    }

    @Test
    public void trackConstructorTest() {
        Track track = new Track();

        assertFalse(track.getTrackName() == null);
        assertFalse(track.getTrackPrice() == null);
        assertFalse(track.getArtistName() == null);
        assertFalse(track.getPrimaryGenreName() == null);
        assertFalse(track.getReleaseDate() == null);
    }


    @Test
    public void urlTest() {
        String wrongURL = "https://itunes.appl.com/";
        String sampleSearch = "Kanye";

        try {
            APIService apiService = ServiceFactory.getInstance(wrongURL);
            Call<TrackModel> trackModelCall = apiService.getTracks(sampleSearch);
            assertFalse(true);

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(true);
        }
    }
}
