package com.example.taxopark.presentation.login.filldata.filllicense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taxopark.R
import com.example.taxopark.databinding.FragmentFillLicenseBinding
import com.example.taxopark.domain.preference.UserPreferenceManager
import org.koin.android.ext.android.inject

class FillLicenseFragment : Fragment() {


    private lateinit var viewBinding: FragmentFillLicenseBinding
    private val preferenceManager: UserPreferenceManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentFillLicenseBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.fbnBack.setOnClickListener {
            preferenceManager.updateStepData(-2)
        }

        with(viewBinding){


        }

    }


}