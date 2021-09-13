package com.example.gmtest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.gmtest.model.Track;
import com.example.gmtest.model.TrackModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void trackSortTest() {
        Track track1 = new Track();
        Track track2 = new Track();
        Track track3 = new Track();
        Track track4 = new Track();
        TrackModel trackModel = new TrackModel();

        track1.setReleaseDate("2021-07-11");
        track2.setReleaseDate("2017-09-22");
        track3.setReleaseDate("2015-12-24");
        track4.setReleaseDate("2020-03-09");

        List<Track> trackList = new ArrayList<Track>();
        trackList.add(track1);
        trackList.add(track2);
        trackList.add(track3);
        trackList.add(track4);

        trackModel.setTracks(trackList);
        List<Track> sortedTracks = trackModel.sortRelease();

        assertTrue(sortedTracks.get(0) == track1);
        assertTrue(sortedTracks.get(1) == track2);
        assertTrue(sortedTracks.get(2) == track4);
        assertTrue(sortedTracks.get(3) == track3);
    }
}
