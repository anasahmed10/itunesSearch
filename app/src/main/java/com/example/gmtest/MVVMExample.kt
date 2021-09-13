package com.example.gmtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmtest.model.Track
import com.example.gmtest.presenter.TrackAdapter
import com.example.gmtest.presenter.TrackModelContract
import com.example.gmtest.presenter.TrackViewModel
import kotlin.collections.ArrayList

class MVVMExample : AppCompatActivity(), TrackModelContract.View {
    private val dataTracks: ArrayList<Track> = ArrayList()
    var viewModel: TrackViewModel? = null

    var recyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    private var trackAdapter: TrackAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        viewModel = TrackViewModel(this)
        val searchBar: EditText = findViewById<View>(R.id.editTextArtist) as EditText
        val searchButton: Button = findViewById<View>(R.id.enterArtistButton) as Button
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        progressBar = findViewById(R.id.progress_bar)

        trackAdapter = TrackAdapter(dataTracks)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = trackAdapter

        // Initializes search
        searchButton.setOnClickListener {search(searchBar.text.toString()) }
    }

    override fun onStart() {
        super.onStart()
        progressBar!!.visibility = View.INVISIBLE
    }

    override fun onStop() {
        super.onStop()
    }

    fun onEmptyViewRetryClick() {}

    override fun onPointerCaptureChanged(hasCapture: Boolean) {}

    override fun displayMessage(message: String?) {
        setLoadingIndicator(false)
    }

    override fun setLoadingIndicator(isLoading: Boolean) {
        if (isLoading) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.INVISIBLE
        }
    }

    override fun displayTracks(dataTracks: MutableList<Track>?) {
        setLoadingIndicator(false)
        this.dataTracks.clear()
        this.dataTracks.addAll(dataTracks!!)
        trackAdapter!!.notifyDataSetChanged()
    }

    // Search function to use runnable to query for results on another thread
    fun search(searchTerm: String) {
        dataTracks.clear()
        trackAdapter!!.notifyDataSetChanged()
        setLoadingIndicator(true)
        // Special Case: If the string is blank the program will return an error from  gathering too much data
        if (searchTerm == "") {
            Log.d("Print", "Print Toast Message")
            Toast.makeText(this, "String is blank: Please enter an artist name", Toast.LENGTH_LONG)
                .show()
        }
        val runnable = Runnable { viewModel?.getTracks(searchTerm) }
        val handler = Handler()
        handler.postDelayed(runnable, 2000)
    }
}
