package com.example.taxopark.presentation.login.filldata

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.taxopark.R
import com.example.taxopark.databinding.FragmentFillDataBinding
import com.example.taxopark.domain.preference.UserPreferenceManager
import com.example.taxopark.domain.preference.UserPreferenceManager.Companion.STEP_DATA
import com.example.taxopark.utils.constant.StepConst
import org.koin.android.ext.android.inject

class FillDataFragment : Fragment() {


    private lateinit var viewBinding: FragmentFillDataBinding
    private val preferenceManager: UserPreferenceManager by inject()
    private lateinit var prefs: SharedPreferences
    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == STEP_DATA) {
                // Do something when START_COST changes
                val step = sharedPreferences.getInt(STEP_DATA, StepConst.FILL_PERSON_DATA)
                if (step > 0){
                    viewBinding.stepper.goToNextStep()
                }else{
                    viewBinding.stepper.goToPreviousStep()
                }

            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentFillDataBinding.inflate(inflater, container, false)
        preferenceManager.updateStepData(StepConst.FILL_PERSON_DATA)

        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = preferenceManager.getSharedPreferences()
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.frame_stepper) as NavHostFragment
        val navController = navHostFragment.findNavController()

        viewBinding.stepper.setupWithNavController(navController)

    }


    override fun onResume() {
        super.onResume()
        prefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onPause() {
        super.onPause()
        prefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

}