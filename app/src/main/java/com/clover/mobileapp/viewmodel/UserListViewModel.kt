package com.clover.mobileapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clover.mobileapp.LoadingState
import com.clover.mobileapp.model.RickMortyAPIData
import com.clover.mobileapp.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val userListRepository: UserListRepository):
    ViewModel() {

    //keep the loading status of the application
    var loadingState = MutableLiveData<LoadingState>()
    var userList = MutableLiveData<RickMortyAPIData>()


    init {
        loadingState = userListRepository .getLoadingState()
        fetchUserList()
    }

    private fun fetchUserList() {
        loadingState.value = LoadingState.LOADING
        userList = userListRepository.getUserList()

    }


}