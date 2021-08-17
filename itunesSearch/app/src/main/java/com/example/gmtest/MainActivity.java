package com.example.gmtest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements TrackAdapter.Callback{

    EditText searchBar;
    Button searchButton;
    RecyclerView recyclerView;
    TrackAdapter trackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = (EditText) findViewById(R.id.editTextArtist);
        searchButton = (Button) findViewById(R.id.enterArtistButton);
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
}