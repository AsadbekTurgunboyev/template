package com.example.taxopark.presentation.login.filldata.fillperson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taxopark.databinding.FragmentFillPersonBinding
import com.example.taxopark.domain.preference.UserPreferenceManager
import com.example.taxopark.utils.constant.StepConst.FILL_CAR_DATA
import org.koin.android.ext.android.inject

class FillPersonFragment : Fragment() {



    private lateinit var viewBinding: FragmentFillPersonBinding
    private val preferenceManager: UserPreferenceManager by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentFillPersonBinding.inflate(inflater,container,false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.nextDetailCarFbn.setOnClickListener {
                preferenceManager.updateStepData(FILL_CAR_DATA)
        }
    }



}