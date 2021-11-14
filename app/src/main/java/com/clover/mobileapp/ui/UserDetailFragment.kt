package com.clover.mobileapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.clover.mobileapp.R
import com.clover.mobileapp.network.NetworkResult
import com.clover.mobileapp.util.LoadingDialog
import com.clover.mobileapp.util.Util
import com.clover.mobileapp.viewmodel.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * Fragment to User detail with location
 */
@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private val userDetailViewModel: UserDetailViewModel by viewModels()
    val args: UserDetailFragmentArgs by navArgs()
    private var progressDialog: LoadingDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent(view)
    }

    private fun initComponent(view: View) {
        progressDialog = LoadingDialog(requireActivity())
        userDetailViewModel.fetchLocationDetails(args.userDetail.id)

        val locationNameTV: TextView = view.findViewById(R.id.locatioNameTV)
        val locationTypeTV: TextView = view.findViewById(R.id.locationTypeTV)
        val locationDimensionTV: TextView = view.findViewById(R.id.locationDimensionTV)
        val numberOfResidence: TextView = view.findViewById(R.id.numberOfResidenceTV)

        Util().loadImageWithGlide(
            requireActivity(),
            view.findViewById(R.id.userAvatarImg),
            args.userDetail.image
        )

        userDetailViewModel.locationDetails.observe(viewLifecycleOwner) {

            when (it) {
                is NetworkResult.Success -> {
                    progressDialog?.hideProgressdialog()

                    it.data?.let { locationDetail ->
                        locationNameTV.text = locationDetail.name
                        locationTypeTV.text = locationDetail.type
                        locationDimensionTV.text = locationDetail.dimension
                        numberOfResidence.text = locationDetail.residents.size.toString()
                    }
                }
                is NetworkResult.Loading -> {
                    progressDialog?.showProgressDialog()
                }
                is NetworkResult.Error -> {
                }
            }
        }
    }
}