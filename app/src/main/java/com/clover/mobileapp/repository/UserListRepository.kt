package com.clover.mobileapp.repository

import androidx.lifecycle.MutableLiveData
import com.clover.mobileapp.model.userlist.RickMortyAPIData
import com.clover.mobileapp.network.APIInterface
import com.clover.mobileapp.network.NetworkResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class UserListRepository @Inject constructor(
    val apiInterface: APIInterface,
){

    var networkResultStatus: MutableLiveData<NetworkResult<RickMortyAPIData>> = MutableLiveData()

    fun getUserList(): MutableLiveData<NetworkResult<RickMortyAPIData>> {

        networkResultStatus.value = NetworkResult.Loading()

        apiInterface.getRickMortyList().enqueue(object : Callback<RickMortyAPIData> {
            override fun onResponse(
                call: Call<RickMortyAPIData>,
                response: Response<RickMortyAPIData>
            ) {
                response.body()?.let {
                    networkResultStatus.value = NetworkResult.Success(it)
                }
            }

            override fun onFailure(call: Call<RickMortyAPIData>, t: Throwable) {
                networkResultStatus.value = NetworkResult.Error("Failed to get Result")
            }
        })

        return networkResultStatus
    }

}