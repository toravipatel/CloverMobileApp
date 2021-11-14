package com.clover.mobileapp.network

import com.clover.mobileapp.model.userlist.RickMortyAPIData
import com.clover.mobileapp.model.location.LocationDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


// API interface for Retrofit to call API
interface APIInterface {

    /**
     * function to get the User List
     * */
    @GET("character")
    fun getRickMortyList() : Call<RickMortyAPIData>

    /**
     * function to Location details for respective user
     * */
    @GET("location/{id}")
    fun getLocationDetail(@Path("id") id:Int):Call<LocationDetail>
}