package com.clover.mobileapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clover.mobileapp.model.userlist.RickMortyAPIData
import com.clover.mobileapp.network.NetworkResult
import com.clover.mobileapp.network.NetworkResult.Loading
import com.clover.mobileapp.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val userListRepository: UserListRepository):
    ViewModel() {

    //live data to update api calling status for UI layer
    var userList: MutableLiveData<NetworkResult<RickMortyAPIData>> = MutableLiveData()

    init {
        fetchUserList()
    }

    /**
     * Function to call userlist api
     * */
    private fun fetchUserList() {
        userList.value = Loading()
        userList = userListRepository.getUserList()

    }
}