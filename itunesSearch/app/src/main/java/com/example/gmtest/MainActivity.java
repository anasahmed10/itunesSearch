package com.example.gmtest;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmtest.model.Track;
import com.example.gmtest.presenter.TrackAdapter;
import com.example.gmtest.presenter.TrackModelContract;
import com.example.gmtest.presenter.TrackModelPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TrackAdapter.Callback, TrackModelContract.View {

    private List<Track> dataTracks = new ArrayList<>();
    TrackModelPresenter presenter;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    private TrackAdapter trackAdapter;
    LinearLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText searchBar;
        Button searchButton;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements

        presenter = new TrackModelPresenter(this);
        searchBar = (EditText) findViewById(R.id.editTextArtist);
        searchButton = (Button) findViewById(R.id.enterArtistButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_bar);

        trackAdapter = new TrackAdapter(dataTracks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trackAdapter);

        // Initializes search
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(searchBar.getText().toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onEmptyViewRetryClick() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void displayMessage(String message) {
        setLoadingIndicator(false);
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        if(isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void displayTracks(List<Track> inputTracks) {
        setLoadingIndicator(false);
        this.dataTracks.clear();
        this.dataTracks.addAll(inputTracks);
        trackAdapter.notifyDataSetChanged();
    }

    // Search function to use runnable to query for results on another thread
    public void search(final String searchTerm) {
        dataTracks.clear();
        trackAdapter.notifyDataSetChanged();

        setLoadingIndicator(true);
        // Special Case: If the string is blank the program will return an error from  gathering too much data
        if(searchTerm == "") {
            Toast.makeText(getApplicationContext(), "String is blank: Please enter an artist name", Toast.LENGTH_LONG);
        }

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                presenter.getTracks(searchTerm);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 2000);
    }
}