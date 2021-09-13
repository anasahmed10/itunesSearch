package com.example.gmtest.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gmtest.MVVMExample
import com.example.gmtest.model.TrackModel
import com.example.gmtest.api.ServiceFactory
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class TrackViewModel(val trackModelView: TrackModelContract.View) : ViewModel(), TrackModelContract.ViewModel {

    private val trackModelMutableLiveData = MutableLiveData<TrackModel>()
    private val scope = CoroutineScope(CoroutineName(name="ViewmodelScope"))

    override fun getTracks(term: String) {
        scope.launch {
            val service = ServiceFactory.getInstance()
            val trackModelCall = service.getTracks(term)

            trackModelCall.enqueue(object : Callback<TrackModel> {
                override fun onResponse(call: Call<TrackModel>, response: Response<TrackModel>) {
                    val trackModel = response.body()
                    if(trackModel.resultCount > 0) {
                        trackModelMutableLiveData.postValue(trackModel)
                    }
                    else {
                        trackModelView.displayMessage("No songs found, Try again.")
                    }
                }

                override fun onFailure(call: Call<TrackModel>, t: Throwable) {
                    trackModelView.displayMessage("No songs found, Try again.")
                    trackModelMutableLiveData.postValue(null)
                }
            })
        }

    }

}