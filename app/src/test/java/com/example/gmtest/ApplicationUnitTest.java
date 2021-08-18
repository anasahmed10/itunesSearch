package com.example.gmtest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.gmtest.model.Track;
import com.example.gmtest.model.TrackModel;

import org.junit.Test;

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
    
}
