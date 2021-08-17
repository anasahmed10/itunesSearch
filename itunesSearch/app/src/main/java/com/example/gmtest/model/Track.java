package com.example.gmtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("trackPrice")
    @Expose
    private final String trackPrice;

    @SerializedName("releaseDate")
    @Expose
    private final String releaseDate;

    @SerializedName("primaryGenreName")
    @Expose
    private final String primaryGenreName;

    Track(String martistName, String mtrackName, String mtrackPrice, String mreleaseDate, String mprimaryGenreName) {
        artistName = martistName;
        trackName = mtrackName;
        trackPrice = mtrackPrice;
        releaseDate = mreleaseDate;
        primaryGenreName = mprimaryGenreName;
    }

    public void setArtistName(String artistInput) {artistName = artistInput;}

    public void setTrackName(String trackNameInput) {trackName = trackNameInput;}

    public void setTrackPrice(String trackPriceInput) {trackName = trackPriceInput;}

    public void setReleaseDate(String releaseDateInput) {trackName = releaseDateInput;}

    public void setPrimaryGenreName(String primaryGenreNameInput) {trackName = primaryGenreNameInput;}

    public String getArtistName() {return  artistName;}

    public String getTrackName() {return  trackName;}

    public String getTrackPrice() {return trackPrice;}

    public String getReleaseDate() {return releaseDate;}

    public String getPrimaryGenreName() {return primaryGenreName;}
}
