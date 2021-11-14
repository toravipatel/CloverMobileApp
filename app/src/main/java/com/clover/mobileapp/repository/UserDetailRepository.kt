package com.clover.mobileapp.repository

import androidx.lifecycle.MutableLiveData
import com.clover.mobileapp.model.location.LocationDetail
import com.clover.mobileapp.network.APIInterface
import com.clover.mobileapp.network.NetworkResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
    val apiInterface: APIInterface,
) {

    var networkResultStatus: MutableLiveData<NetworkResult<LocationDetail>> = MutableLiveData()


    fun getLocationDeails(id: Int): MutableLiveData<NetworkResult<LocationDetail>> {

        networkResultStatus.value = NetworkResult.Loading()
        apiInterface.getLocationDetail(id).enqueue(object : Callback<LocationDetail> {
            override fun onResponse(
                call: Call<LocationDetail>,
                response: Response<LocationDetail>
            ) {

                response.body()?.let {
                    networkResultStatus.value = NetworkResult.Success(it)
                }
            }

            override fun onFailure(call: Call<LocationDetail>, t: Throwable) {
                networkResultStatus.value = NetworkResult.Error("Failed to get Result")
            }
        })

        return networkResultStatus
    }

}