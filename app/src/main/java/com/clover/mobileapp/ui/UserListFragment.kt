package com.clover.mobileapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clover.mobileapp.R
import com.clover.mobileapp.adapter.UserListAdapter
import com.clover.mobileapp.model.userlist.Result
import com.clover.mobileapp.network.NetworkResult
import com.clover.mobileapp.util.LoadingDialog
import com.clover.mobileapp.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment to show User List
 */
@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val userDetailViewModel: UserListViewModel by viewModels()
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var userList:ArrayList<Result>
    private var progressDialog:LoadingDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_user_list, container, false)
        initComponent()
        observeAPIUpdate()
        initRecyclerView(view)
        return view
    }

    /**
     * Function to observer api calling update like success, fail, in progress
     * */
    private fun observeAPIUpdate() {
        userDetailViewModel.userList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    progressDialog?.hideProgressdialog()
                    userList.clear()
                    it.data?.let { apiResult -> userList.addAll(apiResult.results) }
                    userListAdapter.notifyDataSetChanged()
                }
                is NetworkResult.Loading -> {
                    progressDialog?.showProgressDialog()
                }
                is NetworkResult.Error -> {
                }
            }
        }
    }

    /**
     * Function to init Recyclerview to show user list
     * */
    private fun initRecyclerView(view: View) {

        userListAdapter =  UserListAdapter(userList, requireActivity() ,
            object : UserListAdapter.OnUserClickListener {
                override fun onUserClick(result: Result) {
                    val action =
                        UserListFragmentDirections.actionUserListToUserDetailFragment(
                            result
                        )
                    Navigation.findNavController(view).navigate(action)
                }
            }
        )
        view?.findViewById<RecyclerView>(R.id.userListRV)?.layoutManager = LinearLayoutManager(activity)
        view?.findViewById<RecyclerView>(R.id.userListRV)?.adapter = userListAdapter

    }

    /**
     * Function to init component
     * */
    private fun initComponent() {
        progressDialog = LoadingDialog(requireActivity())
        userList = ArrayList()
    }
}