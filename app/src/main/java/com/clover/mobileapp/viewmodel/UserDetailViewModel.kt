package com.clover.mobileapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clover.mobileapp.model.location.LocationDetail
import com.clover.mobileapp.network.NetworkResult
import com.clover.mobileapp.repository.UserDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(private val userDetailRepository: UserDetailRepository) :
    ViewModel() {

    //live data to update api calling status for UI layer
    var locationDetails: MutableLiveData<NetworkResult<LocationDetail>> = MutableLiveData()

    /**
     * Function to call location API.
     * @param id user ID
     * */
    fun fetchLocationDetails(id: Int) {
        locationDetails = userDetailRepository.getLocationDeails(id)
    }
}